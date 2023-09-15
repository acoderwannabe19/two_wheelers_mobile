package sn.ept.git.dic2.projetjeemobile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sn.ept.git.dic2.projetjeemobile.R;
import sn.ept.git.dic2.projetjeemobile.entities.Magasin;
import sn.ept.git.dic2.projetjeemobile.entities.Employe;
import sn.ept.git.dic2.projetjeemobile.entities.Employe;
import sn.ept.git.dic2.projetjeemobile.entities.Produit;
import sn.ept.git.dic2.projetjeemobile.remote.APIUtils;
import sn.ept.git.dic2.projetjeemobile.remote.services.EmployeService;

public class EmployeActivity extends AppCompatActivity {
    EmployeService employeService;
    List<Employe> managerList;
    List<Magasin> magasinList;
    EditText edtUId;
    EditText edtEmployeFirstName;
    EditText edtEmployeLastName;
    EditText edtEmployeTelephone;
    EditText edtEmployeEmail;
    EditText edtEmployeAdresse;
    EditText edtEmployeVille;
    EditText edtEmployeEtat;
    EditText edtEmployeCodeZip;

    Spinner managerSpinner;
    Spinner magasinSpinner;
    Button btnSave;
    Button btnDel;
    TextView txtUId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employe);

        setTitle("Employes");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtUId = (TextView) findViewById(R.id.txtUId);
        edtUId = (EditText) findViewById(R.id.edtUId);
        edtEmployeFirstName = (EditText) findViewById(R.id.edtEmployeFirstName);
        edtEmployeLastName = (EditText) findViewById(R.id.edtEmployeLastName);
        edtEmployeTelephone = (EditText) findViewById(R.id.edtEmployeTelephone);
        edtEmployeEmail = (EditText) findViewById(R.id.edtEmployeEmail);
        edtEmployeAdresse = (EditText) findViewById(R.id.edtEmployeAdresse);
        edtEmployeVille = (EditText) findViewById(R.id.edtEmployeVille);
        edtEmployeEtat = (EditText) findViewById(R.id.edtEmployeEtat);
        edtEmployeCodeZip = (EditText) findViewById(R.id.edtEmployeCodeZip);
        managerSpinner = (Spinner) findViewById(R.id.managerSpinner);
        magasinSpinner =(Spinner) findViewById(R.id.magasinSpinner);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);

        employeService = APIUtils.getEmployeService();

        loadEmployes();

        loadMagasins();


        Bundle extras = getIntent().getExtras();
        String employeId = extras.getString("employe_id");
        String employeFirstName = extras.getString("employe_first_name");
        String employeLastName = extras.getString("employe_last_name");
        String employeTelephone = extras.getString("employe_phone");
        String employeEmail = extras.getString("employe_email");
        String employeAdresse = extras.getString("employe_address");
        String employeVille = extras.getString("employe_city");
        String employeEtat = extras.getString("employe_state");
        String employeCodeZip = extras.getString("employe_zip_code");


        edtUId.setText(employeId);
        edtEmployeFirstName.setText(employeFirstName);
        edtEmployeLastName.setText(employeLastName);
        edtEmployeTelephone.setText(employeTelephone);
        edtEmployeEmail.setText(employeEmail);
        edtEmployeAdresse.setText(employeAdresse);
        edtEmployeVille.setText(employeVille);
        edtEmployeEtat.setText(employeEtat);
        edtEmployeCodeZip.setText(employeCodeZip);


//        if (employeMagasin != null) {
//            int magasinIndex = findMagasinIndexById(employeMagasin); // Implement this method
//            if (magasinIndex >= 0) {
//                magasinSpinner.setSelection(magasinIndex);
//            }
//        }
////
////// Find the index of selected manager and set the spinner selection
//        if (employeEmploye != null) {
//            int managerIndex = findEmployeIndexById(employeEmploye); // Implement this method
//            if (managerIndex >= 0) {
//                managerSpinner.setSelection(managerIndex);
//            }
//        }


        if(employeId != null && employeId.trim().length() > 0 ){
            edtUId.setFocusable(false);
        } else {
            txtUId.setVisibility(View.INVISIBLE);
            edtUId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Employe u = new Employe();
                u.setPrenom(edtEmployeFirstName.getText().toString());
                u.setNom(edtEmployeLastName.getText().toString());
                u.setTelephone(edtEmployeTelephone.getText().toString());
                u.setEmail(edtEmployeEmail.getText().toString());
                u.setAdresse(edtEmployeAdresse.getText().toString());
                u.setVille(edtEmployeVille.getText().toString());
                u.setEtat(edtEmployeEtat.getText().toString());
                u.setCodeZip(edtEmployeCodeZip.getText().toString());
                u.setMagasinId((Magasin) magasinSpinner.getSelectedItem());
                u.setManagerId((Employe) managerSpinner.getSelectedItem());

                if(employeId != null && employeId.trim().length() > 0 ){
                    //update employe
                    updateEmploye(Integer.parseInt(employeId), u);
                } else {
//                    add employe
                    addEmploye(u);
                }

                Intent intent = new Intent(EmployeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEmploye(Integer.parseInt(employeId));

                Intent intent = new Intent(EmployeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private int findMagasinIndexById(String categoryId) {
        for (int i = 0; i < magasinList.size(); i++) {
            if (magasinList.get(i).getId().toString().equals(categoryId)) {
                return i;
            }
        }
        return -1; // Not found
    }

    private int findEmployeIndexById(String managerId) {
        for (int i = 0; i < managerList.size(); i++) {
            if (managerList.get(i).getId().toString().equals(managerId)) {
                return i;
            }
        }
        return -1; // Not found
    }


    private void loadEmployes() {
        Call<List<Employe>> call = APIUtils.getEmployeService().getEmployes();
        call.enqueue(new Callback<List<Employe>>() {
            @Override
            public void onResponse(Call<List<Employe>> call, Response<List<Employe>> response) {
                if (response.isSuccessful()) {
                    managerList = response.body();
                    populateEmployeSpinner();
                }
            }

            @Override
            public void onFailure(Call<List<Employe>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    private void loadMagasins() {
        Call<List<Magasin>> call = APIUtils.getMagasinService().getMagasins();
        call.enqueue(new Callback<List<Magasin>>() {
            @Override
            public void onResponse(Call<List<Magasin>> call, Response<List<Magasin>> response) {
                if (response.isSuccessful()) {
                    magasinList = response.body();
                    populateMagasinSpinner();
                }
            }

            @Override
            public void onFailure(Call<List<Magasin>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    private void populateEmployeSpinner() {
        // Initialize ArrayAdapter or your custom adapter for Spinner
        ArrayAdapter<Employe> managerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, managerList);
        managerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        managerSpinner.setAdapter(managerAdapter);
    }

    private void populateMagasinSpinner() {
        // Initialize ArrayAdapter or your custom adapter for Spinner
        ArrayAdapter<Magasin> magasinAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, magasinList);
        magasinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        magasinSpinner.setAdapter(magasinAdapter);
    }

    public void addEmploye(Employe u){
        Call<Employe> call = employeService.addEmploye(u);
        call.enqueue(new Callback<Employe>() {
            @Override
            public void onResponse(Call<Employe> call, Response<Employe> response) {
                if(response.isSuccessful()){
                    Toast.makeText(EmployeActivity.this, "Employe created successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Employe> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updateEmploye(int id, Employe u){
        Call<Employe> call = employeService.updateEmploye(id, u);
        call.enqueue(new Callback<Employe>() {
            @Override
            public void onResponse(Call<Employe> call, Response<Employe> response) {
                if(response.isSuccessful()){
                    Toast.makeText(EmployeActivity.this, "Employe updated successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Employe> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void deleteEmploye(int id){
        Call<Employe> call = employeService.deleteEmploye(id);
        call.enqueue(new Callback<Employe>() {
            @Override
            public void onResponse(Call<Employe> call, Response<Employe> response) {
                if(response.isSuccessful()){
                    Toast.makeText(EmployeActivity.this, "Employe deleted successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Employe> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}