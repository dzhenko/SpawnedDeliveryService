﻿namespace SDS.Clould.WebAPI.DataModels
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.Linq;

    using SDS.Clould.Models;

    public class NewTransportDataModel
    {
        public static Transport FromModel(NewTransportDataModel model, string driverId)
        {
            return new Transport()
            {
                 Arrival = model.Arrival,
                 AvailableKilograms = model.AvailableKilograms,
                 AvailableSpace = model.AvailableSpace,
                 Departure = model.Departure,
                 FromTown = model.FromTown,
                 ToTown = model.ToTown,
                 UserId = driverId,
                 AdditionalContact = model.AdditionalContact
            };
        }

        [Required]
        public string FromTown { get; set; }

        [Required]
        public string ToTown { get; set; }

        public int AvailableSpace { get; set; }

        public int AvailableKilograms { get; set; }

        public DateTime Arrival { get; set; }

        public DateTime Departure { get; set; }

        public string DriverName { get; set; }

        public string DriverPhone { get; set; }

        [Required]
        public string AdditionalContact { get; set; }
    }
}