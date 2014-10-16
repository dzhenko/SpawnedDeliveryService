namespace SDS.Clould.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;

    public class Transport
    {
        private ICollection<Package> packages;

        public Transport()
        {
            this.packages = new HashSet<Package>();
        }

        public int Id { get; set; }

        [Required]
        public string FromTown { get; set; }

        [Required]
        public string ToTown { get; set; }

        // in square meters
        public int AvailableSpace { get; set; }

        public int AvailableKilograms { get; set; }

        public DateTime Departure { get; set; }

        public DateTime Arrival { get; set; }

        [Required]
        public string UserId { get; set; }

        [ForeignKey("UserId")]
        public virtual User Driver { get; set; }

        public string AdditionalContact { get; set; }

        public virtual ICollection<Package> Packages
        {
            get
            {
                return this.packages;
            }
            set
            {
                this.packages = value;
            }
        }
    }
}
