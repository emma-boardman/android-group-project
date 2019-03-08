package com.example.testandroidapplication;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;

import com.example.testandroidapplication.helper.CheckNetworkStatus;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addNewBtn = findViewById(R.id.addNewBtn);
        Button btn_messaging = findViewById(R.id.btn_messaging);

        btn_messaging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Messaging.class);
                startActivity(i);
            }
        });

        addNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check for network connectivity
                if (CheckNetworkStatus.isNetworkAvailable(getApplicationContext())) {
                    Intent i = new Intent(getApplicationContext(), TempAddUserToDB.class);
                    startActivity(i);
                } else {
                    //Display error message if not connected to internet
                    Toast.makeText(MainActivity.this,
                            "Unable to connect to internet",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
    }




}
