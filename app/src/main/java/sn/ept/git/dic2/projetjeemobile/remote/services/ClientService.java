package sn.ept.git.dic2.projetjeemobile.remote.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import sn.ept.git.dic2.projetjeemobile.entities.Client;

public interface ClientService {

    @GET("clients/")
    Call<List<Client>> getClients();

    @PUT("clients/")
    Call<Client> addClient(@Body Client client);

    @PUT("clients/{id}")
    Call<Client> updateClient(@Path("id") int id, @Body Client client);

    @DELETE("clients/{id}")
    Call<Client> deleteClient(@Path("id") int id);
}
