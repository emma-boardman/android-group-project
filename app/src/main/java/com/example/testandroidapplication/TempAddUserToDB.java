package com.example.testandroidapplication;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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
    private static final String BASE_URL = "http://40414669.wdd.napier.ac.uk/users";
    private static String STRING_EMPTY = "";
    private EditText userNameEditText;
    private EditText userEmailEditText;
    private EditText userPasswordEditText;
    private String userName;
    private String userEmail;
    private String userPassword;
    private Button addButton;
    private int success;
    private ProgressDialog pDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_add_user_to_db);
        userNameEditText = findViewById(R.id.txtUserNameAdd);
        userEmailEditText =  findViewById(R.id.txtUserEmailAdd);
        userPasswordEditText = findViewById(R.id.txtUserPasswordAdd);
        addButton =  findViewById(R.id.addNewUserBtn);
    }
}
