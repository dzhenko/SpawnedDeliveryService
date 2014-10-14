namespace SDS.Clould.WebAPI.DataModels
{
    using System;
    using System.Linq.Expressions;

    using SDS.Clould.Models;

    public class FinishedPackageDataModel
    {
        public static Expression<Func<Package, FinishedPackageDataModel>> FromData
        {
            get
            {
                return p => new FinishedPackageDataModel
                {
                    Id = p.Id,
                    FromTown = p.FromTown,
                    ToTown = p.ToTown,
                    Arrival = p.Transport.Arrival,
                    DriverName = p.Transport.Driver.UserName,
                    DriverPhone = p.Transport.Driver.PhoneNumber
                };
            }
        }

        public int Id { get; set; }

        public string FromTown { get; set; }

        public string ToTown { get; set; }

        public DateTime Arrival { get; set; }

        public string DriverName { get; set; }

        public string DriverPhone { get; set; }
    }
}