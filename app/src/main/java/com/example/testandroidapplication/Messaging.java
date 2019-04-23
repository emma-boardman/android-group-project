package com.example.testandroidapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.testandroidapplication.Notifications.Data;
import com.example.testandroidapplication.Notifications.Token;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class Messaging extends AppCompatActivity {

    Button login, register;

    FirebaseUser firebaseUser;


    @Override
    protected void onStart() {
        super.onStart();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        //check if user is null
        if(firebaseUser != null) {
            Intent intent = new Intent(Messaging.this, MessagingWindow.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);


        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.btn_register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Messaging.this, LoginActivity.class));
                //updateToken(FirebaseInstanceId.getInstance().getToken());
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Messaging.this, LoginActivity.class));
                //updateToken(FirebaseInstanceId.getInstance().getToken());
            }
        });
    }

    //might be able to use

    /*
    private void updateToken(String token){
        DatabaseReference tokenReference = FirebaseDatabase.getInstance().getReference("Tokens");
        Token token1 = new Token(token);
        tokenReference.child(firebaseUser.getUid()).setValue(token1);
    }
    */
}
