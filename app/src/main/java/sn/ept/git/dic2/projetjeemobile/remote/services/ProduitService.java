package sn.ept.git.dic2.projetjeemobile.remote.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import sn.ept.git.dic2.projetjeemobile.entities.Produit;

public interface ProduitService {

    @GET("produits/")
    Call<List<Produit>> getProduits();

    @PUT("produits/")
    Call<Produit> addProduit(@Body Produit produit);

    @PUT("produits/{id}")
    Call<Produit> updateProduit(@Path("id") int id, @Body Produit produit);

    @DELETE("produits/{id}")
    Call<Produit> deleteProduit(@Path("id") int id);
}
