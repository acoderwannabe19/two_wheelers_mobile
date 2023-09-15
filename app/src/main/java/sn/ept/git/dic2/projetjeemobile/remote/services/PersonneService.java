package sn.ept.git.dic2.projetjeemobile.remote.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import sn.ept.git.dic2.projetjeemobile.entities.Personne;

public interface PersonneService {

    @GET("personnes/")
    Call<List<Personne>> getPersonnes();

    @PUT("personnes/")
    Call<Personne> addPersonne(@Body Personne personne);

    @PUT("personnes/{id}")
    Call<Personne> updatePersonne(@Path("id") int id, @Body Personne personne);

    @DELETE("personnes/{id}")
    Call<Personne> deletePersonne(@Path("id") int id);
}
