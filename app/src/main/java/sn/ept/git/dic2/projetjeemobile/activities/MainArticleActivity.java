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
import sn.ept.git.dic2.projetjeemobile.adapters.ArticleCommandeAdapter;
import sn.ept.git.dic2.projetjeemobile.entities.ArticleCommande;
import sn.ept.git.dic2.projetjeemobile.remote.APIUtils;
import sn.ept.git.dic2.projetjeemobile.remote.services.ArticleCommandeService;

public class MainArticleActivity extends AppCompatActivity {
    Button btnAddArticleCommande;
    Button btnGetArticleCommandesList;
    ListView listView;

    ArticleCommandeService userService;
    List<ArticleCommande> list = new ArrayList<ArticleCommande>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_article);

//        setTitle("Retrofit 2 CRUD Demo");

        btnAddArticleCommande = (Button) findViewById(R.id.btnAddArticle);
        btnGetArticleCommandesList = (Button) findViewById(R.id.btnGetArticlesList);
        listView = (ListView) findViewById(R.id.listView);
        userService = APIUtils.getArticleCommandeService();

        btnGetArticleCommandesList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get users list
                getArticleCommandesList();
            }
        });

        btnAddArticleCommande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainArticleActivity.this, ArticleCommandeActivity.class);
                intent.putExtra("user_name", "");
                startActivity(intent);
            }
        });
    }

    public void getArticleCommandesList(){
        Call<List<ArticleCommande>> call = userService.getArticleCommandes();
        call.enqueue(new Callback<List<ArticleCommande>>() {
            @Override
            public void onResponse(Call<List<ArticleCommande>> call, Response<List<ArticleCommande>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new ArticleCommandeAdapter(MainArticleActivity.this, R.layout.liste_articles_commandes, list));
                }
            }

            @Override
            public void onFailure(Call<List<ArticleCommande>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}