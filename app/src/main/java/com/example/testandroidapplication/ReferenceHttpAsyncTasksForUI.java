package com.example.testandroidapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testandroidapplication.utils.ArtistResult;
import com.example.testandroidapplication.utils.CheckNetworkStatus;
import com.example.testandroidapplication.utils.GigResult;
import com.example.testandroidapplication.utils.VenueResult;
import com.example.testandroidapplication.utils.WebClientMethods;

public class ReferenceHttpAsyncTasksForUI extends AppCompatActivity {

    private EditText userNameEditText;
    private EditText userEmailEditText;
    private EditText userPasswordEditText;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String artistUserIdforTesting = "test";
    private String venueUserIdforTesting = "testVenue";
    private int gigUserIdforTesting = 1;

    // Included methods: create new user ; read artist profile ; read venue profile ; read gig information

    //  File Structure:
        // 1. Buttons & Set-up
        // 2. Methods
        // 3. Async tasks

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_add_user_to_db);

        // CREATE NEW USER ACCOUNT - button and onclick setup
        userNameEditText = findViewById(R.id.txtUserNameAdd);
        userEmailEditText =  findViewById(R.id.txtUserEmailAdd);
        userPasswordEditText = findViewById(R.id.txtUserPasswordAdd);

        Button createNewUserButton = findViewById(R.id.createNewUserBtn);

        createNewUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CheckNetworkStatus.isNetworkAvailable(getApplicationContext())) {
                    createNewUser();
                } else {
                    Toast.makeText(ReferenceHttpAsyncTasksForUI.this,
                            "Unable to connect to internet",
                            Toast.LENGTH_LONG).show();

                }

            }
        });

        // READ ARTIST PROFILE -  button and onclick setup
        // Currently hardcoded to userId 5
        Button readArtistProfileButton = findViewById(R.id.readArtistProfileBtn);

        readArtistProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CheckNetworkStatus.isNetworkAvailable(getApplicationContext())) {
                   readArtistProfile(artistUserIdforTesting);
                } else {
                    Toast.makeText(ReferenceHttpAsyncTasksForUI.this,
                            "Unable to connect to internet",
                            Toast.LENGTH_LONG).show();

                }

            }
        });

        // READ VENUE PROFILE - button and onclick setup
        // Currently hardcoded to userId 29
        Button readVenueProfileButton = findViewById(R.id.readVenueProfileBtn);

        readVenueProfileButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (CheckNetworkStatus.isNetworkAvailable(getApplicationContext())){
                    readVenueProfile();
                } else {
                    Toast.makeText(ReferenceHttpAsyncTasksForUI.this, "Unable to connect to internet", Toast.LENGTH_LONG).show();
                }
            }
        });

        // READ GIG INFORMATION - button and onclick setup
        // Currently hardcoded to gigId 1
        Button readGigInfoButton = findViewById(R.id.readGigInfoBtn);

        readGigInfoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (CheckNetworkStatus.isNetworkAvailable(getApplicationContext())){
                    readGigInformation();
                } else {
                    Toast.makeText(ReferenceHttpAsyncTasksForUI.this, "Unable to connect to internet", Toast.LENGTH_LONG).show();
                }
            }
        });
    }



    // CREATE NEW USER ACCOUNT - method

    private void createNewUser() {

        String STRING_EMPTY = "";
        if (!STRING_EMPTY.equals(userNameEditText.getText().toString()) &&
                !STRING_EMPTY.equals(userEmailEditText.getText().toString()) &&
                !STRING_EMPTY.equals(userPasswordEditText.getText().toString())) {

            userName = userNameEditText.getText().toString();
            userEmail = userEmailEditText.getText().toString();
            userPassword = userPasswordEditText.getText().toString();
            new CreateNewUserAsyncTask().execute();
        } else {
            Toast.makeText(ReferenceHttpAsyncTasksForUI.this,
                    "One or more fields left empty!",
                    Toast.LENGTH_LONG).show();

        }
    }


    // READ ARTIST PROFILE - method

    private void readArtistProfile(String artistUserIdforTesting) {
       new ReadArtistProfileAsyncTask().execute();
    }

    // READ VENUE PROFILE - method

    private void readVenueProfile() {
        new ReadVenueProfileAsyncTask().execute();
    }

    // READ GIG INFORMATION - method

    private void readGigInformation() {
        new ReadGigInformationAsyncTask().execute();
    }


    // CREATE NEW USER ACCOUNT - Async Task

    private class CreateNewUserAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
           return new WebClientMethods().createUserAccount(userName, userEmail, userPassword);

        }


        protected void onPostExecute(final String result) {

            runOnUiThread(new Runnable() {
                public void run() {
                    if (result.equals("1")) {
                        //Display success message
                        Toast.makeText(ReferenceHttpAsyncTasksForUI.this,
                                "User Added", Toast.LENGTH_LONG).show();

                        //Finish ths activity
                        finish();

                    } else {
                        Toast.makeText(ReferenceHttpAsyncTasksForUI.this,
                                "Some error occurred while adding user",
                                Toast.LENGTH_LONG).show();

                    }
                }
            });
        }

    }

    // READ ARTIST PROFILE - Async Task

    private class ReadArtistProfileAsyncTask extends AsyncTask<String, String, ArtistResult> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArtistResult doInBackground(String... params) {
            return new WebClientMethods().readArtistProfile(artistUserIdforTesting);

        }

        protected void onPostExecute(final ArtistResult result) {

            runOnUiThread(new Runnable() {
                public void run() {
                    if (result.isSuccess()) {
                        //Display success message
                        Toast.makeText(ReferenceHttpAsyncTasksForUI.this,
                                "Artist profile returned (see LogCat 'tagreceiveddata')", Toast.LENGTH_LONG).show();
//                        Log.i("artist:", result.getArtist().getName());
                        //Finish ths activity
                        finish();

                    } else {
                        Toast.makeText(ReferenceHttpAsyncTasksForUI.this,
                                "Artist profile could not be returned",
                                Toast.LENGTH_LONG).show();

                    }
                }
            });
        }

    }

    private class ReadVenueProfileAsyncTask extends AsyncTask<String, String, VenueResult> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected VenueResult doInBackground(String... params) {
            return new WebClientMethods().readVenueProfile();

        }

        protected void onPostExecute(final VenueResult result) {

            runOnUiThread(new Runnable() {
                public void run() {
                    if (result.isSuccess()) {
                        //Display success message
                        Toast.makeText(ReferenceHttpAsyncTasksForUI.this,
                                "Venue profile returned (see LogCat 'tagreceiveddata')", Toast.LENGTH_LONG).show();
                        //Finish ths activity
                        finish();

                    } else {
                        Toast.makeText(ReferenceHttpAsyncTasksForUI.this,
                                "Venue profile could not be returned",
                                Toast.LENGTH_LONG).show();

                    }
                }
            });
        }

    }

    private class ReadGigInformationAsyncTask extends AsyncTask<String, String, GigResult> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected GigResult doInBackground(String... params) {
            return new WebClientMethods().readGigInformation();

        }

        protected void onPostExecute(final GigResult result) {

            runOnUiThread(new Runnable() {
                public void run() {
                    if (result.isSuccess()) {
                        //Display success message
                        Toast.makeText(ReferenceHttpAsyncTasksForUI.this,
                                "Gig information returned (see LogCat 'tagreceiveddata')", Toast.LENGTH_LONG).show();
                        //Finish ths activity
                        finish();

                    } else {
                        Toast.makeText(ReferenceHttpAsyncTasksForUI.this,
                                "Gig information could not be returned",
                                Toast.LENGTH_LONG).show();

                    }
                }
            });
        }

    }

}
