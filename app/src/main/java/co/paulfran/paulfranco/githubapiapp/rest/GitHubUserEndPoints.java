package co.paulfran.paulfranco.githubapiapp.rest;

import com.google.gson.annotations.SerializedName;

import co.paulfran.paulfranco.githubapiapp.model.GitHubUser;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubUserEndPoints {

    @GET("/users/{user}")
    Call<GitHubUser> getUser(@Path("user") String user);



}
