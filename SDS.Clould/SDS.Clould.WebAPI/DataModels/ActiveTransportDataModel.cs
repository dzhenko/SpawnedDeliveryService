namespace SDS.Clould.WebAPI.DataModels
{
    using System;
    using System.Linq;
    using System.Linq.Expressions;

    using SDS.Clould.Models;

    public class ActiveTransportDataModel
    {
        public static Expression<Func<Transport, ActiveTransportDataModel>> FromData
        {
            get
            {
                return t => new ActiveTransportDataModel
                {
                    Id = t.Id,
                    ArrivalDate = t.Arrival,
                    FromTown = t.FromTown,
                    ToTown = t.ToTown
                };
            }
        }

        public int Id { get; set; }

        public DateTime ArrivalDate { get; set; }

        public string FromTown { get; set; }

        public string ToTown { get; set; }
    }
}