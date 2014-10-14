namespace SDS.Clould.WebAPI.DataModels
{
    using System;
    using System.Linq.Expressions;

    using SDS.Clould.Models;

    public class FinishedTransportDataModel
    {
        public static Expression<Func<Transport, FinishedTransportDataModel>> FromData
        {
            get
            {
                return t => new FinishedTransportDataModel
                {
                    Id = t.Id,
                    FromTown = t.FromTown,
                    ToTown = t.ToTown,
                    Arrival = t.Arrival,
                    DriverName = t.Driver.UserName,
                    DriverPhone = t.Driver.PhoneNumber
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