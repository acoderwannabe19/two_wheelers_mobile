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

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import sn.ept.git.dic2.projetjeemobile.R;
import sn.ept.git.dic2.projetjeemobile.activities.CommandeActivity;
import sn.ept.git.dic2.projetjeemobile.activities.EmployeActivity;
import sn.ept.git.dic2.projetjeemobile.entities.Commande;

public class CommandeAdapter extends ArrayAdapter<Commande> {

    private Context context;
    private List<Commande> commandes;

    public CommandeAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Commande> objects) {
        super(context, resource, objects);
        this.context = context;
        this.commandes = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.liste_commandes, parent, false);


        TextView txtCommandeNumero = rowView.findViewById(R.id.txtNumero);
        TextView txtCommandeStatut = rowView.findViewById(R.id.txtCommandeStatut);
        TextView txtCommandeDate = rowView.findViewById(R.id.txtCommandeDate);
        TextView txtCommandeLivraisonVoulue = rowView.findViewById(R.id.txtCommandeDateLivraisonVoulue);
        TextView txtCommandeLivraison = rowView.findViewById(R.id.txtCommandeDateLivraison);
        TextView txtCommandeClientId = rowView.findViewById(R.id.txtCommandeClientId);
        TextView txtCommandeVendeurId = rowView.findViewById(R.id.txtCommandeVendeurId);
        TextView txtCommandeMagasinId = rowView.findViewById(R.id.txtCommandeMagasinId);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());


        txtCommandeNumero.setText(String.format("Number: %d", commandes.get(pos).getNumero()));
        txtCommandeStatut.setText(String.format("Status: %d", commandes.get(pos).getStatut()));
        txtCommandeDate.setText(String.format("Order date: %s", dateFormat.format(commandes.get(pos).getDateCommande())));
        txtCommandeLivraisonVoulue.setText(String.format("Delivery date: %s", dateFormat.format(commandes.get(pos).getDateLivraisonVoulue())));
        txtCommandeLivraison.setText(String.format("Wanted delivery date: %s", dateFormat.format(commandes.get(pos).getDateLivraison())));
        txtCommandeClientId.setText(String.format("Client: %s %s", commandes.get(pos).getClientId().getPrenom(), commandes.get(pos).getClientId().getNom()));
        txtCommandeVendeurId.setText(String.format("Seller: %s %s", commandes.get(pos).getVendeurId().getPrenom(), commandes.get(pos).getVendeurId().getNom()));
        txtCommandeMagasinId.setText(String.format("Store: %s", commandes.get(pos).getMagasinId().getNom()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Activity Personne Form
                Intent intent = new Intent(context, CommandeActivity.class);
                intent.putExtra("commande_numero", String.valueOf(commandes.get(pos).getNumero()));
                intent.putExtra("commande_order_date", commandes.get(pos).getDateCommande());
                intent.putExtra("commande_delivery_date", commandes.get(pos).getDateLivraison());
                intent.putExtra("commande_wanted_delivery_date", commandes.get(pos).getDateLivraisonVoulue());
                intent.putExtra("commande_status", commandes.get(pos).getStatut());
                intent.putExtra("commande_client", commandes.get(pos).getClientId().getId());
                intent.putExtra("commande_seller", commandes.get(pos).getVendeurId().getId());
                intent.putExtra("commande_store", commandes.get(pos).getMagasinId().getId());


                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
