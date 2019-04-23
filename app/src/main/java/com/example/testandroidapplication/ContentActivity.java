package com.example.testandroidapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class ContentActivity extends AppCompatActivity {


    private ProfileFragment profileView = new ProfileFragment();
    private HomeFragment homeView = new HomeFragment();

    private ImageButton userProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_activity);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_fragement_container,
                homeView).commit();

        userProfile = findViewById(R.id.profile_button);
        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.content_fragement_container,
                        profileView).commit();
            }
        });


        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        //bottomNav.setOnNavigationItemSelectedListener(navListener);

    }
}
