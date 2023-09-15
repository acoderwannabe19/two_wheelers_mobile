package sn.ept.git.dic2.projetjeemobile.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import java.util.List;

import sn.ept.git.dic2.projetjeemobile.R;
import sn.ept.git.dic2.projetjeemobile.activities.ProduitActivity;
import sn.ept.git.dic2.projetjeemobile.activities.StockActivity;
import sn.ept.git.dic2.projetjeemobile.entities.Stock;

public class StockAdapter extends ArrayAdapter<Stock> {

    private Context context;
    private List<Stock> stocks;

    public StockAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Stock> objects) {
        super(context, resource, objects);
        this.context = context;
        this.stocks = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.liste_stocks, parent, false);

        TextView txtStockProduit = rowView.findViewById(R.id.txtStockProduit);
        TextView txtStockQuantite = rowView.findViewById(R.id.txtStockQuantite);
        TextView txtStockMagasin = rowView.findViewById(R.id.txtStockMagasin);

        txtStockProduit.setText(String.format("Product: %s", stocks.get(pos).getProduit().getNom()));
        txtStockQuantite.setText(String.format("Quantity: %d", stocks.get(pos).getQuantite()));
        txtStockMagasin.setText(String.format("Store: %s", stocks.get(pos).getMagasin().getNom()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Activity Client Form
                Intent intent = new Intent(context, StockActivity.class);
                intent.putExtra("stock_product", String.valueOf(stocks.get(pos).getProduit().getId()));
                intent.putExtra("stock_quantity", String.valueOf(stocks.get(pos).getQuantite()));
                intent.putExtra("stock_store", String.valueOf(stocks.get(pos).getMagasin().getId()));


                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
