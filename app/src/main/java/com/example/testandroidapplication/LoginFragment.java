package com.example.testandroidapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.AsyncTask;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testandroidapplication.helper.CheckNetworkStatus;
import com.example.testandroidapplication.helper.HttpJsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginFragment extends Fragment {

    private static final String KEY_SUCCESS = "success";
    private static final String KEY_EMAIL = "Email";
    private static final String KEY_PASSWORD = "Password";
    private static final String BASE_URL = "http://40414669.wdd.napier.ac.uk/inc/";
    private EditText userEmailEditText;
    private EditText userPasswordEditText;
    private String userEmail;
    private String userPassword;
    private int success;
    private RegisterFragmentVOrA registerVOrA = new RegisterFragmentVOrA();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main,container, false);

        userEmailEditText = v.findViewById(R.id.txtUserNameLogin);
        userPasswordEditText = v.findViewById(R.id.txtUserPasswordLogin);
        Button loginBtn =  v.findViewById(R.id.loginBtn);
        Button addNewBtn = v.findViewById(R.id.addNewBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if network is available, validate the user input
                if (CheckNetworkStatus.isNetworkAvailable(getActivity().getApplicationContext())) {
                    validateUser();

                } else {
                    //Display error message if not connected to internet
                    Toast.makeText(getActivity(),
                            "Unable to connect to internet",
                            Toast.LENGTH_LONG).show();
                }
            }

        });

        addNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check for network connectivity
                if (CheckNetworkStatus.isNetworkAvailable(getActivity().getApplicationContext())) {

                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container,
                            registerVOrA).commit();
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

        return v;
    }



    private void validateUser() {
        String STRING_EMPTY = "";
        if (!STRING_EMPTY.equals(userEmailEditText.getText().toString()) &&
                !STRING_EMPTY.equals(userPasswordEditText.getText().toString())) {

            userEmail = userEmailEditText.getText().toString();
            userPassword = userPasswordEditText.getText().toString();
            new LoginFragment.ValidateUserAsyncTask().execute();
        } else {
            Toast.makeText(getActivity(),
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

            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    if (success == 1) {
                        //Display success message
                        Toast.makeText(getActivity(),
                                "User Validated", Toast.LENGTH_LONG).show();

                        Intent i = new Intent(getActivity().getApplicationContext(),
                                ProfileActivity.class);
                        startActivity(i);

                        getActivity().finish();

                    } else {
                        Toast.makeText(getActivity(),
                                "Some error occurred while adding user",
                                Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
    }
}
