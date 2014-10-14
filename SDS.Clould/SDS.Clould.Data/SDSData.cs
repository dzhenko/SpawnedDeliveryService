namespace SDS.Clould.Data
{
    using System;
    using System.Collections.Generic;
    using System.Linq;

    using SDS.Clould.Data.Repositories;
    using SDS.Clould.Models;

    public class SDSData : ISDSData
    {
        private SDSDbContext context;
        private IDictionary<Type, object> repositories;

        public SDSData() 
            : this(new SDSDbContext())
        {
        }

        public SDSData(SDSDbContext context)
        {
            this.context = context;
            this.repositories = new Dictionary<Type, object>();
        }

        public IRepository<User> Users
        {
            get
            {
                return this.GetRepository<User>();
            }
        }

        public IRepository<Package> Packages
        {
            get
            {
                return this.GetRepository<Package>();
            }
        }

        public IRepository<Transport> Transports
        {
            get
            {
                return this.GetRepository<Transport>();
            }
        }

        public void SaveChanges()
        {
            this.context.SaveChanges();
        }

        private IRepository<T> GetRepository<T>() where T : class
        {
            var typeOfModel = typeof(T);

            if (!this.repositories.ContainsKey(typeOfModel))
            {
                this.repositories.Add(typeOfModel, Activator.CreateInstance(typeof(Repository<T>), this.context));
            }

            return (IRepository<T>)this.repositories[typeOfModel];
        }
    }
}
