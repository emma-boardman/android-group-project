package com.example.testandroidapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testandroidapplication.helper.CheckNetworkStatus;

import com.google.firebase.auth.FirebaseAuth;

import com.example.testandroidapplication.helper.HttpJsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;



public class MainActivity extends AppCompatActivity {

    private LoginFragment loginView = new LoginFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.landr_toolbar);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container, loginView).commit();

        userEmailEditText = findViewById(R.id.txtUserNameLogin);
        userPasswordEditText = findViewById(R.id.txtUserPasswordLogin);
        Button loginBtn = findViewById(R.id.loginBtn);

        Button addNewBtn = findViewById(R.id.addNewBtn);
        Button btn_messaging = findViewById(R.id.btn_messaging);

        btn_messaging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Messaging.class);
                startActivity(i);
                finish();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if network is available, validate the user input
                if (CheckNetworkStatus.isNetworkAvailable(getApplicationContext())) {
                    validateUser();

                } else {
                    //Display error message if not connected to internet
                    Toast.makeText(MainActivity.this,
                            "Unable to connect to internet",
                            Toast.LENGTH_LONG).show();
                }
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



    private void validateUser() {
        String STRING_EMPTY = "";
        if (!STRING_EMPTY.equals(userEmailEditText.getText().toString()) &&
                !STRING_EMPTY.equals(userPasswordEditText.getText().toString())) {

            userEmail = userEmailEditText.getText().toString();
            userPassword = userPasswordEditText.getText().toString();
            new ValidateUserAsyncTask().execute();
        } else {
            Toast.makeText(MainActivity.this,
                    "One or more fields left empty!",
                    Toast.LENGTH_LONG).show();

        }
    }

    private class ValidateUserAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpJsonParser httpJsonParser = new HttpJsonParser();
            Map<String, String> httpParams = new HashMap<>();
            //Populating request parameters
            httpParams.put(KEY_EMAIL, userEmail);
            httpParams.put(KEY_PASSWORD, userPassword);
            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                    BASE_URL + "validateUser.php", "POST", httpParams);
            try {
                success = jsonObject.getInt(KEY_SUCCESS);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {

            runOnUiThread(new Runnable() {
                public void run() {
                    if (success == 1) {
                        //Display success message
                        Toast.makeText(MainActivity.this,
                                "User Validated", Toast.LENGTH_LONG).show();

                        Intent i = new Intent(getApplicationContext(),
                                ProfileActivity.class);
                        startActivity(i);

                        finish();

                    } else {
                        Toast.makeText(MainActivity.this,
                                "Some error occurred while adding user",
                                Toast.LENGTH_LONG).show();

                    }
                }
            });
        }


    }

}
