using System;
using System.Linq.Expressions;
using SDS.Clould.Models;

namespace SDS.Clould.WebAPI.DataModels
{
    public class CoordinatesDataModel
    {
        public static Expression<Func<User, CoordinatesDataModel>> FromData
        {
            get
            {
                return u => new CoordinatesDataModel()
                {
                    Latitude = u.Latitude,
                    Longitude = u.Longitude
                };
            }
        }

        public double Longitude { get; set; }

        public double Latitude { get; set; }
    }
}