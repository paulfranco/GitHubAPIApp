package co.paulfran.paulfranco.githubapiapp.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import co.paulfran.paulfranco.githubapiapp.ImageDownloader;
import co.paulfran.paulfranco.githubapiapp.R;
import co.paulfran.paulfranco.githubapiapp.model.GitHubUser;
import co.paulfran.paulfranco.githubapiapp.rest.APIClient;
import co.paulfran.paulfranco.githubapiapp.rest.GitHubUserEndPoints;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity{

    ImageView avatarImg;
    TextView usernameTV;
    TextView followersTV;
    TextView followingTV;
    TextView logIn;
    TextView email;
    Button ownedRepos;

    Bitmap myImage;

    Bundle extras;
    String newString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        avatarImg = (ImageView) findViewById(R.id.avatar);
        usernameTV = (TextView) findViewById(R.id.username);
        followersTV = (TextView) findViewById(R.id.followers);
        followingTV = (TextView) findViewById(R.id.following);
        logIn = (TextView) findViewById(R.id.logIn);
        email = (TextView) findViewById(R.id.email);
        ownedRepos = (Button) findViewById(R.id.ownedReposBtn);

        extras = getIntent().getExtras();
        newString = extras.getString("STRING_I_NEED");

        System.out.println(newString);

        loadData();


    }

    private void loadData() {

        final GitHubUserEndPoints apiService = APIClient.getClient().create(GitHubUserEndPoints.class);

        Call<GitHubUser> call = apiService.getUser(newString);
        call.enqueue(new Callback<GitHubUser>() {
            @Override
            public void onResponse(Call<GitHubUser> call, Response<GitHubUser> response) {
                // Image
                ImageDownloader task = new ImageDownloader();
                try {
                    myImage = task.execute(response.body().getAvatar()).get();
                } catch (Exception e){
                    e.printStackTrace();
                }

                avatarImg.setImageBitmap(myImage);
                avatarImg.getLayoutParams().height=220;
                avatarImg.getLayoutParams().width=220;

                // TextViews
                if (response.body().getName() == null) {
                    usernameTV.setText("No Username Provided");
                } else {
                    usernameTV.setText("Username: " + response.body().getName());
                }
                followersTV.setText("Followers: " + response.body().getFollowers());
                followingTV.setText("Following: " + response.body().getFollowing());
                logIn.setText("Login: " + response.body().getLogin());
                if (response.body().getEmail() == null) {
                    email.setText("No Email provided");
                } else {
                    email.setText("Email: " + response.body().getEmail());
                }
            }

            @Override
            public void onFailure(Call<GitHubUser> call, Throwable t) {
                // Log error since the request failed

            }
        });

    }


    public void loadOwnRepos(View view) {



    }
}
