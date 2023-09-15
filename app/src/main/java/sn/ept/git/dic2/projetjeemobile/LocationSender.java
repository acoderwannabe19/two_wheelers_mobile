package sn.ept.git.dic2.projetjeemobile;

import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sn.ept.git.dic2.projetjeemobile.entities.GPSLocation;
import sn.ept.git.dic2.projetjeemobile.remote.services.GPSLocationService;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class LocationSender {

    private static final String TAG = "LocationSender";
    private static final long LOCATION_UPDATE_INTERVAL = 60000; // 10 minutes
    private final Context context;
    private final GPSLocationService gpsLocationService;
    private final FusedLocationProviderClient fusedLocationProviderClient;
    private Timer locationUpdateTimer;

    public LocationSender(Context context, GPSLocationService gpsLocationService) {
        this.context = context;
        this.gpsLocationService = gpsLocationService;
        fusedLocationProviderClient = new FusedLocationProviderClient(context);
    }

    public void startSendingLocationUpdates() {
        // Start sending location updates periodically
        locationUpdateTimer = new Timer();
        locationUpdateTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                getLastKnownLocationAndSend();
            }
        }, 0, LOCATION_UPDATE_INTERVAL);
    }

    private void getLastKnownLocationAndSend() {
        try {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(location -> {
                if (location != null) {
                    GPSLocation gpsLocation = new GPSLocation();
                    gpsLocation.setLatitude(location.getLatitude());
                    gpsLocation.setLongitude(location.getLongitude());
                    gpsLocation.setTimestamp(new Date());

                    sendLocationToServer(gpsLocation);
                }
            });
        } catch (SecurityException e) {
            Log.e(TAG, "Location permission denied: " + e.getMessage());
        }
    }

    private void sendLocationToServer(GPSLocation gpsLocation) {
        Call<GPSLocation> call = gpsLocationService.addGPSLocation(gpsLocation);
        call.enqueue(new Callback<GPSLocation>() {
            @Override
            public void onResponse(@NonNull Call<GPSLocation> call, @NonNull Response<GPSLocation> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "Location sent to server successfully");
                } else {
                    Log.e(TAG, "Failed to send location to server");
                }
            }

            @Override
            public void onFailure(@NonNull Call<GPSLocation> call, @NonNull Throwable t) {
                Log.e(TAG, "Error while sending location to server: " + t.getMessage());
            }
        });
    }

    public void stopSendingLocationUpdates() {
        if (locationUpdateTimer != null) {
            locationUpdateTimer.cancel();
        }
    }
}
