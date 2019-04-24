package com.example.testandroidapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MessagingFragment extends Fragment {

    Button login, register;

    FirebaseUser firebaseUser;


    @Override
    public void onStart() {
        super.onStart();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        //check if user is null
        if (firebaseUser != null) {
            Intent intent = new Intent(getActivity(), MessagingWindowFragment.class);
            startActivity(intent);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_messaging, container, false);

        login = v.findViewById(R.id.btn_login);
        register = v.findViewById(R.id.btn_register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
                //updateToken(FirebaseInstanceId.getInstance().getToken());
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
                //updateToken(FirebaseInstanceId.getInstance().getToken());
            }
        });

        return v;
    }
}


    //might be able to use

    /*
    private void updateToken(String token){
        DatabaseReference tokenReference = FirebaseDatabase.getInstance().getReference("Tokens");
        Token token1 = new Token(token);
        tokenReference.child(firebaseUser.getUid()).setValue(token1);
    }
    */
/*
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

