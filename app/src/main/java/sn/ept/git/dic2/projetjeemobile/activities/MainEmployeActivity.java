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
import sn.ept.git.dic2.projetjeemobile.adapters.EmployeAdapter;
import sn.ept.git.dic2.projetjeemobile.entities.Employe;
import sn.ept.git.dic2.projetjeemobile.remote.APIUtils;
import sn.ept.git.dic2.projetjeemobile.remote.services.EmployeService;

public class MainEmployeActivity extends AppCompatActivity {

    Button btnAddEmploye;
    Button btnGetEmployesList;
    ListView listView;

    EmployeService userService;
    List<Employe> list = new ArrayList<Employe>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_employe);

//        setTitle("Retrofit 2 CRUD Demo");

        btnAddEmploye = (Button) findViewById(R.id.btnAddEmploye);
        btnGetEmployesList = (Button) findViewById(R.id.btnGetEmployesList);
        listView = (ListView) findViewById(R.id.listView);
        userService = APIUtils.getEmployeService();

        btnGetEmployesList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get users list
                getEmployesList();
            }
        });

        btnAddEmploye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainEmployeActivity.this, EmployeActivity.class);
                intent.putExtra("user_name", "");
                startActivity(intent);
            }
        });
    }

    public void getEmployesList(){
        Call<List<Employe>> call = userService.getEmployes();
        call.enqueue(new Callback<List<Employe>>() {
            @Override
            public void onResponse(Call<List<Employe>> call, Response<List<Employe>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new EmployeAdapter(MainEmployeActivity.this, R.layout.liste_magasins, list));
                }
            }

            @Override
            public void onFailure(Call<List<Employe>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

}