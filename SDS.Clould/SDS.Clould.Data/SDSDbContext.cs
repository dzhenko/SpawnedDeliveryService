namespace SDS.Clould.Data
{
    using System;
    using System.Data.Entity;
    using System.Data.Entity.ModelConfiguration.Conventions;

    using Microsoft.AspNet.Identity.EntityFramework;

    using SDS.Clould.Data.Migrations;
    using SDS.Clould.Models;

    public class SDSDbContext : IdentityDbContext<User>
    {
        public SDSDbContext()
            : base("SDSConnection", throwIfV1Schema: false)
        {
            Database.SetInitializer<SDSDbContext>(new MigrateDatabaseToLatestVersion<SDSDbContext, Configuration>());
        }

        public static SDSDbContext Create()
        {
            return new SDSDbContext();
        }

        public IDbSet<Package> Packages { get; set; }

        public IDbSet<Transport> Transports { get; set; }
    }
}
