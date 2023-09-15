package sn.ept.git.dic2.projetjeemobile.remote.services;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import sn.ept.git.dic2.projetjeemobile.entities.GPSLocation;

public interface GPSLocationService {

    @GET("gpsLocations/")
    Call<List<GPSLocation>> getGPSLocations();

    @PUT("gpsLocations/")
    Call<GPSLocation> addGPSLocation(@Body GPSLocation gpsLocation);

}

