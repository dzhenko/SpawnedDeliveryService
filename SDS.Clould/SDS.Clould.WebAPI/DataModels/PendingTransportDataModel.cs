namespace SDS.Clould.WebAPI.DataModels
{
    using System;
    using System.Linq;
    using System.Linq.Expressions;

    using SDS.Clould.Models;

    public class PendingTransportDataModel
    {
        public static Expression<Func<Transport, PendingTransportDataModel>> FromData
        {
            get
            {
                return t => new PendingTransportDataModel
                {
                    Id = t.Id,
                    Deadline = t.Arrival,
                    FromTown = t.FromTown,
                    ToTown = t.ToTown
                };
            }
        }

        public int Id { get; set; }

        public DateTime Deadline { get; set; }

        public string FromTown { get; set; }

        public string ToTown { get; set; }
    }
}
                