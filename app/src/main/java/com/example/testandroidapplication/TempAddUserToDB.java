package com.example.testandroidapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.example.testandroidapplication.helper.HttpJsonParser;
import com.example.testandroidapplication.helper.CheckNetworkStatus;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TempAddUserToDB extends AppCompatActivity {

    private static final String KEY_SUCCESS = "success";
    private static final String KEY_USER_NAME = "User_Name";
    private static final String KEY_EMAIL = "Email";
    private static final String KEY_PASSWORD = "Password";
    private static final String BASE_URL = "http://40414669.wdd.napier.ac.uk/inc/";
    private EditText userNameEditText;
    private EditText userEmailEditText;
    private EditText userPasswordEditText;
    private String userName;
    private String userEmail;
    private String userPassword;
    private int success;
    private ProgressDialog pDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_add_user_to_db);
        userNameEditText = findViewById(R.id.txtUserNameAdd);
        userEmailEditText =  findViewById(R.id.txtUserEmailAdd);
        userPasswordEditText = findViewById(R.id.txtUserPasswordAdd);
        Button addButton = findViewById(R.id.addNewUserBtn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CheckNetworkStatus.isNetworkAvailable(getApplicationContext())) {
                    addUser();
                } else {
                    Toast.makeText(TempAddUserToDB.this,
                            "Unable to connect to internet",
                            Toast.LENGTH_LONG).show();

                }

            }
        });
    }

    private void addUser() {
        String STRING_EMPTY = "";
        if (!STRING_EMPTY.equals(userNameEditText.getText().toString()) &&
                !STRING_EMPTY.equals(userEmailEditText.getText().toString()) &&
                !STRING_EMPTY.equals(userPasswordEditText.getText().toString())) {

            userName = userNameEditText.getText().toString();
            userEmail = userEmailEditText.getText().toString();
            userPassword = userPasswordEditText.getText().toString();
            new AddUserAsyncTask().execute();
        } else {
            Toast.makeText(TempAddUserToDB.this,
                    "One or more fields left empty!",
                    Toast.LENGTH_LONG).show();

        }
    }

    private class AddUserAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Display progress bar
//            pDialog = new ProgressDialog(TempAddUserToDB.this);
//            pDialog.setMessage("Adding User. Please wait...");
//            pDialog.setIndeterminate(false);
//            pDialog.setCancelable(false);
//            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpJsonParser httpJsonParser = new HttpJsonParser();
            Map<String, String> httpParams = new HashMap<>();
            //Populating request parameters
            httpParams.put(KEY_USER_NAME, userName);
            httpParams.put(KEY_EMAIL, userEmail);
            httpParams.put(KEY_PASSWORD, userPassword);
            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                    BASE_URL + "addUser.php", "POST", httpParams);
            try {
                success = jsonObject.getInt(KEY_SUCCESS);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {
//            pDialog.dismiss();
            runOnUiThread(new Runnable() {
                public void run() {
                    if (success == 1) {
                        //Display success message
                        Toast.makeText(TempAddUserToDB.this,
                                "User Added", Toast.LENGTH_LONG).show();
                        Intent i = getIntent();
                        //send result code 20 to notify about movie update
                        setResult(20, i);
                        //Finish ths activity and go back to listing activity
                        finish();

                    } else {
                        Toast.makeText(TempAddUserToDB.this,
                                "Some error occurred while adding user",
                                Toast.LENGTH_LONG).show();

                    }
                }
            });
        }

    }
}
