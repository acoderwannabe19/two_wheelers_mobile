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
import sn.ept.git.dic2.projetjeemobile.entities.Commande;
import sn.ept.git.dic2.projetjeemobile.entities.Produit;
import sn.ept.git.dic2.projetjeemobile.entities.ArticleCommande;
import sn.ept.git.dic2.projetjeemobile.remote.APIUtils;
import sn.ept.git.dic2.projetjeemobile.remote.services.ArticleCommandeService;

public class ArticleCommandeActivity extends AppCompatActivity {
    ArticleCommandeService articleService;
    List<Produit> produitList;
    List<Commande> commandeList;
    EditText edtLigne;
    EditText edtArticleCommandeQuantite;
    EditText edtArticleCommandeRemise;
    EditText edtArticleCommandePrixDepart;

    Spinner produitSpinner;
    Spinner commandeSpinner;
    Button btnSave;
    Button btnDel;
    TextView txtLigne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_commande);

        setTitle("ArticleCommandes");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtLigne = (TextView) findViewById(R.id.txtLigne);
        edtLigne = (EditText) findViewById(R.id.edtLigne);
        edtArticleCommandeQuantite = (EditText) findViewById(R.id.edtArticleCommandeQuantite);
        edtArticleCommandeRemise = (EditText) findViewById(R.id.edtArticleCommandeRemise);
        edtArticleCommandePrixDepart = (EditText) findViewById(R.id.edtArticleCommandePrixDepart);
        produitSpinner = (Spinner) findViewById(R.id.produitSpinner);
        commandeSpinner =(Spinner) findViewById(R.id.commandeSpinner);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);

        articleService = APIUtils.getArticleCommandeService();

        loadProduits();

        loadCommandes();


        Bundle extras = getIntent().getExtras();
        String articleLigne = extras.getString("article_line");
        String articleNumero = extras.getString("article_number");
        String articleQuantite = extras.getString("article_quantity");
        String articleRemise = extras.getString("article_discount");
        String articlePrixDepart = extras.getString("article_price");


        edtLigne.setText(articleLigne);
        edtArticleCommandeQuantite.setText(articleQuantite);
        edtArticleCommandeRemise.setText(articleRemise);
        edtArticleCommandePrixDepart.setText(articlePrixDepart);


//        if (articleCommande != null) {
//            int commandeIndex = findCommandeIndexById(articleCommande); // Implement this method
//            if (commandeIndex >= 0) {
//                commandeSpinner.setSelection(commandeIndex);
//            }
//        }
////
////// Find the index of selected produit and set the spinner selection
//        if (articleProduit != null) {
//            int produitIndex = findProduitIndexById(articleProduit); // Implement this method
//            if (produitIndex >= 0) {
//                produitSpinner.setSelection(produitIndex);
//            }
//        }


        if((articleLigne != null && articleLigne.trim().length() > 0) && (articleNumero != null && articleNumero.trim().length() > 0) ){
            edtLigne.setFocusable(false);

        } else {
            txtLigne.setVisibility(View.INVISIBLE);
            edtLigne.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArticleCommande u = new ArticleCommande();
                String quantiteText = edtArticleCommandeQuantite.getText().toString();
                int quantite = Integer.parseInt(quantiteText);
                u.setQuantite(quantite);

                u.setProduitId((Produit) produitSpinner.getSelectedItem());
                u.setNumeroCommande((Commande) commandeSpinner.getSelectedItem());

                String prixDepartText = edtArticleCommandePrixDepart.getText().toString();
                BigDecimal prixDepart = new BigDecimal(prixDepartText);
                u.setPrixDepart(prixDepart);



                if((articleLigne != null && articleLigne.trim().length() > 0) && (articleNumero != null && articleNumero.trim().length() > 0)){
                    //update article
                    updateArticleCommande(Integer.parseInt(articleLigne), Integer.parseInt(articleNumero), u);
                } else {
//                    add article
                    addArticleCommande(u);
                }

                Intent intent = new Intent(ArticleCommandeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteArticleCommande(Integer.parseInt(articleLigne), Integer.parseInt(articleNumero));

                Intent intent = new Intent(ArticleCommandeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private int findCommandeIndexById(String categoryId) {
        for (int i = 0; i < commandeList.size(); i++) {
            if (commandeList.get(i).getNumero().toString().equals(categoryId)) {
                return i;
            }
        }
        return -1; // Not found
    }

    private int findProduitIndexById(String produitId) {
        for (int i = 0; i < produitList.size(); i++) {
            if (produitList.get(i).getId().toString().equals(produitId)) {
                return i;
            }
        }
        return -1; // Not found
    }


    private void loadProduits() {
        Call<List<Produit>> call = APIUtils.getProduitService().getProduits();
        call.enqueue(new Callback<List<Produit>>() {
            @Override
            public void onResponse(Call<List<Produit>> call, Response<List<Produit>> response) {
                if (response.isSuccessful()) {
                    produitList = response.body();
                    populateProduitSpinner();
                }
            }

            @Override
            public void onFailure(Call<List<Produit>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    private void loadCommandes() {
        Call<List<Commande>> call = APIUtils.getCommandeService().getCommandes();
        call.enqueue(new Callback<List<Commande>>() {
            @Override
            public void onResponse(Call<List<Commande>> call, Response<List<Commande>> response) {
                if (response.isSuccessful()) {
                    commandeList = response.body();
                    populateCommandeSpinner();
                }
            }

            @Override
            public void onFailure(Call<List<Commande>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    private void populateProduitSpinner() {
        // Initialize ArrayAdapter or your custom adapter for Spinner
        ArrayAdapter<Produit> produitAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, produitList);
        produitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        produitSpinner.setAdapter(produitAdapter);
    }

    private void populateCommandeSpinner() {
        // Initialize ArrayAdapter or your custom adapter for Spinner
        ArrayAdapter<Commande> commandeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, commandeList);
        commandeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        commandeSpinner.setAdapter(commandeAdapter);
    }

    public void addArticleCommande(ArticleCommande u){
        Call<ArticleCommande> call = articleService.addArticleCommande(u);
        call.enqueue(new Callback<ArticleCommande>() {
            @Override
            public void onResponse(Call<ArticleCommande> call, Response<ArticleCommande> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ArticleCommandeActivity.this, "ArticleCommande created successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArticleCommande> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updateArticleCommande(int ligne, int numeroCommande, ArticleCommande u){
        Call<ArticleCommande> call = articleService.updateArticleCommande(ligne, numeroCommande, u);
        call.enqueue(new Callback<ArticleCommande>() {
            @Override
            public void onResponse(Call<ArticleCommande> call, Response<ArticleCommande> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ArticleCommandeActivity.this, "ArticleCommande updated successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArticleCommande> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void deleteArticleCommande(int ligne, int numeroCommande){
        Call<ArticleCommande> call = articleService.deleteArticleCommande(ligne, numeroCommande);
        call.enqueue(new Callback<ArticleCommande>() {
            @Override
            public void onResponse(Call<ArticleCommande> call, Response<ArticleCommande> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ArticleCommandeActivity.this, "ArticleCommande deleted successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArticleCommande> call, Throwable t) {
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