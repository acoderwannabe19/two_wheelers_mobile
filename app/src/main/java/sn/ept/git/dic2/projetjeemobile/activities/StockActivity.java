package sn.ept.git.dic2.projetjeemobile.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sn.ept.git.dic2.projetjeemobile.DatabaseHelper;
import sn.ept.git.dic2.projetjeemobile.R;
import sn.ept.git.dic2.projetjeemobile.entities.Produit;
import sn.ept.git.dic2.projetjeemobile.entities.Magasin;
import sn.ept.git.dic2.projetjeemobile.entities.Stock;
import sn.ept.git.dic2.projetjeemobile.remote.APIUtils;
import sn.ept.git.dic2.projetjeemobile.remote.services.StockService;

public class StockActivity extends AppCompatActivity {
    StockService stockService;
    List<Magasin> magasinList;
    List<Produit> produitList;
    EditText edtQuantite;
    Spinner magasinSpinner;
    Spinner produitSpinner;
    Button btnSave;
    Button btnDel;
    TextView txtQuantite;
    String CHANNEL_ID = "stocksAlert";

    NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
//            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle("My notification")
            .setContentText("Much longer text that cannot fit one line...")
            .setStyle(new NotificationCompat.BigTextStyle()
                    .bigText("Much longer text that cannot fit one line..."))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT);

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.stocks);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        setTitle("Stocks");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtQuantite = (TextView) findViewById(R.id.txtQuantite);
        edtQuantite = (EditText) findViewById(R.id.edtQuantite);
        magasinSpinner = (Spinner) findViewById(R.id.magasinSpinner);
        produitSpinner =(Spinner) findViewById(R.id.produitSpinner);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);

        stockService = APIUtils.getStockService();

        loadMagasins();
        loadProduits();

        Bundle extras = getIntent().getExtras();
        String stockQuantite = extras.getString("stock_quantity");
        String stockMagasin = extras.getString("stock_store");
        String stockProduit = extras.getString("stock_product");


        edtQuantite.setText(stockQuantite);


//        if (produitProduit != null) {
//            int produitIndex = findProduitIndexById(produitProduit); // Implement this method
//            if (produitIndex >= 0) {
//                produitSpinner.setSelection(produitIndex);
//            }
//        }
////
////// Find the index of selected magasin and set the spinner selection
//        if (produitMagasin != null) {
//            int magasinIndex = findMagasinIndexById(produitMagasin); // Implement this method
//            if (magasinIndex >= 0) {
//                magasinSpinner.setSelection(magasinIndex);
//            }
//        }


        if((stockProduit != null && stockProduit.trim().length() > 0) && (stockMagasin != null && stockMagasin.trim().length() > 0) ){
            magasinSpinner.setFocusable(false);
            produitSpinner.setFocusable(false);
        } else {
//            magasinSpinner.setVisibility(View.INVISIBLE);
//            produitSpinner.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Stock u = new Stock();
                String quantiteText = edtQuantite.getText().toString();
                int quantite = Integer.parseInt(quantiteText);
                u.setQuantite(quantite);

                u.setMagasin((Magasin) magasinSpinner.getSelectedItem());
                u.setProduit((Produit) produitSpinner.getSelectedItem());


                if((stockProduit != null && stockProduit.trim().length() > 0) && (stockMagasin != null && stockMagasin.trim().length() > 0)){
                    //update produit
                    updateStock(Integer.parseInt(stockMagasin), Integer.parseInt(stockProduit), u);
                } else {
//                    add produit
                    addStock(u);
                }

                Intent intent = new Intent(StockActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteStock(Integer.parseInt(stockMagasin), Integer.parseInt(stockProduit));

                Intent intent = new Intent(StockActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfoMobile = cm.getNetworkInfo(cm.TYPE_MOBILE);
        NetworkInfo netInfoWifi = cm.getNetworkInfo(cm.TYPE_WIFI);
        if (netInfoMobile != null && netInfoMobile.isConnectedOrConnecting() ||
                netInfoWifi != null && netInfoWifi.isConnectedOrConnecting()) {
            return true;
        }
        return false;

    }


    private int findProduitIndexById(String categoryId) {
        for (int i = 0; i < produitList.size(); i++) {
            if (produitList.get(i).getId().toString().equals(categoryId)) {
                return i;
            }
        }
        return -1; // Not found
    }

    private int findMagasinIndexById(String magasinId) {
        for (int i = 0; i < magasinList.size(); i++) {
            if (magasinList.get(i).getId().toString().equals(magasinId)) {
                return i;
            }
        }
        return -1; // Not found
    }


    private void loadMagasins() {
        if (isOnline()){
            Call<List<Magasin>> call = APIUtils.getMagasinService().getMagasins();
            call.enqueue(new Callback<List<Magasin>>() {
                @Override
                public void onResponse(Call<List<Magasin>> call, Response<List<Magasin>> response) {
                    if (response.isSuccessful()) {
                        magasinList = response.body();
                        populateMagasinSpinner();
                    }
                }

                @Override
                public void onFailure(Call<List<Magasin>> call, Throwable t) {
                    Log.e("ERROR: ", t.getMessage());
                }
            });
        }else {
            DatabaseHelper dbHelper = new DatabaseHelper(StockActivity.this);
            magasinList = dbHelper.getAllMagasins();
            populateMagasinSpinner();

        }
    }

    private void loadProduits() {
        if (isOnline()){
            Call<List<Produit>> call = APIUtils.getProduitService().getProduits();
            call.enqueue(new Callback<List<Produit>>() {
                @Override
                public void onResponse(Call<List<Produit>> call, Response<List<Produit>> response) {
                    if (response.isSuccessful()) {
                        produitList = response.body();
                        populateProduitSpinner();
                    }
                }

                @Override
                public void onFailure(Call<List<Produit>> call, Throwable t) {
                    Log.e("ERROR: ", t.getMessage());
                }
            });
        }else {
            DatabaseHelper dbHelper = new DatabaseHelper(StockActivity.this);
            produitList = dbHelper.getAllProduits();
            populateProduitSpinner();
        }
    }

    private void populateMagasinSpinner() {
        // Initialize ArrayAdapter or your custom adapter for Spinner
        ArrayAdapter<Magasin> magasinAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, magasinList);
        magasinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        magasinSpinner.setAdapter(magasinAdapter);
    }

    private void populateProduitSpinner() {
        // Initialize ArrayAdapter or your custom adapter for Spinner
        ArrayAdapter<Produit> produitAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, produitList);
        produitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        produitSpinner.setAdapter(produitAdapter);
    }

    public void addStock(Stock u){
        if (isOnline()){
            Call<Stock> call = stockService.addStock(u);
            call.enqueue(new Callback<Stock>() {
                @Override
                public void onResponse(Call<Stock> call, Response<Stock> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(StockActivity.this, "Stock created successfully!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Stock> call, Throwable t) {
                    Log.e("ERROR: ", t.getMessage());
                }
            });
        } else {
            // Perform local database insertion using DatabaseHelper
            DatabaseHelper dbHelper = new DatabaseHelper(StockActivity.this);
            dbHelper.addStock(u);
            // Show a message or perform appropriate action
            Toast.makeText(StockActivity.this, "Offline: Stock added locally!", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateStock(int magasinId, int produitId, Stock u){
        if (isOnline()){
            Call<Stock> call = stockService.updateStock(magasinId, produitId, u);
            call.enqueue(new Callback<Stock>() {
                @Override
                public void onResponse(Call<Stock> call, Response<Stock> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(StockActivity.this, "Stock updated successfully!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Stock> call, Throwable t) {
                    Log.e("ERROR: ", t.getMessage());
                }
            });
        }else {
            // Perform local database insertion using BicycleDbHelper
            DatabaseHelper dbHelper = new DatabaseHelper(StockActivity.this);
            dbHelper.updateStock(magasinId, produitId, u);
            // Show a message or perform appropriate action
            Toast.makeText(StockActivity.this, "Offline: Stock updated locally!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteStock(int magasinId, int produitId){
        if (isOnline()){
            Call<Stock> call = stockService.deleteStock(magasinId, produitId);
            call.enqueue(new Callback<Stock>() {
                @Override
                public void onResponse(Call<Stock> call, Response<Stock> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(StockActivity.this, "Stock deleted successfully!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Stock> call, Throwable t) {
                    Log.e("ERROR: ", t.getMessage());
                }
            });
        }else {
            // Perform local database insertion using BicycleDbHelper
            DatabaseHelper dbHelper = new DatabaseHelper(StockActivity.this);
            dbHelper.deleteStock(magasinId, produitId);
            // Show a message or perform appropriate action
            Toast.makeText(StockActivity.this, "Offline: Stock deleted locally!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}