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

import sn.ept.git.dic2.projetjeemobile.activities.PersonneActivity;
import sn.ept.git.dic2.projetjeemobile.R;
import sn.ept.git.dic2.projetjeemobile.entities.Personne;

public class PersonneAdapter extends ArrayAdapter<Personne> {

    private Context context;
    private List<Personne> personnes;

    public PersonneAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Personne> objects) {
        super(context, resource, objects);
        this.context = context;
        this.personnes = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.liste_personnes, parent, false);

        TextView txtPersonneId = (TextView) rowView.findViewById(R.id.txtPersonneId);
        TextView txtPersonneFirstName = (TextView) rowView.findViewById(R.id.txtPersonneFirstName);
        TextView txtPersonneLastName = (TextView) rowView.findViewById(R.id.txtPersonneLastName);
        TextView txtPersonneTelephone = (TextView) rowView.findViewById(R.id.txtPersonneTelephone);
        TextView txtPersonneEmail = (TextView) rowView.findViewById(R.id.txtPersonneEmail);

        txtPersonneId.setText(String.format("Id: %d", personnes.get(pos).getId()));
        txtPersonneFirstName.setText(String.format("First name: %s ", personnes.get(pos).getPrenom()));
        txtPersonneLastName.setText(String.format("Last name: %s", personnes.get(pos).getNom()));
        txtPersonneTelephone.setText(String.format("Phone: %s", personnes.get(pos).getTelephone()));
        txtPersonneEmail.setText(String.format("Email: %s", personnes.get(pos).getEmail()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Activity Personne Form
                Intent intent = new Intent(context, PersonneActivity.class);
                intent.putExtra("personne_id", String.valueOf(personnes.get(pos).getId()));
                intent.putExtra("personne_last_name", personnes.get(pos).getNom());
                intent.putExtra("personne_first_name", personnes.get(pos).getPrenom());
                intent.putExtra("personne_telephone", personnes.get(pos).getTelephone());
                intent.putExtra("personne_email", personnes.get(pos).getEmail());
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
