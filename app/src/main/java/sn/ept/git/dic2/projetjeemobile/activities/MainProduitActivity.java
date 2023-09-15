package sn.ept.git.dic2.projetjeemobile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sn.ept.git.dic2.projetjeemobile.R;
import sn.ept.git.dic2.projetjeemobile.adapters.ProduitAdapter;
import sn.ept.git.dic2.projetjeemobile.entities.Produit;
import sn.ept.git.dic2.projetjeemobile.remote.APIUtils;
import sn.ept.git.dic2.projetjeemobile.remote.services.ProduitService;

public class MainProduitActivity extends AppCompatActivity {


    Button btnAddProduit;
    Button btnGetProduitsList;
    ListView listView;

    ProduitService userService;
    List<Produit> list = new ArrayList<Produit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_produit);

//        setTitle("Retrofit 2 CRUD Demo");

        btnAddProduit = (Button) findViewById(R.id.btnAddProduit);
        btnGetProduitsList = (Button) findViewById(R.id.btnGetProduitsList);
        listView = (ListView) findViewById(R.id.listView);
        userService = APIUtils.getProduitService();

        btnGetProduitsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get users list
                getProduitsList();
            }
        });

        btnAddProduit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainProduitActivity.this, ProduitActivity.class);
                intent.putExtra("user_name", "");
                startActivity(intent);
            }
        });
    }

    public void getProduitsList(){
        Call<List<Produit>> call = userService.getProduits();
        call.enqueue(new Callback<List<Produit>>() {
            @Override
            public void onResponse(Call<List<Produit>> call, Response<List<Produit>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new ProduitAdapter(MainProduitActivity.this, R.layout.liste_produits, list));
                }
            }

            @Override
            public void onFailure(Call<List<Produit>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}