package sn.ept.git.dic2.projetjeemobile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sn.ept.git.dic2.projetjeemobile.R;
import sn.ept.git.dic2.projetjeemobile.entities.Magasin;
import sn.ept.git.dic2.projetjeemobile.remote.APIUtils;
import sn.ept.git.dic2.projetjeemobile.remote.services.MagasinService;

public class MagasinActivity extends AppCompatActivity {
    MagasinService magasinService;
    EditText edtUId;
    EditText edtMagasinNom;
    EditText edtMagasinTelephone;
    EditText edtMagasinEmail;
    EditText edtMagasinAdresse;
    EditText edtMagasinVille;
    EditText edtMagasinEtat;
    EditText edtMagasinCodeZip;
    Button btnSave;
    Button btnDel;
    TextView txtUId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magasin);

        setTitle("Magasins");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtUId = (TextView) findViewById(R.id.txtUId);
        edtUId = (EditText) findViewById(R.id.edtUId);
        edtMagasinNom = (EditText) findViewById(R.id.edtMagasinNom);
        edtMagasinTelephone = (EditText) findViewById(R.id.edtMagasinTelephone);
        edtMagasinEmail = (EditText) findViewById(R.id.edtMagasinEmail);
        edtMagasinAdresse = (EditText) findViewById(R.id.edtMagasinAdresse);
        edtMagasinVille = (EditText) findViewById(R.id.edtMagasinVille);
        edtMagasinEtat = (EditText) findViewById(R.id.edtMagasinEtat);
        edtMagasinCodeZip = (EditText) findViewById(R.id.edtMagasinCodeZip);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);

        magasinService = APIUtils.getMagasinService();

        Bundle extras = getIntent().getExtras();
        String magasinId = extras.getString("magasin_id");
        String magasinNom = extras.getString("magasin_name");
        String magasinTelephone = extras.getString("magasin_phone");
        String magasinEmail = extras.getString("magasin_email");
        String magasinAdresse = extras.getString("magasin_address");
        String magasinVille = extras.getString("magasin_city");
        String magasinEtat = extras.getString("magasin_state");
        String magasinCodeZip = extras.getString("magasin_zip_code");


        edtUId.setText(magasinId);
        edtMagasinNom.setText(magasinNom);
        edtMagasinTelephone.setText(magasinTelephone);
        edtMagasinEmail.setText(magasinEmail);
        edtMagasinAdresse.setText(magasinAdresse);
        edtMagasinVille.setText(magasinVille);
        edtMagasinEtat.setText(magasinEtat);
        edtMagasinCodeZip.setText(magasinCodeZip);


        if(magasinId != null && magasinId.trim().length() > 0 ){
            edtUId.setFocusable(false);
        } else {
            txtUId.setVisibility(View.INVISIBLE);
            edtUId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Magasin u = new Magasin();
                u.setNom(edtMagasinNom.getText().toString());
                u.setTelephone(edtMagasinTelephone.getText().toString());
                u.setEmail(edtMagasinEmail.getText().toString());
                u.setAdresse(edtMagasinAdresse.getText().toString());
                u.setVille(edtMagasinVille.getText().toString());
                u.setEtat(edtMagasinEtat.getText().toString());
                u.setCodeZip(edtMagasinCodeZip.getText().toString());

                if(magasinId != null && magasinId.trim().length() > 0 ){
                    //update magasin
                    updateMagasin(Integer.parseInt(magasinId), u);
                } else {
//                    add magasin
//                    Magasin p = new Magasin();
                    addMagasin(u);
                }

                Intent intent = new Intent(MagasinActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteMagasin(Integer.parseInt(magasinId));

                Intent intent = new Intent(MagasinActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void addMagasin(Magasin u){
        Call<Magasin> call = magasinService.addMagasin(u);
        call.enqueue(new Callback<Magasin>() {
            @Override
            public void onResponse(Call<Magasin> call, Response<Magasin> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MagasinActivity.this, "Magasin created successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Magasin> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updateMagasin(int id, Magasin u){
        Call<Magasin> call = magasinService.updateMagasin(id, u);
        call.enqueue(new Callback<Magasin>() {
            @Override
            public void onResponse(Call<Magasin> call, Response<Magasin> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MagasinActivity.this, "Magasin updated successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Magasin> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void deleteMagasin(int id){
        Call<Magasin> call = magasinService.deleteMagasin(id);
        call.enqueue(new Callback<Magasin>() {
            @Override
            public void onResponse(Call<Magasin> call, Response<Magasin> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MagasinActivity.this, "Magasin deleted successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Magasin> call, Throwable t) {
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