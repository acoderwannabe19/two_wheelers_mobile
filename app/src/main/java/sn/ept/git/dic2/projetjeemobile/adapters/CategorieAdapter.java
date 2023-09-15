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
import sn.ept.git.dic2.projetjeemobile.activities.CategorieActivity;
import sn.ept.git.dic2.projetjeemobile.entities.Categorie;

public class CategorieAdapter extends ArrayAdapter<Categorie> {

    private Context context;
    private List<Categorie> categories;

    public CategorieAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Categorie> objects) {
        super(context, resource, objects);
        this.context = context;
        this.categories = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.liste_categories, parent, false);

        TextView txtCategorieId = rowView.findViewById(R.id.txtCategorieId);
        TextView txtCategorieNom = rowView.findViewById(R.id.txtCategorieNom);

        txtCategorieId.setText(String.format("Id: %d", categories.get(pos).getId()));
        txtCategorieNom.setText(String.format("Name: %s", categories.get(pos).getNom()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Activity Categorie Form
                Intent intent = new Intent(context, CategorieActivity.class);
                intent.putExtra("categorie_id", String.valueOf(categories.get(pos).getId()));
                intent.putExtra("categorie_name", categories.get(pos).getNom());
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
