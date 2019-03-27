package com.example.testandroidapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private LoginFragment loginView = new LoginFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.landr_toolbar);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container,
                loginView, "loginFragment").addToBackStack(null).commit();

    }
}
