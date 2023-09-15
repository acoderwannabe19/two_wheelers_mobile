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
import sn.ept.git.dic2.projetjeemobile.activities.ArticleCommandeActivity;
import sn.ept.git.dic2.projetjeemobile.activities.ProduitActivity;
import sn.ept.git.dic2.projetjeemobile.entities.ArticleCommande;

public class ArticleCommandeAdapter extends ArrayAdapter<ArticleCommande> {

    private Context context;
    private List<ArticleCommande> articleCommandes;

    public ArticleCommandeAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ArticleCommande> objects) {
        super(context, resource, objects);
        this.context = context;
        this.articleCommandes = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.liste_articles_commandes, parent, false);

        TextView txtArticleCommandeNumero = rowView.findViewById(R.id.txtArticleCommandeNumero);
        TextView txtArticleCommandeLigne = rowView.findViewById(R.id.txtLigne);
        TextView txtArticleCommandeProduit = rowView.findViewById(R.id.txtArticleCommandeProduit);
        TextView txtArticleCommandeQuantite = rowView.findViewById(R.id.txtArticleCommandeQuantite);
        TextView txtArticleCommandePrixDepart = rowView.findViewById(R.id.txtArticleCommandePrixDepart);
        TextView txtArticleCommandeRemise = rowView.findViewById(R.id.txtArticleCommandeRemise);

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        txtArticleCommandeNumero.setText(String.format("Commande: %d", articleCommandes.get(pos).getNumeroCommande().getNumero()));
        txtArticleCommandeLigne.setText(String.format("Ligne: %d", articleCommandes.get(pos).getLigne()));
        txtArticleCommandeProduit.setText(String.format("Produit: %s", articleCommandes.get(pos).getProduitId().getNom()));
        txtArticleCommandeQuantite.setText(String.format("Quantite: %d", articleCommandes.get(pos).getQuantite()));
        txtArticleCommandePrixDepart.setText(String.format("Prix Depart: %s", decimalFormat.format(articleCommandes.get(pos).getPrixDepart())));
        txtArticleCommandeRemise.setText(String.format("Remise: %s", decimalFormat.format(articleCommandes.get(pos).getRemise())));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Activity Client Form
                Intent intent = new Intent(context, ArticleCommandeActivity.class);
                intent.putExtra("article_line", String.valueOf(articleCommandes.get(pos).getLigne()));
                intent.putExtra("article_number", String.valueOf(articleCommandes.get(pos).getNumeroCommande().getNumero()));
                intent.putExtra("article_quantity", articleCommandes.get(pos).getQuantite());
                intent.putExtra("article_discount", articleCommandes.get(pos).getRemise());
                intent.putExtra("article_product", articleCommandes.get(pos).getProduitId().getNom());
                intent.putExtra("article_price", articleCommandes.get(pos).getPrixDepart().toString());


                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
