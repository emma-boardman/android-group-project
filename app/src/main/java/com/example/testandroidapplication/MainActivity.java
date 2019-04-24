package com.example.testandroidapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.testandroidapplication.View.registerUser.RegisterFragment;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private LoginFragment loginView = new LoginFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.landr_toolbar);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container,
                loginView, "loginFragment").addToBackStack("loginFragment").commit();

    }

}
