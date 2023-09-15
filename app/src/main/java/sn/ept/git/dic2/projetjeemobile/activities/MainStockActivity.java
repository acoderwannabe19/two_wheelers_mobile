package sn.ept.git.dic2.projetjeemobile.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sn.ept.git.dic2.projetjeemobile.DatabaseHelper;
import sn.ept.git.dic2.projetjeemobile.R;
import sn.ept.git.dic2.projetjeemobile.StockCheckWorker;
import sn.ept.git.dic2.projetjeemobile.adapters.StockAdapter;
import sn.ept.git.dic2.projetjeemobile.entities.Stock;
import sn.ept.git.dic2.projetjeemobile.remote.APIUtils;
import sn.ept.git.dic2.projetjeemobile.remote.services.StockService;

public class MainStockActivity extends AppCompatActivity {


    Button btnAddStock;
    Button btnGetStocksList;
    ListView listView;
    StockService userService;
    List<Stock> list = new ArrayList<Stock>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_stock);


        Intent intent = new Intent(this, MainStockActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        scheduleStockCheckWorker();

        btnAddStock = (Button) findViewById(R.id.btnAddStock);
        btnGetStocksList = (Button) findViewById(R.id.btnGetStocksList);
        listView = (ListView) findViewById(R.id.listView);
        userService = APIUtils.getStockService();

        btnGetStocksList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get users list
                getStocksList();
            }
        });

        btnAddStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainStockActivity.this, StockActivity.class);
                intent.putExtra("user_name", "");
                startActivity(intent);
            }
        });
    }

    private void scheduleStockCheckWorker() {
        PeriodicWorkRequest stockCheckWorkRequest =
                new PeriodicWorkRequest.Builder(
                        StockCheckWorker.class, 15, TimeUnit.MINUTES)
                        .build();

        // Enqueue the work request
        WorkManager.getInstance(this).enqueue(stockCheckWorkRequest);
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

    public void getStocksList(){
        if (isOnline()){
            Call<List<Stock>> call = userService.getStocks();
            call.enqueue(new Callback<List<Stock>>() {
                @Override
                public void onResponse(Call<List<Stock>> call, Response<List<Stock>> response) {
                    if (response.isSuccessful()) {
                        list = response.body();
                        listView.setAdapter(new StockAdapter(MainStockActivity.this, R.layout.liste_stocks, list));
                    }
                }

                @Override
                public void onFailure(Call<List<Stock>> call, Throwable t) {
                    Log.e("ERROR: ", t.getMessage());
                }
            });
        }else {
            list = loadStocksFromLocalDatabase();
            if (list != null) {
                listView.setAdapter(new StockAdapter(MainStockActivity.this, R.layout.liste_stocks, list));
            }
        }
    }

    private List<Stock> loadStocksFromLocalDatabase() {
        DatabaseHelper dbHelper = new DatabaseHelper(MainStockActivity.this);
        List<Stock> stocks = dbHelper.getAllStocks();
        dbHelper.close();
        return stocks;
    }

}