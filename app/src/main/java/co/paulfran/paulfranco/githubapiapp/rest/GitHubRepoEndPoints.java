package co.paulfran.paulfranco.githubapiapp.rest;

import java.util.List;

import co.paulfran.paulfranco.githubapiapp.model.GitHubRepo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubRepoEndPoints {

    @GET("/users/{user}/repos")
    Call<List<GitHubRepo>> getRepo(@Path("user") String name);

}