package com.example.testandroidapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class ContentActivity extends AppCompatActivity {

    private WelcomeFragement welcomeFragement =  new WelcomeFragement();
    private ProfileFragment profileView = new ProfileFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_activity);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container,
                welcomeFragement, "loginFragment").addToBackStack("loginFragment").commit();


        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        //bottomNav.setOnNavigationItemSelectedListener(navListener);

    }
}
