namespace SDS.Clould.WebAPI.DataModels
{
    using System.ComponentModel.DataAnnotations;

    public class PhoneDataModel
    {
        [Required]
        public string Phone { get; set; }
    }
}