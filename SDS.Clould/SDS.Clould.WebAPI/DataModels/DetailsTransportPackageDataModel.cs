namespace SDS.Clould.WebAPI.DataModels
{
    using System;
    using System.Linq;
    using System.Linq.Expressions;

    using SDS.Clould.Models;

    public class DetailsTransportPackageDataModel
    {
        public static Expression<Func<Package, DetailsTransportPackageDataModel>> FromData
        {
            get
            {
                return p => new DetailsTransportPackageDataModel
                {
                    Id = p.Id,
                    Price = p.Price,
                    Space = p.Space,
                    Kilograms = p.Kilograms,
                    Deadline = p.Deadline,
                    OwnerName = p.Owner.UserName,
                    OwnerPhone = p.Owner.PhoneNumber,
                    AdditionalContact = p.AdditionalContact
                };
            }
        }

        public int Id { get; set; }

        public decimal Price { get; set; }

        public int Space { get; set; }

        public int Kilograms { get; set; }

        public DateTime Deadline { get; set; }

        public string OwnerName { get; set; }

        public string OwnerPhone { get; set; }

        public string AdditionalContact { get; set; }
    }
}