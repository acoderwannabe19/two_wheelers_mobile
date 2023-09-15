package sn.ept.git.dic2.projetjeemobile;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class GPSLocationListener implements LocationListener {
    @Override
    public void onLocationChanged(Location location) {
        // Handle the new location data here
        // You can send it to the server using an HTTP library (e.g., Retrofit or OkHttp)
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // Handle status changes (if needed)
    }

    @Override
    public void onProviderEnabled(String provider) {
        // Handle provider enabled (if needed)
    }

    @Override
    public void onProviderDisabled(String provider) {
        // Handle provider disabled (if needed)
    }
}
