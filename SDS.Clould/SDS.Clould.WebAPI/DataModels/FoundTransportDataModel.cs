namespace SDS.Clould.WebAPI.DataModels
{
    using System;
    using System.Linq;
    using System.Linq.Expressions;

    using SDS.Clould.Models;

    public class FoundTransportDataModel
    {
        public static Expression<Func<Transport, FoundTransportDataModel>> FromData
        {
            get
            {
                return t => new FoundTransportDataModel
                {
                    Id = t.Id,
                    Space = t.AvailableSpace - t.Packages.Sum(p => p.Space),
                    Kilograms = t.AvailableKilograms - t.Packages.Sum(p => p.Kilograms),
                    FromTown = t.FromTown,
                    ToTown = t.ToTown,
                    Departure = t.Departure,
                    Arrival = t.Arrival
                };
            }
        }

        public int Id { get; set; }

        public int Space { get; set; }

        public int Kilograms { get; set; }

        public string FromTown { get; set; }

        public string ToTown { get; set; }

        public DateTime Departure { get; set; }

        public DateTime Arrival { get; set; }
    }
}