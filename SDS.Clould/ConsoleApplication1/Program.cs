using SDS.Clould.ImageUpload;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApplication1
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine(  new ImageUploader().UrlFromBase64Image());
        }
    }
}
