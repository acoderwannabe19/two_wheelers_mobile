package sn.ept.git.dic2.projetjeemobile.remote.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import sn.ept.git.dic2.projetjeemobile.entities.ArticleCommande;

public interface ArticleCommandeService {

    @GET("articlesCommandes/")
    Call<List<ArticleCommande>> getArticleCommandes();

    @PUT("articlesCommandes/")
    Call<ArticleCommande> addArticleCommande(@Body ArticleCommande articleCommande);

    @PUT("articlesCommandes/{ligne}/{numeroCommande}")
    Call<ArticleCommande> updateArticleCommande(@Path("ligne") int ligne, @Path("numeroCommande") int numeroCommande, @Body ArticleCommande articleCommande);

    @DELETE("articlesCommandes/{ligne}/{numeroCommande}")
    Call<ArticleCommande> deleteArticleCommande(@Path("ligne") int ligne, @Path("numeroCommande") int numeroCommande);
}
