namespace SDS.Clould.Data
{
    using System;
    using System.Linq;

    using SDS.Clould.Data.Repositories;
    using SDS.Clould.Models;

    public interface ISDSData
    {
        IRepository<User> Users { get; }

        IRepository<Package> Packages { get; }

        IRepository<Transport> Transports { get; }

        void SaveChanges();
    }
}
