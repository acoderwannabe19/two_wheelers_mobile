package sn.ept.git.dic2.projetjeemobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sn.ept.git.dic2.projetjeemobile.R;
import sn.ept.git.dic2.projetjeemobile.adapters.CategorieAdapter;
import sn.ept.git.dic2.projetjeemobile.entities.Categorie;
import sn.ept.git.dic2.projetjeemobile.remote.APIUtils;
import sn.ept.git.dic2.projetjeemobile.remote.services.CategorieService;

public class MainCategorieActivity extends AppCompatActivity {
    Button btnAddCategorie;
    Button btnGetCategoriesList;
    ListView listView;

    CategorieService userService;
    List<Categorie> list = new ArrayList<Categorie>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_categorie);

//        setTitle("Retrofit 2 CRUD Demo");

        btnAddCategorie = (Button) findViewById(R.id.btnAddCategorie);
        btnGetCategoriesList = (Button) findViewById(R.id.btnGetCategoriesList);
        listView = (ListView) findViewById(R.id.listView);
        userService = APIUtils.getCategorieService();

        btnGetCategoriesList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get users list
                getCategoriesList();
            }
        });

        btnAddCategorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainCategorieActivity.this, CategorieActivity.class);
                intent.putExtra("user_name", "");
                startActivity(intent);
            }
        });
    }

    public void getCategoriesList(){
        Call<List<Categorie>> call = userService.getCategories();
        call.enqueue(new Callback<List<Categorie>>() {
            @Override
            public void onResponse(Call<List<Categorie>> call, Response<List<Categorie>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new CategorieAdapter(MainCategorieActivity.this, R.layout.liste_categories, list));
                }
            }

            @Override
            public void onFailure(Call<List<Categorie>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
