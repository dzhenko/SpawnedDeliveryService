namespace SDS.Clould.WebAPI.Controllers
{
    using System;
    using System.Linq;
    using System.Web.Http;
    using Microsoft.AspNet.Identity;
    using SDS.Clould.Data;
    using SDS.Clould.WebAPI.Providers;
    using SDS.Clould.Models;
    using System.Collections.Generic;
    using SDS.Clould.WebAPI.DataModels;

    [Authorize]
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
        /// Sets the current location of a driver (user)
        /// </summary>
        [HttpPost]
        public IHttpActionResult Coordinates(CoordinatesDataModel model)
        {
            if (model == null || !ModelState.IsValid)
            {
                try
                {
                    return this.BadRequest(ModelState.Values.FirstOrDefault().Errors.FirstOrDefault().ErrorMessage);
                }
                catch (Exception)
                {
                    return this.BadRequest("Invalid set coordinates data");
                }
            }

            var user = this.Data.Users.Find(User.Identity.GetUserId());

            user.Longitude = model.Longitude;
            user.Latitude = model.Latitude;

            this.Data.Users.Update(user);
            this.Data.SaveChanges();

            return this.Ok();
        }
    }
}