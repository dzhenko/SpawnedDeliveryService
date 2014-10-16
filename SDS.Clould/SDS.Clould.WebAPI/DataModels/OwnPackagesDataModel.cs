namespace SDS.Clould.WebAPI.DataModels
{
    using System;
    using System.Linq.Expressions;

    using SDS.Clould.Models;

    public class OwnPackagesDataModel
    {
        public static Expression<Func<Package, OwnPackagesDataModel>> FromData
        {
            get
            {
                return p => new OwnPackagesDataModel
                {
                    Id = p.Id,
                    FromTown = p.FromTown,
                    ToTown = p.ToTown,
                    Deadline = p.Deadline
                };
            }
        }

        public int Id { get; set; }

        public string FromTown { get; set; }

        public string ToTown { get; set; }

        public DateTime Deadline { get; set; }
    }
}