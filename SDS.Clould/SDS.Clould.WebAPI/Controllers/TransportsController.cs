namespace SDS.Clould.WebAPI.Controllers
{
    using System;
    using System.Linq;
    using System.Web.Http;

    using Microsoft.AspNet.Identity;

    using SDS.Clould.Data;
    using SDS.Clould.WebAPI.DataModels;

    [Authorize]
    public class TransportsController : BaseApiController
    {
        private const int ResultsPerPage = 10;

        public TransportsController(ISDSData data)
            : base(data)
        {
        }

        public TransportsController()
            : this(new SDSData())
        {
        }

        /// <summary>
        /// By givven model (Towns, Dates (both from / to) and Space+Kilograms creates a new Transport
        /// </summary>
        [HttpPost]
        public IHttpActionResult Create(NewTransportDataModel model)
        {
            if (model == null || !ModelState.IsValid)
            {
                try
                {
                    return this.BadRequest(ModelState.Values.FirstOrDefault().Errors.FirstOrDefault().ErrorMessage);
                }
                catch (Exception)
                {
                    return this.BadRequest("Invalid create transport data");
                }
            }

            var transport = NewTransportDataModel.FromModel(model, User.Identity.GetUserId());

            this.Data.Transports.Add(transport);
            this.Data.SaveChanges();

            return this.Created("", transport.Id);
        }

        /// <summary>
        /// Transports that are still in the process of executing or to be executed
        /// </summary>
        [HttpGet]
        public IHttpActionResult Active()
        {
            var user = this.Data.Users.Find(User.Identity.GetUserId());

            return this.Ok(this.Data.Transports.All()
                .Where(t => t.Packages.Any(p => p.Owner.Id == user.Id) && t.Arrival < DateTime.Now)
                .Select(ActiveTransportDataModel.FromData));
        }

        /// <summary>
        /// Transports that are pending - you are the driver and you must transport them
        /// </summary>
        [HttpGet]
        public IHttpActionResult Pending()
        {
            var user = this.Data.Users.Find(User.Identity.GetUserId());

            return this.Ok(this.Data.Transports.All()
                .Where(t => t.Driver.Id == user.Id && t.Arrival < DateTime.Now)
                .Select(PendingTransportDataModel.FromData));
        }

        /// <summary>
        /// Transports that are compleated
        /// </summary>
        [HttpGet]
        public IHttpActionResult Finished()
        {
            return this.Ok(this.Data.Transports.All()
                .Where(t => t.Arrival < DateTime.Now)
                .Select(FinishedTransportDataModel.FromData));
        }

        /// <summary>
        /// Transports that are compleated
        /// </summary>
        [HttpPost]
        public IHttpActionResult AddPackage(AddPackageToTransportDataModel model)
        {
            if (model == null || !ModelState.IsValid)
            {
                try
                {
                    return this.BadRequest(ModelState.Values.FirstOrDefault().Errors.FirstOrDefault().ErrorMessage);
                }
                catch (Exception)
                {
                    return this.BadRequest("Invalid package/transport data");
                }
            }

            var package = this.Data.Packages.Find(model.PackageId);
            if (package == null)
            {
                return this.BadRequest("Invalid package Id");
            }

            var transport = this.Data.Transports.Find(model.TransportId);
            if (transport == null)
            {
                return this.BadRequest("Invalid transport Id");
            }

            if (package.Kilograms > transport.AvailableKilograms - transport.Packages.Sum(p => p.Kilograms))
            {
                return this.BadRequest("Not enough kilograms left on the transport");
            }

            if (package.Space > transport.AvailableSpace - transport.Packages.Sum(p => p.Space))
            {
                return this.BadRequest("Not enough space left on the transport");
            }

            if (transport.Packages.Any(p => p.Id == package.Id))
            {
                return this.BadRequest("Package is already in the transport");
            }

            if (transport.Arrival <= DateTime.Now)
            {
                return this.BadRequest("This tranposrt is already compleated");
            }

            if (transport.Arrival <= package.Deadline)
            {
                return this.BadRequest("This package needs to get to " + package.ToTown + " sooner!");
            }

            transport.Packages.Add(package);
            this.Data.Transports.Update(transport);
            this.Data.Packages.Update(package);
            this.Data.SaveChanges();

            return this.Ok();
        }

        /// <summary>
        /// Transports that are compleated
        /// </summary>
        [HttpGet]
        public IHttpActionResult Get(int id)
        {
            return this.Ok(this.Data.Transports.All().Where(t => t.Id == id).Select(DetailsTransportDataModel.FromData));
        }

        /// <summary>
        /// Find Transports
        /// </summary>
        [HttpGet]                                           //find=towns&value=Sofia-Burgas for towns searching FROM - TO
        public IHttpActionResult Find(int page = 0, string sort = null, string find = null, string value = null)
        {
            var allTransports = this.Data.Transports.All().Where(t => t.Departure < DateTime.Now &&
                                                                      t.AvailableSpace - t.Packages.Sum(p=> p.Space) > 0 &&
                                                                      t.AvailableKilograms - t.Packages.Sum(p=> p.Kilograms) > 0);

            if (!string.IsNullOrEmpty(sort))
            {
                switch (sort)
                {
                    case "space":
                        allTransports = allTransports.OrderBy(t => t.AvailableSpace - t.Packages.Sum(p => p.Space)).ThenByDescending(t => t.Departure); 
                        break;
                    case "kilograms":
                        allTransports = allTransports.OrderBy(t => t.AvailableKilograms - t.Packages.Sum(p => p.Kilograms)).ThenByDescending(t => t.Departure); 
                        break;
                    default:
                        break;
                }
            }
            else
            {
                allTransports = allTransports.OrderByDescending(t => t.Departure); 
            }

            if (!string.IsNullOrEmpty(find) && !string.IsNullOrEmpty(value))
            {
                switch (find)
                {
                    case "towns":
                        var townsSplited = value.Split('-');
                        allTransports = allTransports.Where(t => t.FromTown == townsSplited[0] && t.ToTown == townsSplited[1]); 
                        break;
                    case "space":
                        var spaceAsInt = int.Parse(value);
                        allTransports = allTransports.Where(t => t.AvailableSpace - t.Packages.Sum(p => p.Space) >= spaceAsInt); 
                        break;
                    case "kilograms":
                        var kilogramsAsInt = int.Parse(value);
                        allTransports = allTransports.Where(t => t.AvailableKilograms - t.Packages.Sum(p => p.Kilograms) >= kilogramsAsInt); 
                        break;
                    default:
                        break;
                }
            }

            allTransports = allTransports.Skip(page * ResultsPerPage).Take(ResultsPerPage);

            return this.Ok(allTransports);
        }
    }
}