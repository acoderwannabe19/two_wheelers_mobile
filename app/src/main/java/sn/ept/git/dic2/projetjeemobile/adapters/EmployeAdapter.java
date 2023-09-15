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
import sn.ept.git.dic2.projetjeemobile.activities.EmployeActivity;
import sn.ept.git.dic2.projetjeemobile.activities.PersonneActivity;
import sn.ept.git.dic2.projetjeemobile.entities.Employe;

public class EmployeAdapter extends ArrayAdapter<Employe> {

    private Context context;
    private List<Employe> employes;

    public EmployeAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Employe> objects) {
        super(context, resource, objects);
        this.context = context;
        this.employes = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.liste_employes, parent, false);

        TextView txtEmployeId = rowView.findViewById(R.id.txtEmployeId);
        TextView txtEmployeFirstName = rowView.findViewById(R.id.txtEmployeFirstName);
        TextView txtEmployeLastName = rowView.findViewById(R.id.txtEmployeLastName);
        TextView txtEmployeTelephone = rowView.findViewById(R.id.txtEmployeTelephone);
        TextView txtEmployeEmail = rowView.findViewById(R.id.txtEmployeEmail);
        TextView txtEmployeAddress = rowView.findViewById(R.id.txtEmployeAdresse);
        TextView txtEmployeCity = rowView.findViewById(R.id.txtEmployeVille);
        TextView txtEmployeState = rowView.findViewById(R.id.txtEmployeEtat);
        TextView txtEmployeZipCode = rowView.findViewById(R.id.txtEmployeCodeZip);
        TextView txtEmployeMagasin = rowView.findViewById(R.id.txtEmployeMagasin);
        TextView txtEmployeManager = rowView.findViewById(R.id.txtEmployeManager);


        txtEmployeId.setText(String.format("Id: %d", employes.get(pos).getId()));
        txtEmployeFirstName.setText(String.format("First name: %s ", employes.get(pos).getPrenom()));
        txtEmployeLastName.setText(String.format("Last name: %s", employes.get(pos).getNom()));
        txtEmployeTelephone.setText(String.format("Phone: %s", employes.get(pos).getTelephone()));
        txtEmployeEmail.setText(String.format("Email: %s", employes.get(pos).getEmail()));
        txtEmployeAddress.setText(String.format("Address: %s", employes.get(pos).getAdresse()));
        txtEmployeCity.setText(String.format("City: %s", employes.get(pos).getVille()));
        txtEmployeState.setText(String.format("State: %s", employes.get(pos).getEtat()));
        txtEmployeZipCode.setText(String.format("Zip Code: %s", employes.get(pos).getCodeZip()));
        txtEmployeMagasin.setText(String.format("Store: %s", employes.get(pos).getMagasinId().getNom()));
//        if (employes.get(pos).getMagasinId().getId() != null) {
//            txtEmployeManager.setText(String.format("Manager: %s %s", employes.get(pos).getManagerId().getPrenom(), employes.get(pos).getManagerId().getNom()));
//        }


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Activity Personne Form
                Intent intent = new Intent(context, EmployeActivity.class);
                intent.putExtra("employe_id", String.valueOf(employes.get(pos).getId()));
                intent.putExtra("employe_last_name", employes.get(pos).getNom());
                intent.putExtra("employe_first_name", employes.get(pos).getPrenom());
                intent.putExtra("employe_telephone", employes.get(pos).getTelephone());
                intent.putExtra("employe_email", employes.get(pos).getEmail());
                intent.putExtra("employe_address", employes.get(pos).getAdresse());
                intent.putExtra("employe_city", employes.get(pos).getVille());
                intent.putExtra("employe_state", employes.get(pos).getEtat());
                intent.putExtra("employe_zip_code", employes.get(pos).getCodeZip());
                intent.putExtra("employe_store", String.valueOf(employes.get(pos).getMagasinId().getId()));
                if(employes.get(pos).getManagerId() != null) {
                    intent.putExtra("employe_manager", String.valueOf(employes.get(pos).getManagerId().getId()));
                }
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
