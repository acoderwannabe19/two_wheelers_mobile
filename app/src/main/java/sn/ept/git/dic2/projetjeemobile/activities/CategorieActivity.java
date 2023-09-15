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
import sn.ept.git.dic2.projetjeemobile.entities.Categorie;
import sn.ept.git.dic2.projetjeemobile.remote.APIUtils;
import sn.ept.git.dic2.projetjeemobile.remote.services.CategorieService;

public class CategorieActivity extends AppCompatActivity {
    CategorieService categorieService;
    EditText edtUId;
    EditText edtCategorieNom;
    Button btnSave;
    Button btnDel;
    TextView txtUId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorie);

        setTitle("Categories");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtUId = (TextView) findViewById(R.id.txtUId);
        edtUId = (EditText) findViewById(R.id.edtUId);
        edtCategorieNom = (EditText) findViewById(R.id.edtCategorieNom);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);

        categorieService = APIUtils.getCategorieService();

        Bundle extras = getIntent().getExtras();
        String categorieId = extras.getString("categorie_id");
        String categorieNom = extras.getString("categorie_name");


        edtUId.setText(categorieId);
        edtCategorieNom.setText(categorieNom);


        if(categorieId != null && categorieId.trim().length() > 0 ){
            edtUId.setFocusable(false);
        } else {
            txtUId.setVisibility(View.INVISIBLE);
            edtUId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categorie u = new Categorie();
                u.setNom(edtCategorieNom.getText().toString());
                if(categorieId != null && categorieId.trim().length() > 0 ){
                    //update categorie
                    updateCategorie(Integer.parseInt(categorieId), u);
                } else {
//                    add categorie
//                    Categorie p = new Categorie();
                    addCategorie(u);
                }

                Intent intent = new Intent(CategorieActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCategorie(Integer.parseInt(categorieId));

                Intent intent = new Intent(CategorieActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void addCategorie(Categorie u){
        Call<Categorie> call = categorieService.addCategorie(u);
        call.enqueue(new Callback<Categorie>() {
            @Override
            public void onResponse(Call<Categorie> call, Response<Categorie> response) {
                if(response.isSuccessful()){
                    Toast.makeText(CategorieActivity.this, "Categorie created successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Categorie> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updateCategorie(int id, Categorie u){
        Call<Categorie> call = categorieService.updateCategorie(id, u);
        call.enqueue(new Callback<Categorie>() {
            @Override
            public void onResponse(Call<Categorie> call, Response<Categorie> response) {
                if(response.isSuccessful()){
                    Toast.makeText(CategorieActivity.this, "Categorie updated successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Categorie> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void deleteCategorie(int id){
        Call<Categorie> call = categorieService.deleteCategorie(id);
        call.enqueue(new Callback<Categorie>() {
            @Override
            public void onResponse(Call<Categorie> call, Response<Categorie> response) {
                if(response.isSuccessful()){
                    Toast.makeText(CategorieActivity.this, "Categorie deleted successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Categorie> call, Throwable t) {
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
