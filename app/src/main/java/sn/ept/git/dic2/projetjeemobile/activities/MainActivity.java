package sn.ept.git.dic2.projetjeemobile.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import sn.ept.git.dic2.projetjeemobile.DatabaseHelper;
import sn.ept.git.dic2.projetjeemobile.LocationSender;
import sn.ept.git.dic2.projetjeemobile.R;
import sn.ept.git.dic2.projetjeemobile.remote.APIUtils;
import sn.ept.git.dic2.projetjeemobile.remote.RetrofitClient;
import sn.ept.git.dic2.projetjeemobile.remote.services.GPSLocationService;

public class MainActivity extends AppCompatActivity {
    Button goToPersonnes;
    Button goToMarques;
    Button goToCategories;
    Button goToMagasins;
    Button goToClients;
    Button goToProduits;
    Button goToStocks;
    Button goToEmployes;
    Button goToCommandes;
    Button goToArticles;

    private static final String TAG = "MainActivity";
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 123;
    private LocationSender locationSender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkLocationPermissions()) {
            // Permissions are granted, initialize and start LocationSender
            GPSLocationService gpsLocationService = APIUtils.getGPSLocationService();
            locationSender = new LocationSender(this, gpsLocationService);
            locationSender.startSendingLocationUpdates();
        }

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.close();


        goToPersonnes = (Button) findViewById(R.id.goToPersonnes);
        goToMarques = (Button) findViewById(R.id.goToMarques);
        goToCategories = (Button) findViewById(R.id.goToCategories);
        goToMagasins = (Button) findViewById(R.id.goToMagasins);
        goToClients = (Button) findViewById(R.id.goToClients);
        goToProduits = (Button) findViewById(R.id.goToProduits);
        goToStocks = (Button) findViewById(R.id.goToStocks);
        goToEmployes = (Button) findViewById(R.id.goToEmployes);
        goToCommandes = (Button) findViewById(R.id.goToCommandes);
        goToArticles = (Button) findViewById(R.id.goToArticles);



        goToPersonnes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainPersonneActivity.class);
                startActivity(intent);
            }
        });

        goToMarques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainMarqueActivity.class);
                startActivity(intent);
            }
        });

        goToCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainCategorieActivity.class);
                startActivity(intent);
            }
        });

        goToMagasins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainMagasinActivity.class);
                startActivity(intent);
            }
        });

        goToClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainClientActivity.class);
                startActivity(intent);
            }
        });

        goToProduits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainProduitActivity.class);
                startActivity(intent);
            }
        });

        goToStocks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainStockActivity.class);
                startActivity(intent);
            }
        });

        goToEmployes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainEmployeActivity.class);
                startActivity(intent);
            }
        });

        goToCommandes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainCommandeActivity.class);
                startActivity(intent);
            }
        });

        goToArticles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainArticleActivity.class);
                startActivity(intent);
            }
        });


    }

    private boolean checkLocationPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true; // Permissions are granted
        } else {
            // Request location permissions
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permissions granted, initialize and start LocationSender
                GPSLocationService gpsLocationService = APIUtils.getGPSLocationService();
                locationSender = new LocationSender(this, gpsLocationService);
                locationSender.startSendingLocationUpdates();
            } else {
                Log.e(TAG, "Location permissions denied");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Stop sending location updates when the activity is destroyed
        if (locationSender != null) {
            locationSender.stopSendingLocationUpdates();
        }
    }


}