namespace SDS.Clould.WebAPI.Controllers
{
    using System;
    using System.Linq;
    using System.Web.Http;

    using Microsoft.AspNet.Identity;

    using SDS.Clould.Data;
    using SDS.Clould.WebAPI.DataModels;
    using SDS.Clould.WebAPI.Providers;
    using SDS.Clould.ImageUpload;

    [Authorize]
    public class PackagesController : BaseApiController
    {
        private const int ResultsPerPage = 10;
        private IImageUploader imageUploader;

        public PackagesController(ISDSData data, IImageUploader imageUploader)
            : base(data)
        {
            this.imageUploader = imageUploader;
        }

        public PackagesController()
            : this(new SDSData(), new ImageUploader())
        {
        }

        /// <summary>
        /// By givven model (Towns(from/to), Deadline, Space+Kilograms, creates a new Transport
        /// </summary>
        public IHttpActionResult Create(NewPackageDataModel model)
        {
            if (model == null || !ModelState.IsValid)
            {
                try
                {
                    return this.BadRequest(ModelState.Values.FirstOrDefault().Errors.FirstOrDefault().ErrorMessage);
                }
                catch (Exception)
                {
                    return this.BadRequest("Invalid create package data");
                }
            }

            var pictureUrl = model.Picture == null ? null : this.imageUploader.UrlFromBase64Image(model.Picture);
            var package = NewPackageDataModel.FromModel(model, User.Identity.GetUserId(), pictureUrl);

            this.Data.Packages.Add(package);
            this.Data.SaveChanges();

            return this.Created("", package.Id);
        }

        /// <summary>
        /// Transports that are compleated
        /// </summary>
        [HttpGet]
        public IHttpActionResult Finished()
        {
            return this.Ok(this.Data.Packages.All()
                .Where(t => t.Transport.Arrival < DateTime.Now)
                .Select(FinishedPackageDataModel.FromData));
        }

        [HttpGet]
        public IHttpActionResult Get(int id)
        {
            return this.Ok(this.Data.Packages.All().Where(t => t.Id == id).Select(DetailsPackageDataModel.FromData));
        }

        /// <summary>
        /// Find Package
        /// </summary>
        [HttpGet]                                           //find=towns&value=Sofia-Burgas for towns searching FROM - TO
        public IHttpActionResult Find(int page = 0, string sort = null, string find = null, string value = null)
        {
            var allPackages = this.Data.Packages.All().Where(p => p.Deadline < DateTime.Now);

            if (!string.IsNullOrEmpty(sort))
            {
                switch (sort)
                {
                    case "space":
                        allPackages = allPackages.OrderBy(p => p.Space).ThenBy(p => p.Deadline);
                        break;
                    case "kilograms":
                        allPackages = allPackages.OrderBy(p => p.Kilograms).ThenBy(p => p.Deadline);
                        break;
                    case "price":
                        allPackages = allPackages.OrderBy(p => p.Price).ThenBy(p => p.Deadline);
                        break;
                    default:
                        break;
                }
            }
            else
            {
                allPackages = allPackages.OrderByDescending(p => p.Deadline);
            }

            if (!string.IsNullOrEmpty(find) && !string.IsNullOrEmpty(value))
            {
                switch (find)
                {
                    case "towns":
                        var townsSplited = value.Split('-');
                        allPackages = allPackages.Where(t => t.FromTown == townsSplited[0] && t.ToTown == townsSplited[1]);
                        break;
                    case "space":
                        var spaceAsInt = int.Parse(value);
                        allPackages = allPackages.Where(p => p.Space <= spaceAsInt);
                        break;
                    case "kilograms":
                        var kilogramsAsInt = int.Parse(value);
                        allPackages = allPackages.Where(p => p.Kilograms <= kilogramsAsInt);
                        break;
                    case "price":
                        var priceAsInt = int.Parse(value);
                        allPackages = allPackages.Where(p => p.Price <= priceAsInt);
                        break;
                    default:
                        break;
                }
            }

            allPackages = allPackages.Skip(page * ResultsPerPage).Take(ResultsPerPage);

            return this.Ok(allPackages);
        }
    }
}