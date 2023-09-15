package sn.ept.git.dic2.projetjeemobile.remote.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import sn.ept.git.dic2.projetjeemobile.entities.Employe;

public interface EmployeService {

    @GET("employes/")
    Call<List<Employe>> getEmployes();

    @PUT("employes/")
    Call<Employe> addEmploye(@Body Employe employe);

    @PUT("employes/{id}")
    Call<Employe> updateEmploye(@Path("id") int id, @Body Employe employe);

    @DELETE("employes/{id}")
    Call<Employe> deleteEmploye(@Path("id") int id);
}
