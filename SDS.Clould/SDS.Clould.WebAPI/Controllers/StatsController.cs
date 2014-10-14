namespace SDS.Clould.WebAPI.Controllers
{
    using System;
    using System.Linq;
    using System.Web.Http;

    using Microsoft.AspNet.Identity;

    using SDS.Clould.Data;

    public class StatsController : BaseApiController
    {
        public StatsController(ISDSData data)
            : base(data)
        {
        }

        public StatsController()
            : this(new SDSData())
        {
        }

        /// <summary>
        /// Returns statistics about the system - all packages, finished/active transports and users count
        /// </summary>
        [AllowAnonymous]
        [HttpGet]
        public IHttpActionResult Get()
        {
            return this.Ok(new 
            {
                UsersCount = this.Data.Users.All().Count(),
                FinishedTransports = this.Data.Transports.All().Where(t => t.Arrival < DateTime.Now).Count(),
                ActiveTransports = this.Data.Transports.All().Where(t => t.Arrival >= DateTime.Now).Count(),
                PackagesCount = this.Data.Packages.All().Count()
            });
        }
    }
}