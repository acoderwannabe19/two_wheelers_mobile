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

import java.text.DecimalFormat;
import java.util.List;

import sn.ept.git.dic2.projetjeemobile.R;
import sn.ept.git.dic2.projetjeemobile.activities.ProduitActivity;
import sn.ept.git.dic2.projetjeemobile.entities.Produit;

public class ProduitAdapter extends ArrayAdapter<Produit> {

    private Context context;
    private List<Produit> produits;

    public ProduitAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Produit> objects) {
        super(context, resource, objects);
        this.context = context;
        this.produits = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.liste_produits, parent, false);

        TextView txtProduitId = rowView.findViewById(R.id.txtProduitId);
        TextView txtProduitNom = rowView.findViewById(R.id.txtProduitNom);
        TextView txtProduitMarque = rowView.findViewById(R.id.txtProduitMarque);
        TextView txtProduitCategorie = rowView.findViewById(R.id.txtProduitCategorie);
        TextView txtProduitAnneeModel = rowView.findViewById(R.id.txtProduitAnneeModel);
        TextView txtProduitPrixDepart = rowView.findViewById(R.id.txtProduitPrixDepart);

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        txtProduitId.setText(String.format("Id: %d", produits.get(pos).getId()));
        txtProduitNom.setText(String.format("Name: %s", produits.get(pos).getNom()));
        txtProduitMarque.setText(String.format("Brand: %s", produits.get(pos).getMarqueId().getNom()));
        txtProduitCategorie.setText(String.format("Category: %s", produits.get(pos).getCategorieId().getNom()));
        txtProduitAnneeModel.setText(String.format("Year: %d", produits.get(pos).getAnneeModel()));
        txtProduitPrixDepart.setText(String.format("Price: %s", decimalFormat.format(produits.get(pos).getPrixDepart())));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Activity Client Form
                Intent intent = new Intent(context, ProduitActivity.class);
                intent.putExtra("produit_id", String.valueOf(produits.get(pos).getId()));
                intent.putExtra("produit_name", produits.get(pos).getNom());
                intent.putExtra("produit_category", produits.get(pos).getCategorieId().getNom());
                intent.putExtra("produit_brand", produits.get(pos).getMarqueId().getNom());
                intent.putExtra("produit_year", String.valueOf(produits.get(pos).getAnneeModel()));
                intent.putExtra("produit_price", produits.get(pos).getPrixDepart().toString());


                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
