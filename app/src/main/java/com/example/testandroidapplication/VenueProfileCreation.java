package com.example.testandroidapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testandroidapplication.Presenter.VenueProfileCreationPresenter;
import com.example.testandroidapplication.helper.CheckNetworkStatus;
import com.example.testandroidapplication.helper.HttpJsonParser;
import com.example.testandroidapplication.helper.Validator;
import com.example.testandroidapplication.helper.WebClientMethods;
import com.example.testandroidapplication.objects.User;
import com.example.testandroidapplication.objects.Venue;

import java.io.IOException;

public class VenueProfileCreation extends Fragment {

    private Venue venue;
    private EditText venueNameEditText;
    private EditText venueTaglineEditText;
    private EditText venueLocationEditText;
    private EditText venueDescriptionEditText;
    private EditText venueFacebookEditText;
    private EditText venueTwitterEditText;
    private EditText venueInstagramEditText;
    private EditText venueWebsiteEditText;
    private EditText venueAddress1EditText;
    private EditText venuePostcodeEditText;

    private String venueNameInput;
    private String venueTaglineInput;
    private String venueLocationInput;
    private String venueDescriptionInput;
    private String venueFacebookInput;
    private String venueTwitterInput;
    private String venueInstagramInput;
    private String venueWebsiteInput;
    private String venueAddress1Input;
    private String venuePostcodeInput;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.venue_profile_creation, container, false);

        venueNameEditText = v.findViewById(R.id.venue_name);
        venueTaglineEditText = v.findViewById(R.id.venue_tagline);
        venueLocationEditText = v.findViewById(R.id.venue_location);
        venueDescriptionEditText = v.findViewById(R.id.venue_description);
        venueFacebookEditText = v.findViewById(R.id.venue_facebook);
        venueTwitterEditText = v.findViewById(R.id.venue_twitter);
        venueInstagramEditText = v.findViewById(R.id.venue_instagram);
        venueWebsiteEditText = v.findViewById(R.id.venue_webpage);
        venueAddress1EditText = v.findViewById(R.id.venue_address_line_1);
        venuePostcodeEditText = v.findViewById(R.id.venue_address_line_postcode);

        Button createProfileBtn = v.findViewById(R.id.venue_create_profile);

        createProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // if network is available, validate the user input
                if (CheckNetworkStatus.isNetworkAvailable(getActivity().getApplicationContext())) {

                    venueNameInput = venueNameEditText.getText().toString();
                    if (!venueNameInput.equals("")){
                        // run some validation, let the user know if there is a problem
                        Log.i("tag: ", "validation would be run here");
                    }


                    venueTaglineInput = venueTaglineEditText.getText().toString();
                    if (!venueTaglineInput.equals("")){
                        // run some validation, let the user know if there is a problem
                        Log.i("tag: ", "validation would be run here");
                    }

                    venueLocationInput = venueLocationEditText.getText().toString();
                    if (!venueLocationInput.equals("")){
                        // run some validation, let the user know if there is a problem
                        Log.i("tag: ", "validation would be run here");
                    }

                    venueDescriptionInput = venueDescriptionEditText.getText().toString();
                    if (!venueLocationInput.equals("")){
                        // run some validation, let the user know if there is a problem
                        Log.i("tag: ", "validation would be run here");
                    }

                    venueFacebookInput = venueFacebookEditText.getText().toString();
                    if (!venueFacebookInput.equals("")){
                        // run some validation, let the user know if there is a problem
                        Log.i("tag: ", "validation would be run here");
                    }

                    venueTwitterInput = venueTwitterEditText.getText().toString();
                    if (!venueTwitterInput.equals("")){
                        // run some validation, let the user know if there is a problem
                        Log.i("tag: ", "validation would be run here");
                    }

                    venueInstagramInput = venueInstagramEditText.getText().toString();
                    if (!venueInstagramInput.equals("")){
                        // run some validation, let the user know if there is a problem
                        Log.i("tag: ", "validation would be run here");
                    }

                    venueWebsiteInput = venueWebsiteEditText.getText().toString();
                    if (!venueWebsiteInput.equals("")){
                        // run some validation, let the user know if there is a problem
                        Log.i("tag: ", "validation would be run here");
                    }

                    venueAddress1Input = venueAddress1EditText.getText().toString();
                    if (!venueAddress1Input.equals("")){
                        // run some validation, let the user know if there is a problem
                        Log.i("tag: ", "validation would be run here");
                    }

                    venuePostcodeInput = venuePostcodeEditText.getText().toString();
                    if (!venuePostcodeInput.equals("")){
                        // run some validation, let the user know if there is a problem
                        Log.i("tag: ", "validation would be run here");
                    }

                    createVenueProfile();


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

    private void createVenueProfile(){

        User newUser = new User
                .UserBuilder("1", "Stone Temple Pilots", "stone@temple.com", "123")
                .withTagline(venueTaglineInput)
                .withLocation(venueLocationInput)
                .withDescription(venueDescriptionInput)
                .withFacebookLink(venueFacebookInput)
                .withTwitterLink(venueTwitterInput)
                .withInstagramLink(venueInstagramInput)
                .withWebPageLink(venueWebsiteInput)
                .build();
        Log.i("USER:", String.valueOf(newUser));

        venue = new Venue
                .VenueBuilder(newUser)
                .withAddress1(venueAddress1Input)
                .withPostcode(venuePostcodeInput)
                .build();

        new CreateNewProfileAsyncTask().execute();
    }


    private class CreateNewProfileAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            new WebClientMethods();
            return WebClientMethods.createVenueProfile(venue);

        }

        protected void onPostExecute(final String result) {

            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    if (result.equals("1")) {
                        //Display success message
                        Toast.makeText(getActivity(),
                                "Profile Added", Toast.LENGTH_LONG).show();

                        //Finish ths activity
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
