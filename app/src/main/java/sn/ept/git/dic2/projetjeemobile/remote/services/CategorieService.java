package sn.ept.git.dic2.projetjeemobile.remote.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import sn.ept.git.dic2.projetjeemobile.entities.Categorie;

public interface CategorieService {

    @GET("categories/")
    Call<List<Categorie>> getCategories();

    @PUT("categories/")
    Call<Categorie> addCategorie(@Body Categorie categorie);

    @PUT("categories/{id}")
    Call<Categorie> updateCategorie(@Path("id") int id, @Body Categorie categorie);

    @DELETE("categories/{id}")
    Call<Categorie> deleteCategorie(@Path("id") int id);
}
