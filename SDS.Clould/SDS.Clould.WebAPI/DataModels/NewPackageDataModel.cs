namespace SDS.Clould.WebAPI.DataModels
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.Linq;

    using SDS.Clould.Models;

    public class NewPackageDataModel
    {
                                                                                   // taken from the model or passed
        public static Package FromModel(NewPackageDataModel model, string ownerId, string pictureUrl)
        {
            return new Package()
            {
                Deadline = model.Deadline,
                FromTown = model.FromTown,
                Kilograms = model.Kilograms,
                Notes = model.Notes,
                Price = model.Price,
                Space = model.Space,
                ToTown = model.ToTown,
                UserId = ownerId,
                PictureURL = pictureUrl
            };
        }

        [Required]
        public string Picture { get; set; }

        public decimal Price { get; set; }

        // in square meters
        public int Space { get; set; }

        public int Kilograms { get; set; }

        [Required]
        public string FromTown { get; set; }

        [Required]
        public string ToTown { get; set; }

        public DateTime Deadline { get; set; }

        public string Notes { get; set; }
    }
}