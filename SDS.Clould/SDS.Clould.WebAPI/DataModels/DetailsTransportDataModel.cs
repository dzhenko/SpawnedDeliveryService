namespace SDS.Clould.WebAPI.DataModels
{
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Linq.Expressions;

    using SDS.Clould.Models;

    public class DetailsTransportDataModel
    {
        public static Expression<Func<Transport, DetailsTransportDataModel>> FromData
        {
            get
            {
                return t => new DetailsTransportDataModel
                {
                    Id = t.Id,
                    FromTown = t.FromTown,
                    ToTown = t.ToTown,
                    AvailableSpace = t.AvailableSpace - t.Packages.Sum(p => p.Space),
                    AvailableKilograms = t.AvailableKilograms - t.Packages.Sum(p => p.Kilograms),
                    TotalPrice = t.Packages.Sum(p => p.Price),
                    Departure = t.Departure,
                    Arrival = t.Arrival,
                    DriverId = t.Driver.Id,
                    DriverName = t.Driver.UserName,
                    DriverNumber = t.Driver.PhoneNumber,
                    Packages = t.Packages.AsQueryable().Select(DetailsTransportPackageDataModel.FromData)
                };
            }
        }

        public int Id { get; set; }

        public string FromTown { get; set; }

        public string ToTown { get; set; }

        public int AvailableSpace { get; set; }

        public int AvailableKilograms { get; set; }

        public decimal TotalPrice { get; set; }

        public DateTime Departure { get; set; }

        public DateTime Arrival { get; set; }

        public string DriverId { get; set; }

        public string DriverName { get; set; }

        public string DriverNumber { get; set; }

        public IEnumerable<DetailsTransportPackageDataModel> Packages { get; set; }
    }
}