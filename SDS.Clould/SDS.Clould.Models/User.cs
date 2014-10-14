namespace SDS.Clould.Models
{
    using System;
    using System.Security.Claims;
    using System.Threading.Tasks;

    using Microsoft.AspNet.Identity;
    using Microsoft.AspNet.Identity.EntityFramework;
    using System.Collections.Generic;

    public class User : IdentityUser
    {
        public async Task<ClaimsIdentity> GenerateUserIdentityAsync(UserManager<User> manager, string authenticationType)
        {
            // Note the authenticationType must match the one defined in CookieAuthenticationOptions.AuthenticationType
            var userIdentity = await manager.CreateIdentityAsync(this, authenticationType);
            // Add custom user claims here
            return userIdentity;
        }

        private ICollection<Package> packages;
        private ICollection<Transport> transports;

        public User()
            : base()
        {
            this.packages = new HashSet<Package>();
            this.transports = new HashSet<Transport>();
        }
        public virtual ICollection<Transport> Transports
        {
            get
            {
                return this.transports;
            }
            set
            {
                this.transports = value;
            }
        }

        public virtual ICollection<Package> Packages
        {
            get
            {
                return this.packages;
            }
            set
            {
                this.packages = value;
            }
        }
    }
}
