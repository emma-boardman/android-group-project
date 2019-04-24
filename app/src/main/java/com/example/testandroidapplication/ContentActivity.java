package com.example.testandroidapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.testandroidapplication.utils.WebClientMethods;

import org.json.JSONException;
import org.json.JSONObject;

public class ContentActivity extends AppCompatActivity {

    private ProfileFragment profileView = new ProfileFragment();
    JSONObject userIdandType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_activity);
        Intent intent = getIntent();
        String userEmail = intent.getStringExtra("userEmail");
        new ReadIdandTypeAsyncTask(userEmail, profileView).execute();


        getSupportFragmentManager().beginTransaction().replace(R.id.content_fragement_container,
                profileView).commit();


        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        ImageButton profileBtn = findViewById(R.id.profile_button);

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.content_fragement_container,
                        profileView).commit();
            }
        });

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch(item.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_message:
                            selectedFragment = new MessagingWindowFragment();
                            break;
                        case R.id.nav_calender:
                            selectedFragment = new CalendarFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_fragement_container,
                            selectedFragment).commit();
                    return true;
                }
            };


    private static class ReadIdandTypeAsyncTask extends AsyncTask<String, String, JSONObject> {

        private ProfileFragment profileFragment;
        private final String userEmail;

        ReadIdandTypeAsyncTask(String userEmail, ProfileFragment profileFragment) {
            this.userEmail = userEmail;
            this.profileFragment = profileFragment;
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            return WebClientMethods.readUserIdandType(userEmail);
        }

        protected void onPostExecute(final JSONObject userIdAndType) {
            try {
                profileFragment.accessUserIdForDB(userIdAndType);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

}


