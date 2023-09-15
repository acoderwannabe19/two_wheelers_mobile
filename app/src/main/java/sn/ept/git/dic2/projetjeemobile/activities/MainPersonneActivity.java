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
import sn.ept.git.dic2.projetjeemobile.adapters.PersonneAdapter;
import sn.ept.git.dic2.projetjeemobile.entities.Personne;
import sn.ept.git.dic2.projetjeemobile.remote.APIUtils;
import sn.ept.git.dic2.projetjeemobile.remote.services.PersonneService;

public class MainPersonneActivity extends AppCompatActivity {
    Button btnAddPersonne;
    Button btnGetPersonnesList;
    ListView listView;

    PersonneService userService;
    List<Personne> list = new ArrayList<Personne>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_personne);

//        setTitle("Retrofit 2 CRUD Demo");

        btnAddPersonne = (Button) findViewById(R.id.btnAddPersonne);
        btnGetPersonnesList = (Button) findViewById(R.id.btnGetPersonnesList);
        listView = (ListView) findViewById(R.id.listView);
        userService = APIUtils.getPersonneService();

        btnGetPersonnesList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get users list
                getPersonnesList();
            }
        });

        btnAddPersonne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPersonneActivity.this, PersonneActivity.class);
                intent.putExtra("user_name", "");
                startActivity(intent);
            }
        });
    }

    public void getPersonnesList(){
        Call<List<Personne>> call = userService.getPersonnes();
        call.enqueue(new Callback<List<Personne>>() {
            @Override
            public void onResponse(Call<List<Personne>> call, Response<List<Personne>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new PersonneAdapter(MainPersonneActivity.this, R.layout.liste_personnes, list));
                }
            }

            @Override
            public void onFailure(Call<List<Personne>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}