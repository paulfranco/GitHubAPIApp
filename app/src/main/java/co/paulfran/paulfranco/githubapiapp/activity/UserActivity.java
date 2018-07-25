package co.paulfran.paulfranco.githubapiapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import co.paulfran.paulfranco.githubapiapp.R;

public class UserActivity extends AppCompatActivity{

    ImageView avatarImg;
    TextView usernameTV;
    TextView followersTV;
    TextView followingTV;
    TextView logIn;
    TextView email;
    Button ownedRepos;

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

        Bundle extras;
        String newString;

        extras = getIntent().getExtras();
        newString = extras.getString("STRING_I_NEED");

        System.out.println(newString);


    }

    public void loadOwnRepos(View view) {



    }
}
