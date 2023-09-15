package sn.ept.git.dic2.projetjeemobile.remote.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import sn.ept.git.dic2.projetjeemobile.entities.Marque;

public interface MarqueService {

    @GET("marques/")
    Call<List<Marque>> getMarques();

    @PUT("marques/")
    Call<Marque> addMarque(@Body Marque marque);

    @PUT("marques/{id}")
    Call<Marque> updateMarque(@Path("id") int id, @Body Marque marque);

    @DELETE("marques/{id}")
    Call<Marque> deleteMarque(@Path("id") int id);
}
