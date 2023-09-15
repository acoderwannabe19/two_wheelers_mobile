package sn.ept.git.dic2.projetjeemobile;


import android.content.Context;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sn.ept.git.dic2.projetjeemobile.entities.Stock;
import sn.ept.git.dic2.projetjeemobile.remote.APIUtils;
import sn.ept.git.dic2.projetjeemobile.remote.services.StockService;

import java.util.ArrayList;
import java.util.List;

public class StockCheckWorker extends Worker {

    public StockCheckWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {

        checkStockQuantity();
        return Result.success();
    }

    private void checkStockQuantity() {
        StockService stockService = APIUtils.getStockService();
        Call<List<Stock>> call = stockService.getStocks();
        call.enqueue(new Callback<List<Stock>>() {
            @Override
            public void onResponse(Call<List<Stock>> call, Response<List<Stock>> response) {
                if (response.isSuccessful()) {
                    List<Stock> stockList = response.body();
                    int lowStockCount = 0;
                    List<Stock> lowStockItems = new ArrayList<>();

                    for (Stock stock : stockList) {
                        if (stock.getQuantite() < 3) {
                            lowStockCount++;
                            lowStockItems.add(stock);
                        }
                    }

                    if (lowStockCount > 0) {
                        sendNotification(lowStockCount, lowStockItems);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Stock>> call, Throwable t) {
            }
        });
    }

    private void sendNotification(int lowStockCount, List<Stock> lowStockItems) {
        Context context = getApplicationContext();
        String CHANNEL_ID = "stock_notification_channel";

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle("Low Stock!");
        inboxStyle.addLine("You have " + lowStockCount + " items with low stock:");

        for (Stock stock : lowStockItems) {
            String stockInfo = "Item: " + stock.getProduit().getNom() + " | Quantity: " + stock.getQuantite() + " | Store: " + stock.getMagasin().getNom();
            inboxStyle.addLine(stockInfo);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Low Stock!")
                .setContentText("You have " + lowStockCount + " items with low stock.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setStyle(inboxStyle);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(1, builder.build());
    }

}

