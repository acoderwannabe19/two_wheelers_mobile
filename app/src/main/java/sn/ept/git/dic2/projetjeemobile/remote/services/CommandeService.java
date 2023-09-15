package sn.ept.git.dic2.projetjeemobile.remote.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import sn.ept.git.dic2.projetjeemobile.entities.Commande;

public interface CommandeService {

    @GET("commandes/")
    Call<List<Commande>> getCommandes();

    @PUT("commandes/")
    Call<Commande> addCommande(@Body Commande commande);

    @PUT("commandes/{numero}")
    Call<Commande> updateCommande(@Path("numero") int numero, @Body Commande commande);

    @DELETE("commandes/{numero}")
    Call<Commande> deleteCommande(@Path("numero") int numero);
}
