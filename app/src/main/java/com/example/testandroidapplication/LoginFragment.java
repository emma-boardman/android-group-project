package com.example.testandroidapplication;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.AsyncTask;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testandroidapplication.utils.CheckNetworkStatus;
import com.example.testandroidapplication.utils.HttpJsonParser;
import com.example.testandroidapplication.View.registerUser.RegisterFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginFragment extends Fragment {

    private EditText userEmailEditText;
    private EditText userPasswordEditText;
    private String userEmail;
    private String userPassword;
    private int success;
    private RegisterFragment register = new RegisterFragment();
    private FirebaseAuth auth;

    @Nullable
    @Override
    @TargetApi(26)
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main,container, false);

        userEmailEditText = v.findViewById(R.id.txtUserNameLogin);
        userPasswordEditText = v.findViewById(R.id.txtUserPasswordLogin);
        auth = FirebaseAuth.getInstance();
        Button loginBtn =  v.findViewById(R.id.loginBtn);
        Button addNewBtn = v.findViewById(R.id.addNewBtn);
        Button btn_messaging = v.findViewById(R.id.btn_messaging);
        Button btn_calender = v.findViewById(R.id.btn_calender);
        Button btn_test = v.findViewById(R.id.btn_test);

        btn_test.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ReferenceHttpAsyncTasksForUI.class);
                startActivity(i);
            }
        });

        /*btn_messaging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Messaging.class);
                startActivity(i);
            }
        });*/

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*Intent i = new Intent(getActivity().getApplicationContext(),
                        ContentActivity.class);
                startActivity(i);

                getActivity().finish();*/

                final String txt_email = userEmailEditText.getText().toString();
                String txt_password = userPasswordEditText.getText().toString();

                if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)) {
                    Toast.makeText(getActivity(), "Please enter your email and password.", Toast.LENGTH_SHORT).show();
                } else {
                    auth.signInWithEmailAndPassword(txt_email, txt_password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()) {
                                        Intent intent = new Intent(Objects.requireNonNull(getActivity()).getApplicationContext(),
                                                ContentActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.putExtra("userEmail", txt_email);
                                        startActivity(intent);
                                        getActivity().finish();
                                    } else {
                                        Toast.makeText(getActivity(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                // if network is available, validate the user input

                ///
                /// needs updating to work with Cals firebase Login
                ///
                /*
                if (CheckNetworkStatus.isNetworkAvailable(getActivity().getApplicationContext())) {
                    validateUser();

                } else {
                    //Display error message if not connected to internet
                    Toast.makeText(getActivity(),
                            "Unable to connect to internet",
                            Toast.LENGTH_LONG).show();
                }*/
            }

        });

        addNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check for network connectivity
                if (CheckNetworkStatus.isNetworkAvailable(getActivity().getApplicationContext())) {

                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container,
                            register, "registerFragment").commit();
                    /*Intent i = new Intent(getActivity().getApplicationContext(),
                            ReferenceHttpAsyncTasksForUI.class);
                    startActivity(i);*/
                } else {
                    //Display error message if not connected to internet
                    Toast.makeText(getActivity(),
                            "Unable to connect to internet",
                            Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), CalendarFragment.class);
                startActivity(i);

            }
        });

        return v;
    }

 }
