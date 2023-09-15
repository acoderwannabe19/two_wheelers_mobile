package sn.ept.git.dic2.projetjeemobile.remote.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import sn.ept.git.dic2.projetjeemobile.entities.Stock;

public interface StockService {

    @GET("stocks/")
    Call<List<Stock>> getStocks();

    @PUT("stocks/")
    Call<Stock> addStock(@Body Stock stock);

    @PUT("stocks/{magasinId}/{produitId}")
    Call<Stock> updateStock(@Path("magasinId") int magasinId, @Path("produitId") int produitId, @Body Stock stock);

    @DELETE("stocks/{magasinId}/{produitId}")
    Call<Stock> deleteStock(@Path("magasinId") int magasinId, @Path("produitId") int produitId);
}
