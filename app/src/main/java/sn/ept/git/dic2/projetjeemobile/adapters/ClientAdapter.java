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
import sn.ept.git.dic2.projetjeemobile.activities.ClientActivity;
import sn.ept.git.dic2.projetjeemobile.entities.Client;

public class ClientAdapter extends ArrayAdapter<Client> {

    private Context context;
    private List<Client> clients;

    public ClientAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Client> objects) {
        super(context, resource, objects);
        this.context = context;
        this.clients = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.liste_clients, parent, false);

        TextView txtClientId = rowView.findViewById(R.id.txtClientId);
        TextView txtClientFirstName = rowView.findViewById(R.id.txtClientFirstName);
        TextView txtClientLastName = rowView.findViewById(R.id.txtClientLastName);
        TextView txtClientTelephone = rowView.findViewById(R.id.txtClientTelephone);
        TextView txtClientEmail = rowView.findViewById(R.id.txtClientEmail);
        TextView txtClientAddress = rowView.findViewById(R.id.txtClientAdresse);
        TextView txtClientCity = rowView.findViewById(R.id.txtClientVille);
        TextView txtClientState = rowView.findViewById(R.id.txtClientEtat);
        TextView txtClientZipCode = rowView.findViewById(R.id.txtClientCodeZip);

        txtClientId.setText(String.format("Id: %d", clients.get(pos).getId()));
        txtClientFirstName.setText(String.format("First name: %s ", clients.get(pos).getPrenom()));
        txtClientLastName.setText(String.format("Last name: %s", clients.get(pos).getNom()));
        txtClientTelephone.setText(String.format("Phone: %s", clients.get(pos).getTelephone()));
        txtClientEmail.setText(String.format("Email: %s", clients.get(pos).getEmail()));
        txtClientAddress.setText(String.format("Address: %s", clients.get(pos).getAdresse()));
        txtClientCity.setText(String.format("City: %s", clients.get(pos).getVille()));
        txtClientState.setText(String.format("State: %s", clients.get(pos).getEtat()));
        txtClientZipCode.setText(String.format("Zip Code: %s", clients.get(pos).getCodeZip()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Activity Client Form
                Intent intent = new Intent(context, ClientActivity.class);
                intent.putExtra("client_id", String.valueOf(clients.get(pos).getId()));
                intent.putExtra("client_last_name", clients.get(pos).getNom());
                intent.putExtra("client_first_name", clients.get(pos).getPrenom());
                intent.putExtra("client_phone", clients.get(pos).getTelephone());
                intent.putExtra("client_email", clients.get(pos).getEmail());
                intent.putExtra("client_address", clients.get(pos).getAdresse());
                intent.putExtra("client_city", clients.get(pos).getVille());
                intent.putExtra("client_state", clients.get(pos).getEtat());
                intent.putExtra("client_zip_code", clients.get(pos).getCodeZip());


                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
