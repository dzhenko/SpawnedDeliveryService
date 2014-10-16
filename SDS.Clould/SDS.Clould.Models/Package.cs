namespace SDS.Clould.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;

    public class Package
    {
        public int Id { get; set; }

        public int? TransportId { get; set; }

        public virtual Transport Transport { get; set; }

        [Required]
        public string PictureURL { get; set; }

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

        [Required]
        public string UserId { get; set; }

        [ForeignKey("UserId")]
        public virtual User Owner { get; set; }

        public string AdditionalContact { get; set; }
    }
}
