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
import sn.ept.git.dic2.projetjeemobile.adapters.CommandeAdapter;
import sn.ept.git.dic2.projetjeemobile.entities.Commande;
import sn.ept.git.dic2.projetjeemobile.remote.APIUtils;
import sn.ept.git.dic2.projetjeemobile.remote.services.CommandeService;

public class MainCommandeActivity extends AppCompatActivity {

    Button btnAddCommande;
    Button btnGetCommandesList;
    ListView listView;

    CommandeService userService;
    List<Commande> list = new ArrayList<Commande>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_commande);

//        setTitle("Retrofit 2 CRUD Demo");

        btnAddCommande = (Button) findViewById(R.id.btnAddCommande);
        btnGetCommandesList = (Button) findViewById(R.id.btnGetCommandesList);
        listView = (ListView) findViewById(R.id.listView);
        userService = APIUtils.getCommandeService();

        btnGetCommandesList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get users list
                getCommandesList();
            }
        });

        btnAddCommande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainCommandeActivity.this, CommandeActivity.class);
                intent.putExtra("user_name", "");
                startActivity(intent);
            }
        });
    }

    public void getCommandesList(){
        Call<List<Commande>> call = userService.getCommandes();
        call.enqueue(new Callback<List<Commande>>() {
            @Override
            public void onResponse(Call<List<Commande>> call, Response<List<Commande>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new CommandeAdapter(MainCommandeActivity.this, R.layout.liste_magasins, list));
                }
            }

            @Override
            public void onFailure(Call<List<Commande>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }


}