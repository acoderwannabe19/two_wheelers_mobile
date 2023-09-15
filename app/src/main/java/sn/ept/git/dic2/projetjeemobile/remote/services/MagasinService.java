package sn.ept.git.dic2.projetjeemobile.remote.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import sn.ept.git.dic2.projetjeemobile.entities.Magasin;

public interface MagasinService {

    @GET("magasins/")
    Call<List<Magasin>> getMagasins();

    @PUT("magasins/")
    Call<Magasin> addMagasin(@Body Magasin magasin);

    @PUT("magasins/{id}")
    Call<Magasin> updateMagasin(@Path("id") int id, @Body Magasin magasin);

    @DELETE("magasins/{id}")
    Call<Magasin> deleteMagasin(@Path("id") int id);
}
