package com.example.testandroidapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ContentActivity extends AppCompatActivity {

    private ProfileFragment profileView = new ProfileFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_activity);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_fragement_container,
                profileView, "loginFragment").addToBackStack("loginFragment").commit();

    }
}
