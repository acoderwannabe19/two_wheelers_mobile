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
import sn.ept.git.dic2.projetjeemobile.entities.Client;
import sn.ept.git.dic2.projetjeemobile.remote.APIUtils;
import sn.ept.git.dic2.projetjeemobile.remote.services.ClientService;

public class ClientActivity extends AppCompatActivity {
    ClientService clientService;
    EditText edtUId;
    EditText edtClientFirstName;
    EditText edtClientLastName;
    EditText edtClientTelephone;
    EditText edtClientEmail;
    EditText edtClientAdresse;
    EditText edtClientVille;
    EditText edtClientEtat;
    EditText edtClientCodeZip;
    Button btnSave;
    Button btnDel;
    TextView txtUId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        setTitle("Clients");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtUId = (TextView) findViewById(R.id.txtUId);
        edtUId = (EditText) findViewById(R.id.edtUId);
        edtClientFirstName = (EditText) findViewById(R.id.edtClientFirstName);
        edtClientLastName = (EditText) findViewById(R.id.edtClientLastName);
        edtClientTelephone = (EditText) findViewById(R.id.edtClientTelephone);
        edtClientEmail = (EditText) findViewById(R.id.edtClientEmail);
        edtClientAdresse = (EditText) findViewById(R.id.edtClientAdresse);
        edtClientVille = (EditText) findViewById(R.id.edtClientVille);
        edtClientEtat = (EditText) findViewById(R.id.edtClientEtat);
        edtClientCodeZip = (EditText) findViewById(R.id.edtClientCodeZip);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);

        clientService = APIUtils.getClientService();

        Bundle extras = getIntent().getExtras();
        String clientId = extras.getString("client_id");
        String clientFirstName = extras.getString("client_first_name");
        String clientLastName = extras.getString("client_last_name");
        String clientTelephone = extras.getString("client_phone");
        String clientEmail = extras.getString("client_email");
        String clientAdresse = extras.getString("client_address");
        String clientVille = extras.getString("client_city");
        String clientEtat = extras.getString("client_state");
        String clientCodeZip = extras.getString("client_zip_code");


        edtUId.setText(clientId);
        edtClientFirstName.setText(clientFirstName);
        edtClientLastName.setText(clientLastName);
        edtClientTelephone.setText(clientTelephone);
        edtClientEmail.setText(clientEmail);
        edtClientAdresse.setText(clientAdresse);
        edtClientVille.setText(clientVille);
        edtClientEtat.setText(clientEtat);
        edtClientCodeZip.setText(clientCodeZip);


        if(clientId != null && clientId.trim().length() > 0 ){
            edtUId.setFocusable(false);
        } else {
            txtUId.setVisibility(View.INVISIBLE);
            edtUId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Client u = new Client();
                u.setPrenom(edtClientFirstName.getText().toString());
                u.setNom(edtClientLastName.getText().toString());
                u.setTelephone(edtClientTelephone.getText().toString());
                u.setEmail(edtClientEmail.getText().toString());
                u.setAdresse(edtClientAdresse.getText().toString());
                u.setVille(edtClientVille.getText().toString());
                u.setEtat(edtClientEtat.getText().toString());
                u.setCodeZip(edtClientCodeZip.getText().toString());

                if(clientId != null && clientId.trim().length() > 0 ){
                    //update client
                    updateClient(Integer.parseInt(clientId), u);
                } else {
//                    add client
//                    Client p = new Client();
                    addClient(u);
                }

                Intent intent = new Intent(ClientActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteClient(Integer.parseInt(clientId));

                Intent intent = new Intent(ClientActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void addClient(Client u){
        Call<Client> call = clientService.addClient(u);
        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ClientActivity.this, "Client created successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updateClient(int id, Client u){
        Call<Client> call = clientService.updateClient(id, u);
        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ClientActivity.this, "Client updated successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void deleteClient(int id){
        Call<Client> call = clientService.deleteClient(id);
        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ClientActivity.this, "Client deleted successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
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