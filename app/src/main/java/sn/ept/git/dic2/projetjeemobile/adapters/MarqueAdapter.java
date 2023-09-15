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
import sn.ept.git.dic2.projetjeemobile.activities.MarqueActivity;
import sn.ept.git.dic2.projetjeemobile.entities.Marque;

public class MarqueAdapter extends ArrayAdapter<Marque> {

    private Context context;
    private List<Marque> marques;

    public MarqueAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Marque> objects) {
        super(context, resource, objects);
        this.context = context;
        this.marques = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.liste_marques, parent, false);

        TextView txtMarqueId = rowView.findViewById(R.id.txtMarqueId);
        TextView txtMarqueNom = rowView.findViewById(R.id.txtMarqueNom);

        txtMarqueId.setText(String.format("Id: %d", marques.get(pos).getId()));
        txtMarqueNom.setText(String.format("Name: %s", marques.get(pos).getNom()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Activity Marque Form
                Intent intent = new Intent(context, MarqueActivity.class);
                intent.putExtra("marque_id", String.valueOf(marques.get(pos).getId()));
                intent.putExtra("marque_name", marques.get(pos).getNom());
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
