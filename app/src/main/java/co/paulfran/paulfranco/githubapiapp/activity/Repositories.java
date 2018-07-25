package co.paulfran.paulfranco.githubapiapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import co.paulfran.paulfranco.githubapiapp.R;
import co.paulfran.paulfranco.githubapiapp.adapter.ReposAdapter;
import co.paulfran.paulfranco.githubapiapp.model.GitHubRepo;
import co.paulfran.paulfranco.githubapiapp.rest.APIClient;
import co.paulfran.paulfranco.githubapiapp.rest.GitHubRepoEndPoints;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repositories extends AppCompatActivity {

    String receivedUserName;
    TextView userName;
    RecyclerView mRecyclerView;
    List<GitHubRepo> myDataSource = new ArrayList<>();
    RecyclerView.Adapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repository_screen);

        Bundle extras = getIntent().getExtras();
        receivedUserName = extras.getString("username");

        userName = (TextView) findViewById(R.id.username);

        userName.setText("User: " + receivedUserName);

        mRecyclerView= (RecyclerView) findViewById(R.id.repos_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new ReposAdapter(myDataSource, R.layout.list_item_repo,
                getApplicationContext());

        mRecyclerView.setAdapter(myAdapter);

        loadRepositories();

    }

    public void loadRepositories(){
        GitHubRepoEndPoints apiService =
                APIClient.getClient().create(GitHubRepoEndPoints.class);

        Call<List<GitHubRepo>> call = apiService.getRepo(receivedUserName);
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {

                myDataSource.clear();
                myDataSource.addAll(response.body());
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                // Log error here since request failed
                Log.e("Repos", t.toString());
            }

        });
    }
}

