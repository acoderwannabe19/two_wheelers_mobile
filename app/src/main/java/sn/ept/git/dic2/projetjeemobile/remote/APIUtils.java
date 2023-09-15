package sn.ept.git.dic2.projetjeemobile.remote;


import sn.ept.git.dic2.projetjeemobile.entities.Employe;
import sn.ept.git.dic2.projetjeemobile.entities.GPSLocation;
import sn.ept.git.dic2.projetjeemobile.remote.services.ArticleCommandeService;
import sn.ept.git.dic2.projetjeemobile.remote.services.CategorieService;
import sn.ept.git.dic2.projetjeemobile.remote.services.ClientService;
import sn.ept.git.dic2.projetjeemobile.remote.services.CommandeService;
import sn.ept.git.dic2.projetjeemobile.remote.services.EmployeService;
import sn.ept.git.dic2.projetjeemobile.remote.services.GPSLocationService;
import sn.ept.git.dic2.projetjeemobile.remote.services.MagasinService;
import sn.ept.git.dic2.projetjeemobile.remote.services.MarqueService;
import sn.ept.git.dic2.projetjeemobile.remote.services.PersonneService;
import sn.ept.git.dic2.projetjeemobile.remote.services.ProduitService;
import sn.ept.git.dic2.projetjeemobile.remote.services.StockService;

public class APIUtils {

    private APIUtils(){
    };

    public static final String API_URL = "http://10.0.2.2:8080/projetJEE-1.0-SNAPSHOT/api/";

    public static PersonneService getPersonneService(){
        return RetrofitClient.getClient(API_URL).create(PersonneService.class);
    }

    public static MarqueService getMarqueService(){
        return RetrofitClient.getClient(API_URL).create(MarqueService.class);
    }

    public static CategorieService getCategorieService(){
        return RetrofitClient.getClient(API_URL).create(CategorieService.class);
    }

    public static MagasinService getMagasinService(){
        return RetrofitClient.getClient(API_URL).create(MagasinService.class);
    }

    public static ClientService getClientService(){
        return RetrofitClient.getClient(API_URL).create(ClientService.class);
    }

    public static EmployeService getEmployeService(){
        return RetrofitClient.getClient(API_URL).create(EmployeService.class);
    }

    public static CommandeService getCommandeService(){
        return RetrofitClient.getClient(API_URL).create(CommandeService.class);
    }

    public static ArticleCommandeService getArticleCommandeService(){
        return RetrofitClient.getClient(API_URL).create(ArticleCommandeService.class);
    }

    public static ProduitService getProduitService(){
        return RetrofitClient.getClient(API_URL).create(ProduitService.class);
    }

    public static StockService getStockService(){
        return RetrofitClient.getClient(API_URL).create(StockService.class);
    }

    public static GPSLocationService getGPSLocationService(){
        return RetrofitClient.getClient(API_URL).create(GPSLocationService.class);
    }




}
