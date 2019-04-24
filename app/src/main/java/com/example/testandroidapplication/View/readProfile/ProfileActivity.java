package com.example.testandroidapplication.View.readProfile;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.testandroidapplication.R;
import com.example.testandroidapplication.utils.ArtistResult;
import com.example.testandroidapplication.utils.CheckNetworkStatus;
import com.example.testandroidapplication.utils.WebClientMethods;

public class ProfileActivity extends AppCompatActivity {


    private String artistUserIdforTesting = "test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        if (CheckNetworkStatus.isNetworkAvailable(getApplicationContext())) {
//            readArtistProfile(artistUserIdforTesting);
        } else {
//            Toast.makeText(ProfileActivity.this,
//                    "Unable to connect to internet",
//                    Toast.LENGTH_LONG).show();

        }


        Button btn_messaging = findViewById(R.id.btn_messaging);

        btn_messaging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this, ProfileActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

//    private void readArtistProfile(String artistUserIdforTesting) {
//        new ReadArtistProfileAsyncTask().execute();
//    }

//    private class ReadArtistProfileAsyncTask extends AsyncTask<String, String, ArtistResult> {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected ArtistResult doInBackground(String... params) {
////            return new WebClientMethods().readArtistProfile(artistUserIdforTesting);
//
//        }

//        protected void onPostExecute(final ArtistResult result) {

//            runOnUiThread(new Runnable() {
//                public void run() {
//                    if (result.isSuccess()) {
//                        //Display success message
//                        Toast.makeText(ReferenceHttpAsyncTasksForUI.this,
//                                "Artist profile returned (see LogCat 'tagreceiveddata')", Toast.LENGTH_LONG).show();
////                        Log.i("artist:", result.getArtist().getName());
//                        //Finish ths activity
//                        finish();
//
//                    } else {
//                        Toast.makeText(ReferenceHttpAsyncTasksForUI.this,
//                                "Artist profile could not be returned",
//                                Toast.LENGTH_LONG).show();
//
//                    }
//                }
//            });
        }
//
//    }
//}
