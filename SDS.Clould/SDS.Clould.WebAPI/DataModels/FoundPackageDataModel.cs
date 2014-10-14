namespace SDS.Clould.WebAPI.DataModels
{
    using System;
    using System.Linq.Expressions;

    using SDS.Clould.Models;

    public class FoundPackageDataModel
    {
        public static Expression<Func<Package, FoundPackageDataModel>> FromData
        {
            get
            {
                return p => new FoundPackageDataModel
                {
                    Id = p.Id,
                    Price = p.Price,
                    Space = p.Space,
                    Kilograms = p.Kilograms,
                    FromTown = p.FromTown,
                    ToTown = p.ToTown,
                    Deadline = p.Deadline
                };
            }
        }

        public int Id { get; set; }

        public decimal Price { get; set; }

        // in square meters
        public int Space { get; set; }

        public int Kilograms { get; set; }

        public string FromTown { get; set; }

        public string ToTown { get; set; }

        public DateTime Deadline { get; set; }
    }
}