﻿namespace SDS.Clould.WebAPI.Providers
{
    public class TownsProvider
    {
        private static string[] allTowns = new string[]
        {
            "Aheloy",
            "Ahtopol",
            "Aksakovo",
            "Alfatar",
            "Antonovo",
            "Apriltsi",
            "Ardino",
            "Asenovgrad",
            "Aytos",
            "Balchik",
            "Balgarovo",
            "Bankya",
            "Bansko",
            "Banya",
            "Batak",
            "Batanovtsi",
            "Belene",
            "Belitsa",
            "Belogradchik",
            "Beloslav",
            "Belovo",
            "Berkovitsa",
            "Blagoevgrad",
            "Boboshevo",
            "Bobov Dol",
            "Bolyarovo",
            "Borovo",
            "Botevgrad",
            "Boychinovtsi",
            "Bozhurishte",
            "Bratsigovo",
            "Bregovo",
            "Breznik",
            "Brezovo",
            "Brusartsi",
            "Buhovo",
            "Burgas",
            "Byala",
            "Byala Cherkva",
            "Byala Slatina",
            "Chepelare",
            "Chernomorets",
            "Cherven Bryag",
            "Chiprovtsi",
            "Chirpan",
            "Dalgopol",
            "Debelets",
            "Devin",
            "Devnya",
            "Dimitrovgrad",
            "Dimovo",
            "Dobrich",
            "Dobrinishte",
            "Dolna Banya",
            "Dolna Mitropoliya",
            "Dolna Oryahovitsa",
            "Dolni Chiflik",
            "Dolni Dabnik",
            "Dospat",
            "Dragoman",
            "Dryanovo",
            "Dulovo",
            "Dunavtsi",
            "Dupnitsa",
            "Dve Mogili",
            "Dzhebel",
            "Elena",
            "Elhovo",
            "Elin Pelin",
            "Etropole",
            "Gabrovo",
            "Galabovo",
            "General Toshevo",
            "Glavinitsa",
            "Glodzhevo",
            "Godech",
            "Gorna Oryahovitsa",
            "Gotse Delchev",
            "Gramada",
            "Gulyantsi",
            "Gurkovo",
            "Hadzhidimovo",
            "Harmanli",
            "Haskovo",
            "Hisarya",
            "Ignatievo",
            "Ihtiman",
            "Iskar",
            "Isperih",
            "Ivaylovgrad",
            "Kableshkovo",
            "Kalofer",
            "Kameno",
            "Kaolinovo",
            "Kardzhali",
            "Karlovo",
            "Karnobat",
            "Kaspichan",
            "Kavarna",
            "Kazanlak",
            "Kermen",
            "Kilifarevo",
            "Kiten",
            "Klisura",
            "Knezha",
            "Kocherinovo",
            "Koprivshtitsa",
            "Kostandovo",
            "Kostenets",
            "Kostinbrod",
            "Kotel",
            "Koynare",
            "Kozloduy",
            "Kran",
            "Kresna",
            "Krichim",
            "Krivodol",
            "Krumovgrad",
            "Kubrat",
            "Kuklen",
            "Kula",
            "Kyustendil",
            "Laki",
            "Letnitsa",
            "Levski",
            "Lom",
            "Lovech",
            "Loznitsa",
            "Lukovit",
            "Lyaskovets",
            "Lyubimets",
            "Madan",
            "Madzharovo",
            "Maglizh",
            "Malko Tarnovo",
            "Marten",
            "Melnik",
            "Merichleri",
            "Mezdra",
            "Miziya",
            "Momchilgrad",
            "Momin Prohod",
            "Montana",
            "Nedelino",
            "Nesebar",
            "Nikolaevo",
            "Nikopol",
            "Nova Zagora",
            "Novi Iskar",
            "Novi Pazar",
            "Obzor",
            "Omurtag",
            "Opaka",
            "Oryahovo",
            "Panagyurishte",
            "Parvomay",
            "Pavel Banya",
            "Pavlikeni",
            "Pazardzhik",
            "Pernik",
            "Perushtitsa",
            "Peshtera",
            "Petrich",
            "Pirdop",
            "Plachkovtsi",
            "Pleven",
            "Pliska",
            "Plovdiv",
            "Polski Trambesh",
            "Pomorie",
            "Popovo",
            "Pordim",
            "Pravets",
            "Primorsko",
            "Provadiya",
            "Radnevo",
            "Radomir",
            "Rakitovo",
            "Rakovski",
            "Razgrad",
            "Razlog",
            "Rila",
            "Roman",
            "Rudozem",
            "Ruse",
            "Sadovo",
            "Saedinenie",
            "Samokov",
            "Sandanski",
            "Sapareva Banya",
            "Sarnitsa",
            "Senovo",
            "Septemvri",
            "Sevlievo",
            "Shabla",
            "Shipka",
            "Shivachevo",
            "Shumen",
            "Silistra",
            "Simeonovgrad",
            "Simitli",
            "Slavyanovo",
            "Sliven",
            "Slivnitsa",
            "Slivo Pole",
            "Smolyan",
            "Smyadovo",
            "Sofia",
            "Sopot",
            "Sozopol",
            "Sredets",
            "Stamboliyski",
            "Stara Zagora",
            "Straldzha",
            "Strazhitsa",
            "Strelcha",
            "Suhindol",
            "Sungurlare",
            "Suvorovo",
            "Sveti Vlas",
            "Svilengrad",
            "Svishtov",
            "Svoge",
            "Targovishte",
            "Tervel",
            "Teteven",
            "Topolovgrad",
            "Tran",
            "Trastenik",
            "Troyan",
            "Tryavna",
            "Tsar Kaloyan",
            "Tsarevo",
            "Tutrakan",
            "Tvarditsa",
            "Ugarchin",
            "Valchedram",
            "Valchi Dol",
            "Varbitsa",
            "Varna",
            "Varshets",
            "Veliki Preslav",
            "Veliko Tarnovo",
            "Velingrad",
            "Vetovo",
            "Vetren",
            "Vidin",
            "Vratsa",
            "Yablanitsa",
            "Yakoruda",
            "Yambol",
            "Zavet",
            "Zemen",
            "Zlataritsa",
            "Zlatitsa",
            "Zlatograd"
        };

        public static string[] Towns
        {
            get
            {
                return allTowns;
            }
        }
    }
}