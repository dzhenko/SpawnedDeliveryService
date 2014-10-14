namespace SDS.Clould.WebAPI.Controllers
{
    using System;
    using System.Linq;
    using System.Web.Http;

    using Microsoft.AspNet.Identity;

    using SDS.Clould.Data;
    using SDS.Clould.WebAPI.DataModels;

    public class UsersController : BaseApiController
    {
        public UsersController(ISDSData data)
            : base(data)
        {
        }

        public UsersController()
            : this(new SDSData())
        {
        }

        /// <summary>
        /// Change or Enter user phone number, so he can participate in deliveries / transports
        /// </summary>
        [Authorize]
        [HttpPost]
        public IHttpActionResult Phone(PhoneDataModel model)
        {
            if (model == null || !ModelState.IsValid)
            {
                return this.BadRequest(ModelState);
            }

            this.Data.Users.Find(User.Identity.GetUserId()).PhoneNumber = model.Phone;
            this.Data.SaveChanges();

            return this.Ok(model.Phone);
        }
    }
}