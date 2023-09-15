package sn.ept.git.dic2.projetjeemobile;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;


import sn.ept.git.dic2.projetjeemobile.entities.Categorie;
import sn.ept.git.dic2.projetjeemobile.entities.Magasin;
import sn.ept.git.dic2.projetjeemobile.entities.Marque;
import sn.ept.git.dic2.projetjeemobile.entities.Produit;
import sn.ept.git.dic2.projetjeemobile.entities.Stock;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "bicycle.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_STOCK = "stock";
    private static final String COLUMN_QUANTITE = "quantite";
    private static final String COLUMN_PRODUIT_ID = "produit_id";
    private static final String COLUMN_MAGASIN_ID = "magasin_id";
    private static final String INSERT_STOCKS = "INSERT INTO stock (MAGASIN_ID,PRODUIT_ID,QUANTITE) VALUES (1,1,27),(1,2,5),(1,3,6),(1,4,23),(1,5,22),(1,6,0),(1,8,0),(1,9,11),(1,10,15),(1,11,8),(1,12,16),(1,13,13),(1,14,8),(1,15,3),(1,16,4),(1,17,2),(1,18,16),(1,19,4),(1,20,26),(1,21,24),(1,22,29),(1,23,9),(1,24,10),(1,25,10),(1,26,16),(1,27,21),(1,28,20),(1,29,13),(1,30,30),(1,31,2),(1,32,0),(1,33,10),(1,34,2),(1,35,18),(1,36,26),(1,37,12),(1,38,13),(1,39,2),(1,40,24),(1,41,10),(1,42,0),(1,43,2),(1,44,1),(1,45,15),(1,46,19),(1,47,21),(1,48,5),(1,49,8),(1,50,29),(1,51,2),(1,52,18),(1,53,17),(1,54,11),(1,55,11),(1,56,15),(1,57,1),(1,58,1),(1,59,22),(1,60,19),(1,61,30),(1,62,21),(1,63,4),(1,64,30),(1,65,10),(1,66,12),(1,67,19),(1,68,30),(1,69,4),(1,70,3),(1,71,25),(1,72,9),(1,73,7),(1,74,9),(1,75,23),(1,76,15),(1,77,16),(1,78,13),(1,79,13),(1,80,11),(1,81,25),(1,82,1),(1,83,13),(1,84,11),(1,85,23),(1,86,19),(1,87,27),(1,88,7),(1,89,9),(1,90,4),(1,91,8),(1,92,0),(1,93,22),(1,94,6),(1,95,25),(1,96,20),(1,97,20),(1,98,28),(1,99,28),(1,100,15),(1,101,17),(1,102,9),(1,103,13),(1,104,25),(1,105,7),(1,106,30),(1,107,20),(1,108,15),(1,109,30),(1,110,1),(1,111,11),(1,112,17),(1,113,24),(1,114,3),(1,115,10),(1,116,24),(1,117,15),(1,118,12),(1,119,17),(1,120,23),(1,121,19),(1,122,22),(1,123,8),(1,124,23),(1,125,22),(1,126,28),(1,127,12),(1,128,11),(1,129,21),(1,130,22),(1,131,20),(1,132,1),(1,133,9),(1,134,26),(1,135,15),(1,136,12),(1,137,12),(1,138,6),(1,139,17),(1,140,3),(1,141,21),(1,142,22),(1,143,19),(1,144,7),(1,145,5),(1,146,6),(1,147,8),(1,148,6),(1,149,1),(1,150,22),(1,151,24),(1,152,12),(1,153,25),(1,154,13),(1,155,8),(1,156,13),(1,157,25),(1,158,11),(1,159,18),(1,160,0),(1,161,17),(1,162,14),(1,163,0),(1,164,15),(1,165,16),(1,166,23),(1,167,28),(1,168,0),(1,169,25),(1,170,14),(1,171,3),(1,172,3),(1,173,2),(1,174,26),(1,175,2),(1,176,11),(1,177,19),(1,178,23),(1,179,16),(1,180,5),(1,181,19),(1,182,28),(1,183,11),(1,184,4),(1,185,3),(1,186,1),(1,187,16),(1,188,30),(1,189,6),(1,190,21),(1,191,21),(1,192,20),(1,193,30),(1,194,24),(1,195,1),(1,196,26),(1,197,22),(1,198,1),(1,199,2),(1,200,27),(1,201,16),(1,202,5),(1,203,4),(1,204,23),(1,205,9),(1,206,10),(1,207,27),(1,208,13),(1,209,22),(1,210,25),(1,211,13),(1,212,17),(1,213,14),(1,214,4),(1,215,1),(1,216,11),(1,217,11),(1,218,27),(1,219,30),(1,220,4),(1,221,7),(1,222,12),(1,223,3),(1,224,16),(1,225,24),(1,226,18),(1,227,19),(1,228,22),(1,229,27),(1,230,7),(1,231,25),(1,232,5),(1,233,5),(1,234,13),(1,235,7),(1,236,18),(1,237,26),(1,238,20),(1,239,28),(1,240,17),(1,241,17),(1,242,10),(1,243,11),(1,244,10),(1,245,3),(1,246,0),(1,247,15),(1,248,14),(1,249,27),(1,250,3),(1,251,2),(1,252,28),(1,253,16),(1,254,24),(1,255,13),(1,256,20),(1,257,17),(1,258,25),(1,259,10),(1,260,20),(1,261,3),(1,262,6),(1,263,21),(1,264,16),(1,265,28),(1,266,1),(1,267,27),(1,268,23),(1,269,22),(1,270,16),(1,271,6),(1,272,14),(1,273,20),(1,274,19),(1,275,28),(1,276,7),(1,277,13),(1,278,17),(1,279,17),(1,280,23),(1,281,11),(1,282,6),(1,283,10),(1,284,27),(1,285,20),(1,286,28),(1,287,2),(1,288,9),(1,289,2),(1,290,8),(1,291,9),(1,292,30),(1,293,18),(1,294,3),(1,295,24),(1,296,9),(1,297,22),(1,298,26),(1,299,18),(1,300,7),(1,301,10),(1,302,0),(1,303,12),(1,304,15),(1,305,23),(1,306,21),(1,307,17),(1,308,23),(1,309,7),(1,310,18),(1,311,20),(1,312,23),(1,313,14),(2,1,14),(2,2,16),(2,3,28),(2,4,2),(2,5,1),(2,6,11),(2,7,8),(2,8,1),(2,9,17),(2,10,13),(2,11,21),(2,12,2),(2,13,1),(2,14,18),(2,15,6),(2,16,20),(2,17,3),(2,18,13),(2,19,15),(2,20,20),(2,21,16),(2,22,0),(2,23,12),(2,24,16),(2,25,18),(2,26,2),(2,27,10),(2,28,25),(2,29,29),(2,30,17),(2,31,10),(2,32,13),(2,33,8),(2,34,24),(2,35,25),(2,36,15),(2,37,5),(2,38,18),(2,39,28),(2,40,15),(2,41,6),(2,42,16),(2,43,2),(2,44,24),(2,45,13),(2,46,18),(2,47,0),(2,48,20),(2,49,4),(2,50,7),(2,51,8),(2,52,23),(2,53,19),(2,54,6),(2,55,5),(2,56,20),(2,57,27),(2,58,15),(2,59,17),(2,60,1),(2,61,20),(2,62,4),(2,63,5),(2,64,30),(2,65,16),(2,66,22),(2,67,8),(2,68,23),(2,69,22),(2,70,6),(2,71,30),(2,72,1),(2,73,20),(2,74,23),(2,75,11),(2,76,20),(2,77,25),(2,78,4),(2,79,29),(2,80,27),(2,81,19),(2,82,4),(2,83,21),(2,84,22),(2,85,6),(2,86,24),(2,87,14),(2,88,8),(2,89,8),(2,90,26),(2,91,0),(2,92,22),(2,93,22),(2,94,2),(2,95,10),(2,96,22),(2,97,15),(2,98,14),(2,99,24),(2,100,12),(2,101,4),(2,102,12),(2,103,1),(2,104,26),(2,105,3),(2,106,7),(2,107,29),(2,108,29),(2,109,25),(2,110,7),(2,111,6),(2,112,1),(2,113,7),(2,114,12),(2,115,19),(2,116,17),(2,117,18),(2,118,25),(2,119,23),(2,120,16),(2,121,3),(2,122,11),(2,123,18),(2,124,4),(2,125,10),(2,126,13),(2,127,12),(2,128,28),(2,129,21),(2,130,17),(2,131,9),(2,132,6),(2,133,28),(2,134,13),(2,135,30),(2,136,8),(2,137,1),(2,138,16),(2,139,24),(2,140,13),(2,141,28),(2,142,24),(2,143,12),(2,144,4),(2,145,23),(2,146,3),(2,147,13),(2,148,27),(2,149,22),(2,150,7),(2,151,2),(2,152,21),(2,153,21),(2,154,9),(2,155,21),(2,156,8),(2,157,28),(2,158,0),(2,159,30),(2,160,4),(2,161,9),(2,162,18),(2,163,30),(2,164,9),(2,165,6),(2,166,27),(2,167,1),(2,168,29),(2,169,1),(2,170,2),(2,171,28),(2,172,16),(2,173,17),(2,174,8),(2,175,0),(2,176,26),(2,177,9),(2,178,1),(2,179,16),(2,180,3),(2,181,29),(2,182,21),(2,183,26),(2,184,0),(2,185,4),(2,186,16),(2,187,10),(2,188,27),(2,189,13),(2,190,18),(2,191,15),(2,192,0),(2,193,21),(2,194,15),(2,195,19),(2,196,28),(2,197,10),(2,198,0),(2,199,13),(2,200,11),(2,201,24),(2,202,1),(2,203,7),(2,204,25),(2,205,13),(2,206,9),(2,207,8),(2,208,12),(2,209,11),(2,210,10),(2,211,4),(2,212,22),(2,213,10),(2,214,7),(2,215,11),(2,216,19),(2,217,22),(2,218,26),(2,219,16),(2,220,6),(2,221,12),(2,222,30),(2,223,7),(2,224,4),(2,225,11),(2,226,5),(2,227,12),(2,228,7),(2,229,29),(2,230,25),(2,231,8),(2,232,11),(2,233,25),(2,234,12),(2,235,4),(2,236,7),(2,237,30),(2,238,6),(2,239,17),(2,240,19),(2,241,24),(2,242,2),(2,243,18),(2,244,1),(2,245,18),(2,246,10),(2,247,16),(2,248,26),(2,249,10),(2,250,23),(2,251,0),(2,252,20),(2,253,29),(2,254,7),(2,255,12),(2,256,8),(2,257,5),(2,258,10),(2,259,16),(2,260,20),(2,261,4),(2,262,8),(2,263,4),(2,264,13),(2,265,4),(2,266,18),(2,267,21),(2,268,3),(2,269,4),(2,270,15),(2,271,26),(2,272,1),(2,273,28),(2,274,15),(2,275,3),(2,276,9),(2,277,4),(2,278,8),(2,279,18),(2,280,11),(2,281,3),(2,282,8),(2,283,28),(2,284,6),(2,285,22),(2,286,3),(2,287,15),(2,288,13),(2,289,5),(2,290,29),(2,291,22),(2,292,22),(2,293,12),(2,294,7),(2,295,15),(2,296,8),(2,297,7),(2,298,27),(2,299,0),(2,300,13),(2,301,26),(2,302,6),(2,303,23),(2,304,6),(2,305,21),(2,306,17),(2,307,18),(2,308,15),(2,309,9),(2,310,5),(2,311,27),(2,312,2),(2,313,24),(3,1,14),(3,2,24),(3,3,0),(3,4,11),(3,5,3),(3,6,27),(3,7,12),(3,8,12),(3,9,23),(3,10,21),(3,11,30),(3,12,30),(3,13,19),(3,14,4),(3,15,22),(3,16,19),(3,17,22),(3,18,5),(3,19,24),(3,20,19),(3,21,8),(3,22,20),(3,23,8),(3,24,18),(3,25,15),(3,26,27),(3,27,21),(3,28,20),(3,29,11),(3,30,23),(3,31,10),(3,32,14),(3,33,14),(3,34,6),(3,35,3),(3,36,28),(3,37,30),(3,38,23),(3,39,22),(3,40,2),(3,41,25),(3,42,9),(3,43,26),(3,44,26),(3,45,1),(3,46,16),(3,47,14),(3,48,2),(3,49,4),(3,50,25),(3,51,2),(3,52,8),(3,53,6),(3,54,13),(3,55,5),(3,56,19),(3,57,9),(3,58,27),(3,59,0),(3,60,7),(3,61,28),(3,62,4),(3,63,8),(3,64,22),(3,65,1),(3,66,3),(3,67,6),(3,68,18),(3,69,29),(3,70,2),(3,71,13),(3,72,6),(3,73,3),(3,74,17),(3,75,29),(3,76,23),(3,77,23),(3,78,19),(3,79,29),(3,80,22),(3,81,18),(3,82,7),(3,83,23),(3,84,15),(3,85,15),(3,86,27),(3,87,16),(3,88,28),(3,89,22),(3,90,9),(3,91,8),(3,92,28),(3,93,18),(3,94,15),(3,95,14),(3,96,1),(3,97,28),(3,98,5),(3,99,19),(3,100,28),(3,101,17),(3,102,22),(3,103,10),(3,104,22),(3,105,1),(3,106,1),(3,107,11),(3,108,23),(3,109,24),(3,110,11),(3,111,29),(3,112,24),(3,113,23),(3,114,12),(3,115,25),(3,116,30),(3,117,23),(3,118,9),(3,119,29),(3,120,25),(3,121,4),(3,122,8),(3,123,15),(3,124,11),(3,125,2),(3,126,5),(3,127,4),(3,128,20),(3,129,10),(3,130,20),(3,131,23),(3,132,16),(3,133,16),(3,134,17),(3,135,15),(3,136,4),(3,137,17),(3,138,17),(3,139,21),(3,140,3),(3,141,4),(3,142,29),(3,143,9),(3,144,9),(3,145,11),(3,146,9),(3,147,3),(3,148,6),(3,149,1),(3,150,21),(3,151,1),(3,152,1),(3,153,7),(3,154,22),(3,155,25),(3,156,14),(3,157,12),(3,158,17),(3,159,6),(3,160,27),(3,161,11),(3,162,11),(3,163,11),(3,164,18),(3,165,19),(3,166,27),(3,167,8),(3,168,23),(3,169,3),(3,170,13),(3,171,11),(3,172,22),(3,173,15),(3,174,1),(3,175,3),(3,176,6),(3,177,2),(3,178,12),(3,179,20),(3,180,6),(3,181,8),(3,182,28),(3,183,26),(3,184,21),(3,185,15),(3,186,30),(3,187,2),(3,188,29),(3,189,20),(3,190,20),(3,191,23),(3,192,10),(3,193,5),(3,194,20),(3,195,20),(3,196,25),(3,197,8),(3,198,27),(3,199,5),(3,200,29),(3,201,30),(3,202,11),(3,203,6),(3,204,16),(3,205,3),(3,206,29),(3,207,29),(3,208,12),(3,209,1),(3,210,22),(3,211,3),(3,212,23),(3,213,10),(3,214,30),(3,215,12),(3,216,5),(3,217,4),(3,218,21),(3,219,29),(3,220,0),(3,221,25),(3,222,12),(3,223,29),(3,224,23),(3,225,12),(3,226,14),(3,227,22),(3,228,29),(3,229,7),(3,230,29),(3,231,6),(3,232,16),(3,233,9),(3,234,24),(3,235,1),(3,236,3),(3,237,16),(3,238,12),(3,239,29),(3,240,26),(3,241,8),(3,242,5),(3,243,7),(3,244,5),(3,245,10),(3,246,7),(3,247,3),(3,248,11),(3,249,6),(3,250,16),(3,251,10),(3,252,27),(3,253,15),(3,254,4),(3,255,17),(3,256,1),(3,257,25),(3,258,10),(3,259,0),(3,260,5),(3,261,24),(3,262,29),(3,263,21),(3,264,7),(3,265,22),(3,266,18),(3,267,12),(3,268,17),(3,269,26),(3,270,6),(3,271,23),(3,272,12),(3,273,19),(3,274,7),(3,275,23),(3,276,14),(3,277,26),(3,278,15),(3,279,15),(3,280,1),(3,281,2),(3,282,18),(3,283,1),(3,284,26),(3,285,2),(3,286,7),(3,287,26),(3,288,2),(3,289,2),(3,290,6),(3,291,10),(3,292,16),(3,293,14),(3,294,13),(3,295,9),(3,296,12),(3,297,22),(3,298,14),(3,299,29),(3,300,3),(3,301,29),(3,302,6),(3,303,13),(3,304,23),(3,305,11),(3,306,7),(3,307,17),(3,308,9),(3,309,30),(3,310,8),(3,311,23),(3,312,18),(3,313,0);\n";


    private static final String CREATE_TABLE_STOCK = "CREATE TABLE " + TABLE_STOCK + "("
            + COLUMN_QUANTITE + " INTEGER,"
            + COLUMN_PRODUIT_ID + " INTEGER,"
            + COLUMN_MAGASIN_ID + " INTEGER,"
            + "PRIMARY KEY (" + COLUMN_PRODUIT_ID + ", " + COLUMN_MAGASIN_ID + "),"
            + "FOREIGN KEY (" + COLUMN_PRODUIT_ID + ") REFERENCES produit(ID) ON DELETE CASCADE,"
            + "FOREIGN KEY (" + COLUMN_MAGASIN_ID + ") REFERENCES magasin(ID))";

    private static final String INSERT_MARQUE = "INSERT INTO marque (ID,NOM) VALUES (1,'Electra'), (2,'Haro'), (3,'Heller'), (4,'Pure Cycles'), (5,'Ritchey'), (6,'Strider'), (7,'Sun Bicycles'), (8,'Surly'), (9,'Trek');\n";

    private static final String INSERT_CATEGORIE = "INSERT INTO categorie (ID,NOM)  VALUES (1,'Children Bicycles'), (2,'Comfort Bicycles'), (3,'Cruisers Bicycles'), (4,'Cyclocross Bicycles'), (5,'Electric Bikes'), (6,'Mountain Bikes'), (7,'Road Bikes');\n";

    private static final String INSERT_MAGASIN = "INSERT INTO magasin (ID,NOM,TELEPHONE,EMAIL,ADRESSE,VILLE,ETAT,CODEZIP) VALUES (1,'Santa Cruz Bikes','(831) 476-4321','santacruz@bikes.shop','3700 Portola Drive','Santa Cruz','CA','95060'),(2,'Baldwin Bikes','(516) 379-8888','baldwin@bikes.shop','4200 Chestnut Lane','Baldwin','NY','11432'),(3,'Rowlett Bikes','(972) 530-5555','rowlett@bikes.shop','8000 Fairway Avenue','Rowlett','TX','75088');\n";

    private static final String INSERT_PRODUIT = "INSERT INTO produit (ID,NOM,MARQUE_ID,CATEGORIE_ID,ANNEE_MODEL,PRIX_DEPART) VALUES (1,'Trek 820 - 2016',9,6,2016,379.99),(2,'Ritchey Timberwolf Frameset - 2016',5,6,2016,749.99),(3,'Surly Wednesday Frameset - 2016',8,6,2016,999.99),(4,'Trek Fuel EX 8 29 - 2016',9,6,2016,2899.99),(5,'Heller Shagamaw Frame - 2016',3,6,2016,1320.99),(6,'Surly Ice Cream Truck Frameset - 2016',8,6,2016,469.99),(7,'Trek Slash 8 27.5 - 2016',9,6,2016,3999.99),(8,'Trek Remedy 29 Carbon Frameset - 2016',9,6,2016,1799.99),(9,'Trek Conduit+ - 2016',9,5,2016,2999.99),(10,'Surly Straggler - 2016',8,4,2016,1549.00),(11,'Surly Straggler 650b - 2016',8,4,2016,1680.99),(12,'Electra Townie Original 21D - 2016',1,3,2016,549.99),(13,'Electra Cruiser 1 (24-Inch) - 2016',1,3,2016,269.99),(14,'Electra GirlHawaii 1 (16-inch) - 2015/2016',1,3,2016,269.99),(15,'Electra Moto 1 - 2016',1,3,2016,529.99),(16,'Electra Townie Original 7D EQ - 2016',1,3,2016,599.99),(17,'Pure Cycles Vine 8-Speed - 2016',4,3,2016,429.00),(18,'Pure Cycles Western 3-Speed - Women- 2015/2016',4,3,2016,449.00),(19,'Pure Cycles William 3-Speed - 2016',4,3,2016,449.00),(20,'Electra Townie Original 7D EQ - Women- 2016',1,3,2016,599.99),(21,'Electra Cruiser 1 (24-Inch) - 2016',1,1,2016,269.99),(22,'Electra GirlHawaii 1 (16-inch) - 2015/2016',1,1,2016,269.99),(23,'Electra GirlHawaii 1 (20-inch) - 2015/2016',1,1,2016,299.99),(24,'Electra Townie Original 21D - 2016',1,2,2016,549.99),(25,'Electra Townie Original 7D - 2015/2016',1,2,2016,499.99),(26,'Electra Townie Original 7D EQ - 2016',1,2,2016,599.99),(27,'Surly Big Dummy Frameset - 2017',8,6,2017,999.99),(28,'Surly Karate Monkey 27.5+ Frameset - 2017',8,6,2017,2499.99),(29,'Trek X-Caliber 8 - 2017',9,6,2017,999.99),(30,'Surly Ice Cream Truck Frameset - 2017',8,6,2017,999.99),(31,'Surly Wednesday - 2017',8,6,2017,1632.99),(32,'Trek Farley Alloy Frameset - 2017',9,6,2017,469.99),(33,'Surly Wednesday Frameset - 2017',8,6,2017,469.99),(34,'Trek Session DH 27.5 Carbon Frameset - 2017',9,6,2017,469.99),(35,'Sun Bicycles Spider 3i - 2017',7,6,2017,832.99),(36,'Surly Troll Frameset - 2017',8,6,2017,832.99),(37,'Haro Flightline One ST - 2017',2,6,2017,379.99),(38,'Haro Flightline Two 26 Plus - 2017',2,6,2017,549.99),(39,'Trek Stache 5 - 2017',9,6,2017,1499.99),(40,'Trek Fuel EX 9.8 29 - 2017',9,6,2017,4999.99),(41,'Haro Shift R3 - 2017',2,6,2017,1469.99),(42,'Trek Fuel EX 5 27.5 Plus - 2017',9,6,2017,2299.99),(43,'Trek Fuel EX 9.8 27.5 Plus - 2017',9,6,2017,5299.99),(44,'Haro SR 1.1 - 2017',2,6,2017,539.99),(45,'Haro SR 1.2 - 2017',2,6,2017,869.99),(46,'Haro SR 1.3 - 2017',2,6,2017,1409.99),(47,'Trek Remedy 9.8 - 2017',9,6,2017,5299.99),(48,'Trek Emonda S 4 - 2017',9,7,2017,1499.99),(49,'Trek Domane SL 6 - 2017',9,7,2017,3499.99),(50,'Trek Silque SLR 7 Women- 2017',9,7,2017,5999.99),(51,'Trek Silque SLR 8 Women- 2017',9,7,2017,6499.99),(52,'Surly Steamroller - 2017',8,7,2017,875.99),(53,'Surly Ogre Frameset - 2017',8,7,2017,749.99),(54,'Trek Domane SL Disc Frameset - 2017',9,7,2017,3199.99),(55,'Trek Domane S 6 - 2017',9,7,2017,2699.99),(56,'Trek Domane SLR 6 Disc - 2017',9,7,2017,5499.99),(57,'Trek Emonda S 5 - 2017',9,7,2017,1999.99),(58,'Trek Madone 9.2 - 2017',9,7,2017,4999.99),(59,'Trek Domane S 5 Disc - 2017',9,7,2017,2599.99),(60,'Sun Bicycles ElectroLite - 2017',7,5,2017,1559.99),(61,'Trek Powerfly 8 FS Plus - 2017',9,5,2017,4999.99),(62,'Trek Boone 7 - 2017',9,4,2017,3499.99),(63,'Trek Boone Race Shop Limited - 2017',9,4,2017,3499.99),(64,'Electra Townie Original 7D - 2017',1,3,2017,489.99),(65,'Sun Bicycles Lil Bolt Type-R - 2017',7,3,2017,346.99),(66,'Sun Bicycles Revolutions 24 - 2017',7,3,2017,250.99),(67,'Sun Bicycles Revolutions 24 - Girl- 2017',7,3,2017,250.99),(68,'Sun Bicycles Cruz 3 - 2017',7,3,2017,449.99),(69,'Sun Bicycles Cruz 7 - 2017',7,3,2017,416.99),(70,'Electra Amsterdam Original 3i - 2015/2017',1,3,2017,659.99),(71,'Sun Bicycles Atlas X-Type - 2017',7,3,2017,416.99),(72,'Sun Bicycles Biscayne Tandem 7 - 2017',7,3,2017,619.99),(73,'Sun Bicycles Brickell Tandem 7 - 2017',7,3,2017,749.99),(74,'Electra Cruiser Lux 1 - 2017',1,3,2017,439.99),(75,'Electra Cruiser Lux Fat Tire 1 Ladies - 2017',1,3,2017,599.99),(76,'Electra GirlHawaii 1 16\" - 2017',1,3,2017,299.99),(77,'Electra Glam Punk 3i Ladies - 2017',1,3,2017,799.99),(78,'Sun Bicycles Biscayne Tandem CB - 2017',7,3,2017,647.99),(79,'Sun Bicycles Boardwalk (24-inch Wheels) - 2017',7,3,2017,402.99),(80,'Sun Bicycles Brickell Tandem CB - 2017',7,3,2017,761.99),(81,'Electra Amsterdam Fashion 7i Ladies - 2017',1,3,2017,1099.99),(82,'Electra Amsterdam Original 3i Ladies - 2017',1,3,2017,659.99),(83,'Trek BoyKickster - 2015/2017',9,1,2017,149.99),(84,'Sun Bicycles Lil Kittn - 2017',7,1,2017,109.99),(85,'Haro Downtown 16 - 2017',2,1,2017,329.99),(86,'Trek GirlKickster - 2017',9,1,2017,149.99),(87,'Trek Precaliber 12 Boys - 2017',9,1,2017,189.99),(88,'Trek Precaliber 12 Girls - 2017',9,1,2017,189.99),(89,'Trek Precaliber 16 Boys - 2017',9,1,2017,209.99),(90,'Trek Precaliber 16 Girls - 2017',9,1,2017,209.99),(91,'Trek Precaliber 24 (21-Speed) - Girls - 2017',9,1,2017,349.99),(92,'Haro Shredder 20 - 2017',2,1,2017,209.99),(93,'Haro Shredder 20 Girls - 2017',2,1,2017,209.99),(94,'Haro Shredder Pro 20 - 2017',2,1,2017,249.99),(95,'Electra GirlHawaii 1 16\" - 2017',1,1,2017,299.99),(96,'Electra Moto 3i (20-inch) - Boy- 2017',1,1,2017,349.99),(97,'Electra Savannah 3i (20-inch) - Girl- 2017',1,1,2017,349.99),(98,'Electra Straight 8 3i (20-inch) - Boy- 2017',1,1,2017,489.99),(99,'Electra Sugar Skulls 1 (20-inch) - Girl- 2017',1,1,2017,299.99),(100,'Electra Townie 3i EQ (20-inch) - Boys - 2017',1,1,2017,489.99),(101,'Electra Townie 7D (20-inch) - Boys - 2017',1,1,2017,339.99),(102,'Electra Townie Original 7D - 2017',1,2,2017,489.99),(103,'Sun Bicycles Streamway 3 - 2017',7,2,2017,551.99),(104,'Sun Bicycles Streamway - 2017',7,2,2017,481.99),(105,'Sun Bicycles Streamway 7 - 2017',7,2,2017,533.99),(106,'Sun Bicycles Cruz 3 - 2017',7,2,2017,449.99),(107,'Sun Bicycles Cruz 7 - 2017',7,2,2017,416.99),(108,'Sun Bicycles Cruz 3 - Women- 2017',7,2,2017,449.99),(109,'Sun Bicycles Cruz 7 - Women- 2017',7,2,2017,416.99),(110,'Sun Bicycles Drifter 7 - 2017',7,2,2017,470.99),(111,'Sun Bicycles Drifter 7 - Women- 2017',7,2,2017,470.99),(112,'Trek 820 - 2018',9,6,2018,379.99),(113,'Trek Marlin 5 - 2018',9,6,2018,489.99),(114,'Trek Marlin 6 - 2018',9,6,2018,579.99),(115,'Trek Fuel EX 8 29 - 2018',9,6,2018,3199.99),(116,'Trek Marlin 7 - 2017/2018',9,6,2018,749.99),(117,'Trek Ticket S Frame - 2018',9,6,2018,1469.99),(118,'Trek X-Caliber 8 - 2018',9,6,2018,999.99),(119,'Trek Kids Neko - 2018',9,6,2018,469.99),(120,'Trek Fuel EX 7 29 - 2018',9,6,2018,2499.99),(121,'Surly Krampus Frameset - 2018',8,6,2018,2499.99),(122,'Surly Troll Frameset - 2018',8,6,2018,2499.99),(123,'Trek Farley Carbon Frameset - 2018',9,6,2018,999.99),(124,'Surly Krampus - 2018',8,6,2018,1499.00),(125,'Trek Kids Dual Sport - 2018',9,6,2018,469.99),(126,'Surly Big Fat Dummy Frameset - 2018',8,6,2018,469.99),(127,'Surly Pack Rat Frameset - 2018',8,6,2018,469.99),(128,'Surly ECR 27.5 - 2018',8,6,2018,1899.00),(129,'Trek X-Caliber 7 - 2018',9,6,2018,919.99),(130,'Trek Stache Carbon Frameset - 2018',9,6,2018,919.99),(131,'Heller Bloodhound Trail - 2018',3,6,2018,2599.00),(132,'Trek Procal AL Frameset - 2018',9,6,2018,1499.99),(133,'Trek Procaliber Frameset - 2018',9,6,2018,1499.99),(134,'Trek Remedy 27.5 C Frameset - 2018',9,6,2018,1499.99),(135,'Trek X-Caliber Frameset - 2018',9,6,2018,1499.99),(136,'Trek Procaliber 6 - 2018',9,6,2018,1799.99),(137,'Heller Shagamaw GX1 - 2018',3,6,2018,2599.00),(138,'Trek Fuel EX 5 Plus - 2018',9,6,2018,2249.99),(139,'Trek Remedy 7 27.5 - 2018',9,6,2018,2999.99),(140,'Trek Remedy 9.8 27.5 - 2018',9,6,2018,4999.99),(141,'Trek Stache 5 - 2018',9,6,2018,1599.99),(142,'Trek Fuel EX 8 29 XT - 2018',9,6,2018,3199.99),(143,'Trek Domane ALR 3 - 2018',9,7,2018,1099.99),(144,'Trek Domane ALR 4 Disc - 2018',9,7,2018,1549.99),(145,'Trek Domane ALR 5 Disc - 2018',9,7,2018,1799.99),(146,'Trek Domane SLR 6 - 2018',9,7,2018,4999.99),(147,'Trek Domane ALR 5 Gravel - 2018',9,7,2018,1799.99),(148,'Trek Domane SL 8 Disc - 2018',9,7,2018,5499.99),(149,'Trek Domane SLR 8 Disc - 2018',9,7,2018,7499.99),(150,'Trek Emonda SL 7 - 2018',9,7,2018,4499.99),(151,'Trek Domane ALR 4 Disc Women- 2018',9,7,2018,1549.99),(152,'Trek Domane SL 5 Disc Women- 2018',9,7,2018,2499.99),(153,'Trek Domane SL 7 Women- 2018',9,7,2018,4999.99),(154,'Trek Domane SLR 6 Disc Women- 2018',9,7,2018,5499.99),(155,'Trek Domane SLR 9 Disc - 2018',9,7,2018,11999.99),(156,'Trek Domane SL Frameset - 2018',9,7,2018,6499.99),(157,'Trek Domane SL Frameset Women- 2018',9,7,2018,6499.99),(158,'Trek CrossRip 1 - 2018',9,7,2018,959.99),(159,'Trek Emonda ALR 6 - 2018',9,7,2018,2299.99),(160,'Trek Emonda SLR 6 - 2018',9,7,2018,4499.99),(161,'Surly ECR - 2018',8,7,2018,1899.00),(162,'Trek Emonda SL 6 Disc - 2018',9,7,2018,2999.99),(163,'Surly Pack Rat - 2018',8,7,2018,1349.00),(164,'Surly Straggler 650b - 2018',8,7,2018,1549.00),(165,'Trek 1120 - 2018',9,7,2018,2499.99),(166,'Trek Domane AL 2 Women- 2018',9,7,2018,749.99),(167,'Surly ECR Frameset - 2018',8,7,2018,749.99),(168,'Surly Straggler - 2018',8,7,2018,1549.00),(169,'Trek Emonda SLR 8 - 2018',9,7,2018,6499.99),(170,'Trek CrossRip 2 - 2018',9,7,2018,1299.99),(171,'Trek Domane SL 6 - 2018',9,7,2018,3199.99),(172,'Trek Domane ALR Disc Frameset - 2018',9,7,2018,3199.99),(173,'Trek Domane ALR Frameset - 2018',9,7,2018,3199.99),(174,'Trek Domane SLR Disc Frameset - 2018',9,7,2018,3199.99),(175,'Trek Domane SLR Frameset - 2018',9,7,2018,3199.99),(176,'Trek Madone 9 Frameset - 2018',9,7,2018,3199.99),(177,'Trek Domane SLR 6 Disc - 2018',9,7,2018,5499.99),(178,'Trek Domane AL 2 - 2018',9,7,2018,749.99),(179,'Trek Domane AL 3 - 2018',9,7,2018,919.99),(180,'Trek Domane AL 3 Women- 2018',9,7,2018,919.99),(181,'Trek Domane SL 5 - 2018',9,7,2018,2199.99),(182,'Trek Domane SL 5 Disc - 2018',9,7,2018,2499.99),(183,'Trek Domane SL 5 Women- 2018',9,7,2018,2199.99),(184,'Trek Domane SL 6 Disc - 2018',9,7,2018,3499.99),(185,'Trek Conduit+ - 2018',9,5,2018,2799.99),(186,'Trek CrossRip+ - 2018',9,5,2018,4499.99),(187,'Trek Neko+ - 2018',9,5,2018,2799.99),(188,'Trek XM700+ Lowstep - 2018',9,5,2018,3499.99),(189,'Trek Lift+ Lowstep - 2018',9,5,2018,2799.99),(190,'Trek Dual Sport+ - 2018',9,5,2018,2799.99),(191,'Electra Loft Go! 8i - 2018',1,5,2018,2799.99),(192,'Electra Townie Go! 8i - 2017/2018',1,5,2018,2599.99),(193,'Trek Lift+ - 2018',9,5,2018,2799.99),(194,'Trek XM700+ - 2018',9,5,2018,3499.99),(195,'Electra Townie Go! 8i Ladies - 2018',1,5,2018,2599.99),(196,'Trek Verve+ - 2018',9,5,2018,2299.99),(197,'Trek Verve+ Lowstep - 2018',9,5,2018,2299.99),(198,'Electra Townie Commute Go! - 2018',1,5,2018,2999.99),(199,'Electra Townie Commute Go! Ladies - 2018',1,5,2018,2999.99),(200,'Trek Powerfly 5 - 2018',9,5,2018,3499.99),(201,'Trek Powerfly 5 FS - 2018',9,5,2018,4499.99),(202,'Trek Powerfly 5 Women- 2018',9,5,2018,3499.99),(203,'Trek Powerfly 7 FS - 2018',9,5,2018,4999.99),(204,'Trek Super Commuter+ 7 - 2018',9,5,2018,3599.99),(205,'Trek Super Commuter+ 8S - 2018',9,5,2018,4999.99),(206,'Trek Boone 5 Disc - 2018',9,4,2018,3299.99),(207,'Trek Boone 7 Disc - 2018',9,4,2018,3999.99),(208,'Trek Crockett 5 Disc - 2018',9,4,2018,1799.99),(209,'Trek Crockett 7 Disc - 2018',9,4,2018,2999.99),(210,'Surly Straggler - 2018',8,4,2018,1549.00),(211,'Surly Straggler 650b - 2018',8,4,2018,1549.00),(212,'Electra Townie Original 21D - 2018',1,3,2018,559.99),(213,'Electra Cruiser 1 - 2016/2017/2018',1,3,2018,269.99),(214,'Electra Tiger Shark 3i - 2018',1,3,2018,899.99),(215,'Electra Queen of Hearts 3i - 2018',1,3,2018,749.99),(216,'Electra Super Moto 8i - 2018',1,3,2018,899.99),(217,'Electra Straight 8 3i - 2018',1,3,2018,909.99),(218,'Electra Cruiser 7D - 2016/2017/2018',1,3,2018,319.99),(219,'Electra Moto 3i - 2018',1,3,2018,639.99),(220,'Electra Cruiser 1 Ladies - 2018',1,3,2018,269.99),(221,'Electra Cruiser 7D Ladies - 2016/2018',1,3,2018,319.99),(222,'Electra Cruiser 1 Tall - 2016/2018',1,3,2018,269.99),(223,'Electra Cruiser Lux 3i - 2018',1,3,2018,529.99),(224,'Electra Cruiser Lux 7D - 2018',1,3,2018,479.99),(225,'Electra Delivery 3i - 2016/2017/2018',1,3,2018,959.99),(226,'Electra Townie Original 21D EQ - 2017/2018',1,3,2018,679.99),(227,'Electra Cruiser 7D (24-Inch) Ladies - 2016/2018',1,3,2018,319.99),(228,'Electra Cruiser 7D Tall - 2016/2018',1,3,2018,319.99),(229,'Electra Cruiser Lux 1 - 2016/2018',1,3,2018,429.99),(230,'Electra Cruiser Lux 1 Ladies - 2018',1,3,2018,429.99),(231,'Electra Cruiser Lux 3i Ladies - 2018',1,3,2018,529.99),(232,'Electra Cruiser Lux 7D Ladies - 2018',1,3,2018,479.99),(233,'Electra Cruiser Lux Fat Tire 7D - 2018',1,3,2018,639.99),(234,'Electra Daydreamer 3i Ladies - 2018',1,3,2018,899.99),(235,'Electra Koa 3i Ladies - 2018',1,3,2018,899.99),(236,'Electra Morningstar 3i Ladies - 2018',1,3,2018,749.99),(237,'Electra Relic 3i - 2018',1,3,2018,849.99),(238,'Electra Townie Balloon 8D EQ - 2016/2017/2018',1,3,2018,749.99),(239,'Electra Townie Balloon 8D EQ Ladies - 2016/2017/2018',1,3,2018,749.99),(240,'Electra Townie Commute 27D Ladies - 2018',1,3,2018,899.99),(241,'Electra Townie Commute 8D - 2018',1,3,2018,749.99),(242,'Electra Townie Commute 8D Ladies - 2018',1,3,2018,699.99),(243,'Electra Townie Original 21D EQ Ladies - 2018',1,3,2018,679.99),(244,'Electra Townie Original 21D Ladies - 2018',1,3,2018,559.99),(245,'Electra Townie Original 3i EQ - 2017/2018',1,3,2018,659.99),(246,'Electra Townie Original 3i EQ Ladies - 2018',1,3,2018,639.99),(247,'Electra Townie Original 7D EQ - 2018',1,3,2018,599.99),(248,'Electra Townie Original 7D EQ Ladies - 2017/2018',1,3,2018,599.99),(249,'Electra White Water 3i - 2018',1,3,2018,749.99),(250,'Electra Townie Go! 8i - 2017/2018',1,3,2018,2599.99),(251,'Electra Townie Commute Go! - 2018',1,3,2018,2999.99),(252,'Electra Townie Commute Go! Ladies - 2018',1,3,2018,2999.99),(253,'Electra Townie Go! 8i Ladies - 2018',1,3,2018,2599.99),(254,'Electra Townie Balloon 3i EQ - 2017/2018',1,3,2018,749.99),(255,'Electra Townie Balloon 7i EQ Ladies - 2017/2018',1,3,2018,899.99),(256,'Electra Townie Commute 27D - 2018',1,3,2018,899.99),(257,'Electra Amsterdam Fashion 3i Ladies - 2017/2018',1,3,2018,899.99),(258,'Electra Amsterdam Royal 8i - 2017/2018',1,3,2018,1259.90),(259,'Electra Amsterdam Royal 8i Ladies - 2018',1,3,2018,1199.99),(260,'Electra Townie Balloon 3i EQ Ladies - 2018',1,3,2018,799.99),(261,'Electra Townie Balloon 7i EQ - 2018',1,3,2018,899.99),(262,'Trek MT 201 - 2018',9,1,2018,249.99),(263,'Strider Classic 12 Balance Bike - 2018',6,1,2018,89.99),(264,'Strider Sport 16 - 2018',6,1,2018,249.99),(265,'Strider Strider 20 Sport - 2018',6,1,2018,289.99),(266,'Trek Superfly 20 - 2018',9,1,2018,399.99),(267,'Trek Precaliber 12 Girl- 2018',9,1,2018,199.99),(268,'Trek Kickster - 2018',9,1,2018,159.99),(269,'Trek Precaliber 12 Boy- 2018',9,1,2018,199.99),(270,'Trek Precaliber 16 Boy- 2018',9,1,2018,209.99),(271,'Trek Precaliber 16 Girl- 2018',9,1,2018,209.99),(272,'Trek Precaliber 20 6-speed Boy- 2018',9,1,2018,289.99),(273,'Trek Precaliber 20 6-speed Girl- 2018',9,1,2018,289.99),(274,'Trek Precaliber 20 Boy- 2018',9,1,2018,229.99),(275,'Trek Precaliber 20 Girl- 2018',9,1,2018,229.99),(276,'Trek Precaliber 24 (7-Speed) - Boys - 2018',9,1,2018,319.99),(277,'Trek Precaliber 24 21-speed Boy- 2018',9,1,2018,369.99),(278,'Trek Precaliber 24 21-speed Girl- 2018',9,1,2018,369.99),(279,'Trek Precaliber 24 7-speed Girl- 2018',9,1,2018,319.99),(280,'Trek Superfly 24 - 2017/2018',9,1,2018,489.99),(281,'Electra Cruiser 7D (24-Inch) Ladies - 2016/2018',1,1,2018,319.99),(282,'Electra Cyclosaurus 1 (16-inch) - Boy- 2018',1,1,2018,279.99),(283,'Electra Heartchya 1 (20-inch) - Girl- 2018',1,1,2018,319.99),(284,'Electra Savannah 1 (20-inch) - Girl- 2018',1,1,2018,319.99),(285,'Electra Soft Serve 1 (16-inch) - Girl- 2018',1,1,2018,279.99),(286,'Electra Starship 1 16\" - 2018',1,1,2018,279.99),(287,'Electra Straight 8 1 (16-inch) - Boy- 2018',1,1,2018,279.99),(288,'Electra Straight 8 1 (20-inch) - Boy- 2018',1,1,2018,389.99),(289,'Electra Superbolt 1 20\" - 2018',1,1,2018,319.99),(290,'Electra Superbolt 3i 20\" - 2018',1,1,2018,369.99),(291,'Electra Sweet Ride 1 (20-inch) - Girl- 2018',1,1,2018,319.99),(292,'Electra Sweet Ride 3i (20-inch) - Girls - 2018',1,1,2018,369.99),(293,'Electra Tiger Shark 1 (20-inch) - Boys - 2018',1,1,2018,319.99),(294,'Electra Tiger Shark 3i (20-inch) - Boys - 2018',1,1,2018,369.99),(295,'Electra Treasure 1 20\" - 2018',1,1,2018,319.99),(296,'Electra Treasure 3i 20\" - 2018',1,1,2018,369.99),(297,'Electra Under-The-Sea 1 16\" - 2018',1,1,2018,279.99),(298,'Electra Water Lily 1 (16-inch) - Girl- 2018',1,1,2018,279.99),(299,'Electra Townie Original 21D - 2018',1,2,2018,559.99),(300,'Electra Townie Balloon 3i EQ Ladies - 2018',1,2,2018,799.99),(301,'Electra Townie Balloon 7i EQ - 2018',1,2,2018,899.99),(302,'Electra Townie Original 1 - 2018',1,2,2018,449.99),(303,'Electra Townie Go! 8i - 2017/2018',1,2,2018,2599.99),(304,'Electra Townie Original 21D EQ - 2017/2018',1,2,2018,679.99),(305,'Electra Townie Balloon 3i EQ - 2017/2018',1,2,2018,749.99),(306,'Electra Townie Balloon 7i EQ Ladies - 2017/2018',1,2,2018,899.99),(307,'Electra Townie Balloon 8D EQ - 2016/2017/2018',1,2,2018,749.99),(308,'Electra Townie Balloon 8D EQ Ladies - 2016/2017/2018',1,2,2018,749.99),(309,'Electra Townie Commute 27D - 2018',1,2,2018,899.99),(310,'Electra Townie Commute 27D Ladies - 2018',1,2,2018,899.99),(311,'Electra Townie Commute 8D - 2018',1,2,2018,749.99),(312,'Electra Townie Commute 8D Ladies - 2018',1,2,2018,699.99),(313,'Electra Townie Original 1 Ladies - 2018',1,2,2018,449.99),(314,'Electra Townie Original 21D EQ Ladies - 2018',1,2,2018,679.99),(315,'Electra Townie Original 21D Ladies - 2018',1,2,2018,559.99),(316,'Trek Checkpoint ALR 4 Women- 2019',9,7,2019,1699.99),(317,'Trek Checkpoint ALR 5 - 2019',9,7,2019,1999.99),(318,'Trek Checkpoint ALR 5 Women- 2019',9,7,2019,1999.99),(319,'Trek Checkpoint SL 5 Women- 2019',9,7,2019,2799.99),(320,'Trek Checkpoint SL 6 - 2019',9,7,2019,3799.99),(321,'Trek Checkpoint ALR Frameset - 2019',9,7,2019,3199.99);\n";

    private static final String CREATE_TABLE_PRODUIT = "CREATE TABLE produit (" +
    "ID INTEGER PRIMARY KEY," +
    "NOM VARCHAR(255)," +
    "MARQUE_ID INTEGER NOT NULL,"+
    "CATEGORIE_ID INTEGER NOT NULL," +
    "ANNEE_MODEL INTEGER,"+
    "PRIX_DEPART DECIMAL(19, 2),"+
    "CONSTRAINT fk_produit_marque_id FOREIGN KEY (MARQUE_ID) REFERENCES marque(ID) ON DELETE CASCADE,"+
    "CONSTRAINT fk_produit_categorie_id FOREIGN KEY (CATEGORIE_ID) REFERENCES categorie(ID) ON DELETE CASCADE)";

    private static final String CREATE_TABLE_MARQUE = "CREATE TABLE marque (" +
            "    ID INTEGER PRIMARY KEY," +
            "    NOM VARCHAR(255))";

    private static final String CREATE_TABLE_CATEGORIE= "CREATE TABLE categorie (" +
            "    ID INTEGER PRIMARY KEY," +
            "    NOM VARCHAR(255))";

    private static final String CREATE_TABLE_MAGASIN = "CREATE TABLE magasin (" +
            "    ID INTEGER PRIMARY KEY," +
            "    NOM VARCHAR(255)," +
            "    TELEPHONE VARCHAR(20)," +
            "    EMAIL VARCHAR(255)," +
            "    ADRESSE VARCHAR(255)," +
            "    VILLE VARCHAR(255)," +
            "    ETAT VARCHAR(255)," +
            "    CODEZIP VARCHAR(10))";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MARQUE);
        db.execSQL(INSERT_MARQUE);
        db.execSQL(CREATE_TABLE_CATEGORIE);
        db.execSQL(INSERT_CATEGORIE);
        db.execSQL(CREATE_TABLE_MAGASIN);
        db.execSQL(INSERT_MAGASIN);
        db.execSQL(CREATE_TABLE_PRODUIT);
        db.execSQL(INSERT_PRODUIT);
        db.execSQL(CREATE_TABLE_STOCK);
        db.execSQL(INSERT_STOCKS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if needed
        onCreate(db);
    }

    public long addStock(Stock stock) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("stock_quantity", stock.getQuantite());
        values.put("stock_store", stock.getMagasin().getId());
        values.put("stock_product", stock.getProduit().getId());

        long newRowId = db.insert("stocks", null, values);
        db.close();
        return newRowId;
    }

    public int updateStock(int magasinId, int produitId, Stock stock) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("stock_quantity", stock.getQuantite());
        values.put("stock_store", stock.getMagasin().getId());
        values.put("stock_product", stock.getProduit().getId());

        int rowsAffected = db.update("stocks", values, "stock_store = ? AND stock_product = ?", new String[]{String.valueOf(magasinId), String.valueOf(produitId)});
        db.close();
        return rowsAffected;
    }

    public int deleteStock(int stockStore, int stockProduct) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete("stocks", "stock_store = ? AND stock_product = ?", new String[]{String.valueOf(stockStore), String.valueOf(stockProduct)});
        db.close();
        return rowsAffected;
    }

    public List<Stock> getAllStocks() {
        List<Stock> stocks = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_STOCK, null, null, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") int quantite = cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITE));
                    @SuppressLint("Range") int produitId = cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUIT_ID));
                    @SuppressLint("Range") int magasinId = cursor.getInt(cursor.getColumnIndex(COLUMN_MAGASIN_ID));

                    // Create a Stock object and add it to the list
                    Stock stock = new Stock();
                    stock.setQuantite(quantite);
                    // Retrieve and set the associated Produit and Magasin objects
                    Produit produit = getProduitById(produitId);
                    Magasin magasin = getMagasinById(magasinId);

                    stock.setProduit(produit);
                    stock.setMagasin(magasin);

                    stocks.add(stock);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return stocks;
    }

    public List<Magasin> getAllMagasins() {
        List<Magasin> magasins = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("magasin", null, null, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("ID"));
                    @SuppressLint("Range") String nom = cursor.getString(cursor.getColumnIndex("NOM"));
                    @SuppressLint("Range") String telephone = cursor.getString(cursor.getColumnIndex("TELEPHONE"));
                    @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("EMAIL"));
                    @SuppressLint("Range") String adresse = cursor.getString(cursor.getColumnIndex("ADRESSE"));
                    @SuppressLint("Range") String ville = cursor.getString(cursor.getColumnIndex("VILLE"));
                    @SuppressLint("Range") String etat = cursor.getString(cursor.getColumnIndex("ETAT"));
                    @SuppressLint("Range") String codeZip = cursor.getString(cursor.getColumnIndex("CODEZIP"));

                    // Create a Magasin object and add it to the list
                    Magasin magasin = new Magasin();
                    magasin.setId(id);
                    magasin.setNom(nom);
                    magasin.setTelephone(telephone);
                    magasin.setEmail(email);
                    magasin.setAdresse(adresse);
                    magasin.setVille(ville);
                    magasin.setEtat(etat);
                    magasin.setCodeZip(codeZip);

                    magasins.add(magasin);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return magasins;
    }

    public List<Produit> getAllProduits() {
        List<Produit> produits = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("produit", null, null, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("ID"));
                    @SuppressLint("Range") String nom = cursor.getString(cursor.getColumnIndex("NOM"));
//                    @SuppressLint("Range") int marqueId = cursor.getInt(cursor.getColumnIndex("MARQUE_ID"));
//                    @SuppressLint("Range") int categorieId = cursor.getInt(cursor.getColumnIndex("CATEGORIE_ID"));
                    @SuppressLint("Range") int anneeModel = cursor.getInt(cursor.getColumnIndex("ANNEE_MODEL"));
//                    @SuppressLint("Range") double prixDepart = cursor.getDouble(cursor.getColumnIndex("PRIX_DEPART"));

                    // Create a Produit object and add it to the list
                    Produit produit = new Produit();
                    produit.setId(id);
                    produit.setNom(nom);

//                    Marque marque = getMarqueById(marqueId);
//                    Categorie categorie = getCategorieById(categorieId);

//                    produit.setMarqueId(marque);
//                    produit.setCategorieId(categorie);
                    produit.setAnneeModel(anneeModel);
//                    produit.setPrixDepart(prixDepart);

                    produits.add(produit);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return produits;
    }

    public Produit getProduitById(int produitId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Produit produit = null;

        String[] columns = {
                "ID",
                "NOM",
                "MARQUE_ID",
                "CATEGORIE_ID",
                "ANNEE_MODEL",
                "PRIX_DEPART"
        };

        String selection = "ID = ?";
        String[] selectionArgs = { String.valueOf(produitId) };

        Cursor cursor = db.query("produit", columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("ID"));
            @SuppressLint("Range") String nom = cursor.getString(cursor.getColumnIndex("NOM"));
//            @SuppressLint("Range") int marqueId = cursor.getInt(cursor.getColumnIndex("MARQUE_ID"));
//            @SuppressLint("Range") int categorieId = cursor.getInt(cursor.getColumnIndex("CATEGORIE_ID"));
            @SuppressLint("Range") int anneeModel = cursor.getInt(cursor.getColumnIndex("ANNEE_MODEL"));
//            @SuppressLint("Range") double prixDepart = cursor.getDouble(cursor.getColumnIndex("PRIX_DEPART"));

            // Create Produit object and populate its properties
            produit = new Produit();
            produit.setId(id);
            produit.setNom(nom);

//            Marque marque = getMarqueById(marqueId);
//            Categorie categorie = getCategorieById(categorieId);
//
//            produit.setMarqueId(marque);
//            produit.setCategorieId(categorie);
            produit.setAnneeModel(anneeModel);
//            produit.setPrixDepart(prixDepart);

            cursor.close();
        }

        db.close();
        return produit;
    }

    public Marque getMarqueById(int marqueId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {
                "ID",
                "NOM"
        };

        String selection = "ID = ?";
        String[] selectionArgs = {String.valueOf(marqueId)};

        Cursor cursor = db.query("marque", columns, selection, selectionArgs, null, null, null);

        Marque marque = null;

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("ID"));
            @SuppressLint("Range") String nom = cursor.getString(cursor.getColumnIndex("NOM"));

            // Create Marque object and populate its properties
            marque = new Marque();
            marque.setId(id);
            marque.setNom(nom);

            cursor.close();
        }

        db.close();
        return marque;
    }

    public Categorie getCategorieById(int categorieId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {
                "ID",
                "NOM"
        };

        String selection = "ID = ?";
        String[] selectionArgs = {String.valueOf(categorieId)};

        Cursor cursor = db.query("categorie", columns, selection, selectionArgs, null, null, null);

        Categorie categorie = null;

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("ID"));
            @SuppressLint("Range") String nom = cursor.getString(cursor.getColumnIndex("NOM"));

            // Create Categorie object and populate its properties
            categorie = new Categorie();
            categorie.setId(id);
            categorie.setNom(nom);

            cursor.close();
        }

        db.close();
        return categorie;
    }

    public Magasin getMagasinById(int magasinId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {
                "ID",
                "NOM",
                "TELEPHONE",
                "EMAIL",
                "ADRESSE",
                "VILLE",
                "ETAT",
                "CODEZIP"
        };

        String selection = "ID = ?";
        String[] selectionArgs = {String.valueOf(magasinId)};

        Cursor cursor = db.query("magasin", columns, selection, selectionArgs, null, null, null);

        Magasin magasin = null;

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("ID"));
            @SuppressLint("Range") String nom = cursor.getString(cursor.getColumnIndex("NOM"));
            @SuppressLint("Range") String telephone = cursor.getString(cursor.getColumnIndex("TELEPHONE"));
            @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("EMAIL"));
            @SuppressLint("Range") String adresse = cursor.getString(cursor.getColumnIndex("ADRESSE"));
            @SuppressLint("Range") String ville = cursor.getString(cursor.getColumnIndex("VILLE"));
            @SuppressLint("Range") String etat = cursor.getString(cursor.getColumnIndex("ETAT"));
            @SuppressLint("Range") String codeZip = cursor.getString(cursor.getColumnIndex("CODEZIP"));

            // Create Magasin object and populate its properties
            magasin = new Magasin();
            magasin.setId(id);
            magasin.setNom(nom);
            magasin.setTelephone(telephone);
            magasin.setEmail(email);
            magasin.setAdresse(adresse);
            magasin.setVille(ville);
            magasin.setEtat(etat);
            magasin.setCodeZip(codeZip);

            cursor.close();
        }

        db.close();
        return magasin;
    }


}

