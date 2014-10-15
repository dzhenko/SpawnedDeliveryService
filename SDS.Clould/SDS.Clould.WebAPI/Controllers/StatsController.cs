namespace SDS.Clould.WebAPI.Controllers
{
    using System;
    using System.Linq;
    using System.Web.Http;
    using Microsoft.AspNet.Identity;
    using SDS.Clould.Data;
    using SDS.Clould.WebAPI.Providers;
    using SDS.Clould.Models;
    using System.Collections.Generic;

    [AllowAnonymous]
    public class StatsController : BaseApiController
    {
        public StatsController(ISDSData data) : base(data)
        {
        }

        public StatsController() : this(new SDSData())
        {
        }

        /// <summary>
        /// Returns statistics about the system - all packages, finished/active transports and users count
        /// </summary>
        [HttpGet]
        public IHttpActionResult Get()
        {
            return this.Ok(new
            {
                UsersCount = this.Data.Users.All().Count(),
                TransportsCount = this.Data.Transports.All().Count(),
                PackagesCount = this.Data.Packages.All().Count()
            });
        }

        /// <summary>
        /// Returns all the available towns in Bulgaria
        /// </summary>
        [HttpGet]
        public IHttpActionResult Towns()
        {
            return this.Ok(TownsProvider.Towns);
        }

        /// <summary>
        /// "Ugly" Method used to seed data
        /// </summary>
        [HttpGet]
        public IHttpActionResult SeedData(string password)
        {
            if (this.Data.Packages.All().Any() || password != "qwertyok")
            {
                return this.BadRequest("Invalid password or Data in Db - use qwerty");
            }

            var imageUploader = new SDS.Clould.ImageUpload.ImageUploader();

            if (this.Data.Users.All().Count() < 3)
            {
                return this.BadRequest("You must register at least 3 users first");
            }

            var users = this.Data.Users.All().Take(3).Select(u => new { Id = u.Id, Name = u.UserName, Phone = u.PhoneNumber }).ToArray();

            var transportsNew = new List<Transport>()
            {
                new Transport()
                {
                    Arrival = DateTime.Now.AddDays(-2),
                    AvailableKilograms = 333,
                    AvailableSpace = 432,
                    Departure = DateTime.Now.AddDays(-3),
                    FromTown = "Sofia",
                    ToTown = "Burgas",
                    UserId = users[0].Id
                },
                new Transport()
                {
                    Arrival = DateTime.Now.AddDays(5),
                    AvailableKilograms = 228,
                    AvailableSpace = 341,
                    Departure = DateTime.Now.AddDays(2),
                    FromTown = "Sofia",
                    ToTown = "Burgas",
                    UserId = users[1].Id
                },
                new Transport()
                {
                    Arrival = DateTime.Now.AddDays(15),
                    AvailableKilograms = 428,
                    AvailableSpace = 541,
                    Departure = DateTime.Now.AddDays(12),
                    FromTown = "Plovdiv",
                    ToTown = "Pleven",
                    UserId = users[2].Id
                }
            };

            this.Data.Transports.Add(transportsNew[0]);
            this.Data.Transports.Add(transportsNew[1]);
            this.Data.Transports.Add(transportsNew[2]);
            this.Data.SaveChanges();

            var transports = this.Data.Transports.All().ToArray();

            var generatedCargo = 30;

            for (int i = 0; i < generatedCargo; i++)
            {
                Package package;
                if (i % 3 == 0) 
                {
                    package = new Package()
                    {
                        Deadline = DateTime.Now.AddDays(-1),
                        FromTown = "Sofia",
                        Kilograms = 7,
                        Notes = "Very precious cargo N " + i.ToString(),
                        Price = 13.43m,
                        Space = 3,
                        ToTown = "Burgas",
                        TransportId = transports[0].Id,
                        UserId = users[0].Id,
                        PictureURL = SDS.Clould.ImageUpload.ImageUploader.DefaultUrl
                    };
                }
                else if (i % 3 == 1)
                {
                    package = new Package()
                    {
                        Deadline = DateTime.Now.AddDays(6),
                        FromTown = "Sofia",
                        Kilograms = 7,
                        Notes = "Not so precious cargo N " + i.ToString(),
                        Price = 11.43m,
                        Space = 4,
                        ToTown = "Burgas",
                        TransportId = transports[1].Id,
                        UserId = users[1].Id,
                        PictureURL = SDS.Clould.ImageUpload.ImageUploader.DefaultUrl
                    };
                }
                else 
                {
                    package = new Package()
                    {
                        Deadline = DateTime.Now.AddDays(17),
                        FromTown = "Plovdiv",
                        Kilograms = 7,
                        Notes = "meh " + i.ToString(),
                        Price = 15.43m,
                        Space = 2,
                        ToTown = "Pleven",
                        TransportId = transports[2].Id,
                        UserId = users[2].Id,
                        PictureURL = SDS.Clould.ImageUpload.ImageUploader.DefaultUrl
                    };
                }

                this.Data.Packages.Add(package);
                this.Data.SaveChanges();
            }

            return this.Created("", new
            {
                GeneratedCargoCount= generatedCargo,
                GeneratedTransportsCount = transportsNew.Count
            });
        }
    }
}