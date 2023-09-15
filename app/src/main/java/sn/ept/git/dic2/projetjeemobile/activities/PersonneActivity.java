package sn.ept.git.dic2.projetjeemobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sn.ept.git.dic2.projetjeemobile.R;
import sn.ept.git.dic2.projetjeemobile.entities.Personne;
import sn.ept.git.dic2.projetjeemobile.remote.APIUtils;
import sn.ept.git.dic2.projetjeemobile.remote.services.PersonneService;

public class PersonneActivity extends AppCompatActivity {
    PersonneService personneService;
    EditText edtUId;
    EditText edtPersonneFirstName;
    EditText edtPersonneLastName;
    EditText edtPersonneTelephone;
    EditText edtPersonneEmail;
    Button btnSave;
    Button btnDel;
    TextView txtUId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personne);

        setTitle("Personnes");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtUId = (TextView) findViewById(R.id.txtUId);
        edtUId = (EditText) findViewById(R.id.edtUId);
        edtPersonneFirstName = (EditText) findViewById(R.id.edtPersonneFirstName);
        edtPersonneLastName = (EditText) findViewById(R.id.edtPersonneLastName);
        edtPersonneTelephone = (EditText) findViewById(R.id.edtPersonneTelephone);
        edtPersonneEmail = (EditText) findViewById(R.id.edtPersonneEmail);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);

        personneService = APIUtils.getPersonneService();

        Bundle extras = getIntent().getExtras();
        String personneId = extras.getString("personne_id");
        String personneFirstName = extras.getString("personne_first_name");
        String personneLastName = extras.getString("personne_last_name");
        String personneTelephone = extras.getString("personne_telephone");
        String personneEmail = extras.getString("personne_email");


        edtUId.setText(personneId);
        edtPersonneFirstName.setText(personneFirstName);
        edtPersonneLastName.setText(personneLastName);
        edtPersonneTelephone.setText(personneTelephone);
        edtPersonneEmail.setText(personneEmail);


        if(personneId != null && personneId.trim().length() > 0 ){
            edtUId.setFocusable(false);
        } else {
            txtUId.setVisibility(View.INVISIBLE);
            edtUId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Personne u = new Personne();
                u.setPrenom(edtPersonneFirstName.getText().toString());
                u.setNom(edtPersonneLastName.getText().toString());
                u.setTelephone(edtPersonneTelephone.getText().toString());
                u.setEmail(edtPersonneEmail.getText().toString());
                if(personneId != null && personneId.trim().length() > 0 ){
                    //update personne
                    updatePersonne(Integer.parseInt(personneId), u);
                } else {
//                    add personne
//                    Personne p = new Personne();
                    addPersonne(u);
                }

                Intent intent = new Intent(PersonneActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePersonne(Integer.parseInt(personneId));

                Intent intent = new Intent(PersonneActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void addPersonne(Personne u){
        Call<Personne> call = personneService.addPersonne(u);
        call.enqueue(new Callback<Personne>() {
            @Override
            public void onResponse(Call<Personne> call, Response<Personne> response) {
                if(response.isSuccessful()){
                    Toast.makeText(PersonneActivity.this, "Personne created successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Personne> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updatePersonne(int id, Personne u){
        Call<Personne> call = personneService.updatePersonne(id, u);
        call.enqueue(new Callback<Personne>() {
            @Override
            public void onResponse(Call<Personne> call, Response<Personne> response) {
                if(response.isSuccessful()){
                    Toast.makeText(PersonneActivity.this, "Personne updated successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Personne> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void deletePersonne(int id){
        Call<Personne> call = personneService.deletePersonne(id);
        call.enqueue(new Callback<Personne>() {
            @Override
            public void onResponse(Call<Personne> call, Response<Personne> response) {
                if(response.isSuccessful()){
                    Toast.makeText(PersonneActivity.this, "Personne deleted successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Personne> call, Throwable t) {
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
