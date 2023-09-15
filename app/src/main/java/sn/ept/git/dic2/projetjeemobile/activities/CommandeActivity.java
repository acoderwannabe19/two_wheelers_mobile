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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sn.ept.git.dic2.projetjeemobile.R;
import sn.ept.git.dic2.projetjeemobile.entities.Client;
import sn.ept.git.dic2.projetjeemobile.entities.Commande;
import sn.ept.git.dic2.projetjeemobile.entities.Employe;
import sn.ept.git.dic2.projetjeemobile.entities.Magasin;
import sn.ept.git.dic2.projetjeemobile.remote.APIUtils;
import sn.ept.git.dic2.projetjeemobile.remote.services.CommandeService;

public class CommandeActivity extends AppCompatActivity {
    CommandeService commandeService;
    List<Client> clientList;
    List<Magasin> magasinList;
    List<Employe> vendeurList;
    EditText edtNumero;
    EditText edtCommandeDate;
    EditText edtCommandeDateLivraison;
    EditText edtCommandeDateLivraisonVoulue;
    EditText edtCommandeStatut;
    Spinner clientSpinner;
    Spinner magasinSpinner;
    Spinner vendeurSpinner;
    Button btnSave;
    Button btnDel;
    TextView txtNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commande);

        setTitle("Commandes");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtNumero = (TextView) findViewById(R.id.txtNumero);
        edtNumero = (EditText) findViewById(R.id.edtNumero);
        edtCommandeDate = (EditText) findViewById(R.id.edtCommandeDate);
        edtCommandeDateLivraison = (EditText) findViewById(R.id.edtCommandeDateLivraison);
        edtCommandeDateLivraisonVoulue = (EditText) findViewById(R.id.edtCommandeDateLivraisonVoulue);
        edtCommandeStatut = (EditText) findViewById(R.id.edtCommandeStatut);
        clientSpinner = (Spinner) findViewById(R.id.clientSpinner);
        magasinSpinner =(Spinner) findViewById(R.id.magasinSpinner);
        vendeurSpinner =(Spinner) findViewById(R.id.vendeurSpinner);


        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);

        commandeService = APIUtils.getCommandeService();

        loadClients();
        loadVendeurs();
        loadMagasins();


        Bundle extras = getIntent().getExtras();
        String commandeNumero = extras.getString("commande_numero");
        String commandeDate = extras.getString("commande_order_date");
        String commandeDateLivraison = extras.getString("commande_delivery_date");
        String commandeDateLivraisonVoulue = extras.getString("commande_wanted_delivery_date");
        String commandeStatut = extras.getString("commande_status");


        edtNumero.setText(commandeNumero);
        edtCommandeDate.setText(commandeDate);
        edtCommandeDateLivraison.setText(commandeDateLivraison);
        edtCommandeDateLivraisonVoulue.setText(commandeDateLivraisonVoulue);
        edtCommandeStatut.setText(commandeStatut);


//        if (commandeMagasin != null) {
//            int magasinIndex = findMagasinIndexById(commandeMagasin); // Implement this method
//            if (magasinIndex >= 0) {
//                magasinSpinner.setSelection(magasinIndex);
//            }
//        }
////
////// Find the index of selected client and set the spinner selection
//        if (commandeCommande != null) {
//            int clientIndex = findCommandeIndexById(commandeCommande); // Implement this method
//            if (clientIndex >= 0) {
//                clientSpinner.setSelection(clientIndex);
//            }
//        }


        if(commandeNumero != null && commandeNumero.trim().length() > 0 ){
            edtNumero.setFocusable(false);
        } else {
            txtNumero.setVisibility(View.INVISIBLE);
            edtNumero.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Commande u = new Commande();

//                u.setDateCommande(edtCommandeDate);
//                u.setDateLivraison(edtCommandeDateLivraison);
//                u.setDateLivraisonVoulue(edtCommandeDateLivraisonVoulue);
                u.setStatut(Short.parseShort(edtCommandeStatut.getText().toString()));
                u.setMagasinId((Magasin) magasinSpinner.getSelectedItem());
                u.setClientId((Client) clientSpinner.getSelectedItem());
                u.setVendeurId((Employe) vendeurSpinner.getSelectedItem());


                if(commandeNumero != null && commandeNumero.trim().length() > 0 ){
                    //update commande
                    updateCommande(Integer.parseInt(commandeNumero), u);
                } else {
//                    add commande
                    addCommande(u);
                }

                Intent intent = new Intent(CommandeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCommande(Integer.parseInt(commandeNumero));

                Intent intent = new Intent(CommandeActivity.this, MainActivity.class);
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

    private int findCommandeIndexById(String clientId) {
        for (int i = 0; i < clientList.size(); i++) {
            if (clientList.get(i).getId().toString().equals(clientId)) {
                return i;
            }
        }
        return -1; // Not found
    }


    private void loadClients() {
        Call<List<Client>> call = APIUtils.getClientService().getClients();
        call.enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                if (response.isSuccessful()) {
                    clientList = response.body();
                    populateClientSpinner();
                }
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
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

    private void loadVendeurs() {
        Call<List<Employe>> call = APIUtils.getEmployeService().getEmployes();
        call.enqueue(new Callback<List<Employe>>() {
            @Override
            public void onResponse(Call<List<Employe>> call, Response<List<Employe>> response) {
                if (response.isSuccessful()) {
                    vendeurList = response.body();
                    populateVendeurSpinner();
                }
            }

            @Override
            public void onFailure(Call<List<Employe>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    private void populateClientSpinner() {
        // Initialize ArrayAdapter or your custom adapter for Spinner
        ArrayAdapter<Client> clientAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, clientList);
        clientAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clientSpinner.setAdapter(clientAdapter);
    }

    private void populateMagasinSpinner() {
        // Initialize ArrayAdapter or your custom adapter for Spinner
        ArrayAdapter<Magasin> magasinAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, magasinList);
        magasinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        magasinSpinner.setAdapter(magasinAdapter);
    }

    private void populateVendeurSpinner() {
        // Initialize ArrayAdapter or your custom adapter for Spinner
        ArrayAdapter<Employe> vendeurAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, vendeurList);
        vendeurAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vendeurSpinner.setAdapter(vendeurAdapter);
    }

    public void addCommande(Commande u){
        Call<Commande> call = commandeService.addCommande(u);
        call.enqueue(new Callback<Commande>() {
            @Override
            public void onResponse(Call<Commande> call, Response<Commande> response) {
                if(response.isSuccessful()){
                    Toast.makeText(CommandeActivity.this, "Commande created successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Commande> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updateCommande(int id, Commande u){
        Call<Commande> call = commandeService.updateCommande(id, u);
        call.enqueue(new Callback<Commande>() {
            @Override
            public void onResponse(Call<Commande> call, Response<Commande> response) {
                if(response.isSuccessful()){
                    Toast.makeText(CommandeActivity.this, "Commande updated successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Commande> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void deleteCommande(int id){
        Call<Commande> call = commandeService.deleteCommande(id);
        call.enqueue(new Callback<Commande>() {
            @Override
            public void onResponse(Call<Commande> call, Response<Commande> response) {
                if(response.isSuccessful()){
                    Toast.makeText(CommandeActivity.this, "Commande deleted successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Commande> call, Throwable t) {
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