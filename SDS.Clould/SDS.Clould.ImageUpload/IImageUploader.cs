namespace SDS.Clould.ImageUpload
{
    public interface IImageUploader
    {
        string UrlFromBase64Image(string base64);
    }
}
