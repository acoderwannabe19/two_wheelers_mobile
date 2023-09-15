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
import sn.ept.git.dic2.projetjeemobile.activities.MagasinActivity;
import sn.ept.git.dic2.projetjeemobile.entities.Magasin;

public class MagasinAdapter extends ArrayAdapter<Magasin> {

    private Context context;
    private List<Magasin> magasins;

    public MagasinAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Magasin> objects) {
        super(context, resource, objects);
        this.context = context;
        this.magasins = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.liste_magasins, parent, false);

        TextView txtMagasinId = rowView.findViewById(R.id.txtMagasinId);
        TextView txtMagasinNom = rowView.findViewById(R.id.txtMagasinNom);
        TextView txtMagasinTelephone = rowView.findViewById(R.id.txtMagasinTelephone);
        TextView txtMagasinEmail = rowView.findViewById(R.id.txtMagasinEmail);
        TextView txtMagasinAdresse = rowView.findViewById(R.id.txtMagasinAdresse);
        TextView txtMagasinVille = rowView.findViewById(R.id.txtMagasinVille);
        TextView txtMagasinEtat = rowView.findViewById(R.id.txtMagasinEtat);
        TextView txtMagasinCodeZip = rowView.findViewById(R.id.txtMagasinCodeZip);

        txtMagasinId.setText(String.format("Id: %d", magasins.get(pos).getId()));
        txtMagasinNom.setText(String.format("Name: %s", magasins.get(pos).getNom()));
        txtMagasinTelephone.setText(String.format("Phone: %s", magasins.get(pos).getTelephone()));
        txtMagasinEmail.setText(String.format("Email: %s", magasins.get(pos).getEmail()));
        txtMagasinAdresse.setText(String.format("Address: %s", magasins.get(pos).getAdresse()));
        txtMagasinVille.setText(String.format("City: %s", magasins.get(pos).getVille()));
        txtMagasinEtat.setText(String.format("Sate: %s", magasins.get(pos).getEtat()));
        txtMagasinCodeZip.setText(String.format("Zip code: %s", magasins.get(pos).getCodeZip()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Activity Magasin Form
                Intent intent = new Intent(context, MagasinActivity.class);
                intent.putExtra("magasin_id", String.valueOf(magasins.get(pos).getId()));
                intent.putExtra("magasin_name", magasins.get(pos).getNom());
                intent.putExtra("magasin_phone", magasins.get(pos).getTelephone());
                intent.putExtra("magasin_email", magasins.get(pos).getEmail());
                intent.putExtra("magasin_address", magasins.get(pos).getAdresse());
                intent.putExtra("magasin_city", magasins.get(pos).getVille());
                intent.putExtra("magasin_state", magasins.get(pos).getEtat());
                intent.putExtra("magasin_zip_code", magasins.get(pos).getCodeZip());


                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
