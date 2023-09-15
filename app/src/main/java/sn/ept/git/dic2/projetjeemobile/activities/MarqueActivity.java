package sn.ept.git.dic2.projetjeemobile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sn.ept.git.dic2.projetjeemobile.R;
import sn.ept.git.dic2.projetjeemobile.entities.Marque;
import sn.ept.git.dic2.projetjeemobile.remote.APIUtils;
import sn.ept.git.dic2.projetjeemobile.remote.services.MarqueService;

public class MarqueActivity extends AppCompatActivity {
    MarqueService marqueService;
    EditText edtUId;
    EditText edtMarqueNom;
    Button btnSave;
    Button btnDel;
    TextView txtUId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marque);

        setTitle("Marques");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtUId = (TextView) findViewById(R.id.txtUId);
        edtUId = (EditText) findViewById(R.id.edtUId);
        edtMarqueNom = (EditText) findViewById(R.id.edtMarqueNom);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);

        marqueService = APIUtils.getMarqueService();

        Bundle extras = getIntent().getExtras();
        String marqueId = extras.getString("marque_id");
        String marqueNom = extras.getString("marque_name");


        edtUId.setText(marqueId);
        edtMarqueNom.setText(marqueNom);


        if(marqueId != null && marqueId.trim().length() > 0 ){
            edtUId.setFocusable(false);
        } else {
            txtUId.setVisibility(View.INVISIBLE);
            edtUId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Marque u = new Marque();
                u.setNom(edtMarqueNom.getText().toString());
                if(marqueId != null && marqueId.trim().length() > 0 ){
                    //update marque
                    updateMarque(Integer.parseInt(marqueId), u);
                } else {
//                    add marque
//                    Marque p = new Marque();
                    addMarque(u);
                }

                Intent intent = new Intent(MarqueActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteMarque(Integer.parseInt(marqueId));

                Intent intent = new Intent(MarqueActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void addMarque(Marque u){
        Call<Marque> call = marqueService.addMarque(u);
        call.enqueue(new Callback<Marque>() {
            @Override
            public void onResponse(Call<Marque> call, Response<Marque> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MarqueActivity.this, "Marque created successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Marque> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updateMarque(int id, Marque u){
        Call<Marque> call = marqueService.updateMarque(id, u);
        call.enqueue(new Callback<Marque>() {
            @Override
            public void onResponse(Call<Marque> call, Response<Marque> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MarqueActivity.this, "Marque updated successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Marque> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void deleteMarque(int id){
        Call<Marque> call = marqueService.deleteMarque(id);
        call.enqueue(new Callback<Marque>() {
            @Override
            public void onResponse(Call<Marque> call, Response<Marque> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MarqueActivity.this, "Marque deleted successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Marque> call, Throwable t) {
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
