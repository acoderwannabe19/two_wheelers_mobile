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
import sn.ept.git.dic2.projetjeemobile.adapters.MarqueAdapter;
import sn.ept.git.dic2.projetjeemobile.entities.Marque;
import sn.ept.git.dic2.projetjeemobile.remote.APIUtils;
import sn.ept.git.dic2.projetjeemobile.remote.services.MarqueService;

public class MainMarqueActivity extends AppCompatActivity {
    Button btnAddMarque;
    Button btnGetMarquesList;
    ListView listView;

    MarqueService userService;
    List<Marque> list = new ArrayList<Marque>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_marque);

//        setTitle("Retrofit 2 CRUD Demo");

        btnAddMarque = (Button) findViewById(R.id.btnAddMarque);
        btnGetMarquesList = (Button) findViewById(R.id.btnGetMarquesList);
        listView = (ListView) findViewById(R.id.listView);
        userService = APIUtils.getMarqueService();

        btnGetMarquesList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get users list
                getMarquesList();
            }
        });

        btnAddMarque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMarqueActivity.this, MarqueActivity.class);
                intent.putExtra("user_name", "");
                startActivity(intent);
            }
        });
    }

    public void getMarquesList(){
        Call<List<Marque>> call = userService.getMarques();
        call.enqueue(new Callback<List<Marque>>() {
            @Override
            public void onResponse(Call<List<Marque>> call, Response<List<Marque>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new MarqueAdapter(MainMarqueActivity.this, R.layout.liste_marques, list));
                }
            }

            @Override
            public void onFailure(Call<List<Marque>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
