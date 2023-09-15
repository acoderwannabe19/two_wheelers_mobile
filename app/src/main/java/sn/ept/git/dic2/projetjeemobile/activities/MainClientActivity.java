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
import sn.ept.git.dic2.projetjeemobile.adapters.ClientAdapter;
import sn.ept.git.dic2.projetjeemobile.entities.Client;
import sn.ept.git.dic2.projetjeemobile.remote.APIUtils;
import sn.ept.git.dic2.projetjeemobile.remote.services.ClientService;

public class MainClientActivity extends AppCompatActivity {

    Button btnAddClient;
    Button btnGetClientsList;
    ListView listView;

    ClientService userService;
    List<Client> list = new ArrayList<Client>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_client);

//        setTitle("Retrofit 2 CRUD Demo");

        btnAddClient = (Button) findViewById(R.id.btnAddClient);
        btnGetClientsList = (Button) findViewById(R.id.btnGetClientsList);
        listView = (ListView) findViewById(R.id.listView);
        userService = APIUtils.getClientService();

        btnGetClientsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get users list
                getClientsList();
            }
        });

        btnAddClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainClientActivity.this, ClientActivity.class);
                intent.putExtra("user_name", "");
                startActivity(intent);
            }
        });
    }

    public void getClientsList(){
        Call<List<Client>> call = userService.getClients();
        call.enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new ClientAdapter(MainClientActivity.this, R.layout.liste_clients, list));
                }
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}