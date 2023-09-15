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
import sn.ept.git.dic2.projetjeemobile.entities.Categorie;
import sn.ept.git.dic2.projetjeemobile.entities.Marque;
import sn.ept.git.dic2.projetjeemobile.entities.Produit;
import sn.ept.git.dic2.projetjeemobile.remote.APIUtils;
import sn.ept.git.dic2.projetjeemobile.remote.services.ProduitService;

public class ProduitActivity extends AppCompatActivity {
    ProduitService produitService;
    List<Marque> marqueList;
    List<Categorie> categorieList;
    EditText edtUId;
    EditText edtProduitNom;
    EditText edtProduitAnneeModel;
    EditText edtProduitPrixDepart;

    Spinner marqueSpinner;
    Spinner categorieSpinner;
    Button btnSave;
    Button btnDel;
    TextView txtUId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produit);

        setTitle("Produits");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtUId = (TextView) findViewById(R.id.txtUId);
        edtUId = (EditText) findViewById(R.id.edtUId);
        edtProduitNom = (EditText) findViewById(R.id.edtProduitNom);
        edtProduitAnneeModel = (EditText) findViewById(R.id.edtProduitAnneeModel);
        edtProduitPrixDepart = (EditText) findViewById(R.id.edtProduitPrixDepart);
        marqueSpinner = (Spinner) findViewById(R.id.marqueSpinner);
        categorieSpinner =(Spinner) findViewById(R.id.categorieSpinner);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);

        produitService = APIUtils.getProduitService();

        loadMarques();

        loadCategories();


        Bundle extras = getIntent().getExtras();
        String produitId = extras.getString("produit_id");
        String produitNom = extras.getString("produit_name");
        String produitMarque = extras.getString("produit_brand");
        String produitCategorie = extras.getString("produit_category");
        String produitAnneeModel = extras.getString("produit_year");
        String produitPrixDepart = extras.getString("produit_price");


        edtUId.setText(produitId);
        edtProduitNom.setText(produitNom);
        edtProduitAnneeModel.setText(produitAnneeModel);
        edtProduitPrixDepart.setText(produitPrixDepart);


//        if (produitCategorie != null) {
//            int categorieIndex = findCategorieIndexById(produitCategorie); // Implement this method
//            if (categorieIndex >= 0) {
//                categorieSpinner.setSelection(categorieIndex);
//            }
//        }
////
////// Find the index of selected marque and set the spinner selection
//        if (produitMarque != null) {
//            int marqueIndex = findMarqueIndexById(produitMarque); // Implement this method
//            if (marqueIndex >= 0) {
//                marqueSpinner.setSelection(marqueIndex);
//            }
//        }


        if(produitId != null && produitId.trim().length() > 0 ){
            edtUId.setFocusable(false);
        } else {
            txtUId.setVisibility(View.INVISIBLE);
            edtUId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Produit u = new Produit();
                u.setNom(edtProduitNom.getText().toString());
                u.setMarqueId((Marque) marqueSpinner.getSelectedItem());
                u.setCategorieId((Categorie) categorieSpinner.getSelectedItem());

                String prixDepartText = edtProduitPrixDepart.getText().toString();
                BigDecimal prixDepart = new BigDecimal(prixDepartText);
                u.setPrixDepart(prixDepart);

                String anneeModelText = edtProduitAnneeModel.getText().toString();
                int anneeModel = Integer.parseInt(anneeModelText);
                u.setAnneeModel(anneeModel);

                if(produitId != null && produitId.trim().length() > 0 ){
                    //update produit
                    updateProduit(Integer.parseInt(produitId), u);
                } else {
//                    add produit
                    addProduit(u);
                }

                Intent intent = new Intent(ProduitActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProduit(Integer.parseInt(produitId));

                Intent intent = new Intent(ProduitActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private int findCategorieIndexById(String categoryId) {
        for (int i = 0; i < categorieList.size(); i++) {
            if (categorieList.get(i).getId().toString().equals(categoryId)) {
                return i;
            }
        }
        return -1; // Not found
    }

    private int findMarqueIndexById(String marqueId) {
        for (int i = 0; i < marqueList.size(); i++) {
            if (marqueList.get(i).getId().toString().equals(marqueId)) {
                return i;
            }
        }
        return -1; // Not found
    }


    private void loadMarques() {
        Call<List<Marque>> call = APIUtils.getMarqueService().getMarques();
        call.enqueue(new Callback<List<Marque>>() {
            @Override
            public void onResponse(Call<List<Marque>> call, Response<List<Marque>> response) {
                if (response.isSuccessful()) {
                    marqueList = response.body();
                    populateMarqueSpinner();
                }
            }

            @Override
            public void onFailure(Call<List<Marque>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    private void loadCategories() {
        Call<List<Categorie>> call = APIUtils.getCategorieService().getCategories();
        call.enqueue(new Callback<List<Categorie>>() {
            @Override
            public void onResponse(Call<List<Categorie>> call, Response<List<Categorie>> response) {
                if (response.isSuccessful()) {
                    categorieList = response.body();
                    populateCategorieSpinner();
                }
            }

            @Override
            public void onFailure(Call<List<Categorie>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    private void populateMarqueSpinner() {
        // Initialize ArrayAdapter or your custom adapter for Spinner
        ArrayAdapter<Marque> marqueAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, marqueList);
        marqueAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        marqueSpinner.setAdapter(marqueAdapter);
    }

    private void populateCategorieSpinner() {
        // Initialize ArrayAdapter or your custom adapter for Spinner
        ArrayAdapter<Categorie> categorieAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categorieList);
        categorieAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorieSpinner.setAdapter(categorieAdapter);
    }

    public void addProduit(Produit u){
        Call<Produit> call = produitService.addProduit(u);
        call.enqueue(new Callback<Produit>() {
            @Override
            public void onResponse(Call<Produit> call, Response<Produit> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ProduitActivity.this, "Produit created successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Produit> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updateProduit(int id, Produit u){
        Call<Produit> call = produitService.updateProduit(id, u);
        call.enqueue(new Callback<Produit>() {
            @Override
            public void onResponse(Call<Produit> call, Response<Produit> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ProduitActivity.this, "Produit updated successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Produit> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void deleteProduit(int id){
        Call<Produit> call = produitService.deleteProduit(id);
        call.enqueue(new Callback<Produit>() {
            @Override
            public void onResponse(Call<Produit> call, Response<Produit> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ProduitActivity.this, "Produit deleted successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Produit> call, Throwable t) {
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