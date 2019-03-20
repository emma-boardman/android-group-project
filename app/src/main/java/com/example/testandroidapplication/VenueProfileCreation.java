package com.example.testandroidapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.example.testandroidapplication.objects.Venue;

import java.io.IOException;

public class VenueProfileCreation extends Fragment {

    private Venue venue;
    private EditText venueTaglineEditText;
    private EditText venueLocationEditText;
    private EditText venueDescriptionEditText;

    private String venueTaglineInput;
    private String venueLocationInput;
    private String venueDescriptionInput;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.venue_profile_creation, container, false);

        venueTaglineEditText = v.findViewById(R.id.venue_tagline);
        venueLocationEditText = v.findViewById(R.id.venue_location);
        venueDescriptionEditText = v.findViewById(R.id.venue_description);
        Button createProfileBtn = v.findViewById(R.id.venue_create_profile);

        createProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // if network is available, validate the user input
                if (CheckNetworkStatus.isNetworkAvailable(getActivity().getApplicationContext())) {

                    if (venueTaglineEditText.getText() != null){
                        venueTaglineInput = venueTaglineEditText.getText().toString();
                    } else {
                        venueTaglineInput = "not populated";
                    }

                    if (venueLocationEditText.getText() != null){
                        venueLocationInput = venueLocationEditText.getText().toString();
                    } else {
                        venueLocationInput = "not populated";
                    }

                    if (venueDescriptionEditText.getText() != null){
                        venueDescriptionInput = venueDescriptionEditText.getText().toString();
                    } else {
                        venueDescriptionInput = "not populated";
                    }

                    venue = new Venue("Id populated from firebase",
                            "Name populated from Firebase",
                                        "email populated from Firebase",
                                     "password populated from Firebase",
                                    venueTaglineInput,
                                    "hard-coded tags",
                            venueDescriptionInput,
                            "hard-coded facebook link",
                            "hard-coded instagram link",
                            "hard-coded twitter link",
                            "hard-coded web link",
                            venueLocationInput,
                            5,
                    "hard-coded profile image",
                           "hard-coded faq",
                            "hard-coded address 1",
                            "hard-coded postcode",
                             456789);

                    new CreateNewProfileAsyncTask().execute();

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

    private class CreateNewProfileAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            return new WebClientMethods().createVenueProfile(venue);

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
