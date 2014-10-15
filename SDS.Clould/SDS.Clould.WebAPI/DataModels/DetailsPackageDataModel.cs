namespace SDS.Clould.WebAPI.DataModels
{
    using System;
    using System.Linq;
    using System.Linq.Expressions;

    using SDS.Clould.Models;

    public class DetailsPackageDataModel
    {
        public static Expression<Func<Package, DetailsPackageDataModel>> FromData
        {
            get
            {
                return p => new DetailsPackageDataModel
                {
                    Id = p.Id,
                    TransportId = p.TransportId,
                    PictureURL = p.PictureURL == null ? ImageUpload.ImageUploader.DefaultUrl : p.PictureURL,
                    Price = p.Price,
                    Space = p.Space,
                    Kilograms = p.Kilograms,
                    FromTown = p.FromTown,
                    ToTown = p.ToTown,
                    Deadline = p.Deadline,
                    Notes = p.Notes,
                    OwnerName = p.Owner.UserName,
                    OwnerPhone = p.Owner.PhoneNumber
                };
            }
        }

        public int Id { get; set; }

        public int? TransportId { get; set; }

        public string PictureURL { get; set; }

        public decimal Price { get; set; }

        // in square meters
        public int Space { get; set; }

        public int Kilograms { get; set; }

        public string FromTown { get; set; }

        public string ToTown { get; set; }

        public DateTime Deadline { get; set; }

        public string Notes { get; set; }

        public string OwnerName { get; set; }

        public string OwnerPhone { get; set; }
    }
}