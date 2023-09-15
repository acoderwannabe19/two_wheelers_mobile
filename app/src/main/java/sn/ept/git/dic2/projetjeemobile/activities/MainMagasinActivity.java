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
import sn.ept.git.dic2.projetjeemobile.adapters.MagasinAdapter;
import sn.ept.git.dic2.projetjeemobile.entities.Magasin;
import sn.ept.git.dic2.projetjeemobile.remote.APIUtils;
import sn.ept.git.dic2.projetjeemobile.remote.services.MagasinService;

public class MainMagasinActivity extends AppCompatActivity {

    Button btnAddMagasin;
    Button btnGetMagasinsList;
    ListView listView;

    MagasinService userService;
    List<Magasin> list = new ArrayList<Magasin>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_magasin);

//        setTitle("Retrofit 2 CRUD Demo");

        btnAddMagasin = (Button) findViewById(R.id.btnAddMagasin);
        btnGetMagasinsList = (Button) findViewById(R.id.btnGetMagasinsList);
        listView = (ListView) findViewById(R.id.listView);
        userService = APIUtils.getMagasinService();

        btnGetMagasinsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get users list
                getMagasinsList();
            }
        });

        btnAddMagasin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMagasinActivity.this, MagasinActivity.class);
                intent.putExtra("user_name", "");
                startActivity(intent);
            }
        });
    }

    public void getMagasinsList(){
        Call<List<Magasin>> call = userService.getMagasins();
        call.enqueue(new Callback<List<Magasin>>() {
            @Override
            public void onResponse(Call<List<Magasin>> call, Response<List<Magasin>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new MagasinAdapter(MainMagasinActivity.this, R.layout.liste_magasins, list));
                }
            }

            @Override
            public void onFailure(Call<List<Magasin>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}