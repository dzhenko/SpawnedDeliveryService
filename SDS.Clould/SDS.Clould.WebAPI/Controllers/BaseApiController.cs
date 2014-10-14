using SDS.Clould.Data;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace SDS.Clould.WebAPI.Controllers
{
    public class BaseApiController : ApiController
    {
        private ISDSData data;

        public BaseApiController(ISDSData data)
        {
            this.data = data;
        }

        protected ISDSData Data
        {
            get { return this.data; }
        }
    }
}
