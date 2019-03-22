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
    private EditText venueTaglineEditText;
    private EditText venueLocationEditText;
    private EditText venueDescriptionEditText;

    private String venueTaglineInput;
    private String venueLocationInput;
    private String venueDescriptionInput;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.venue_profile_creation, container, false);

        venueTaglineEditText = v.findViewById(R.id.venue_tagline);
        venueLocationEditText = v.findViewById(R.id.venue_location);
        venueDescriptionEditText = v.findViewById(R.id.venue_description);
        Button createProfileBtn = v.findViewById(R.id.venue_create_profile);

        createProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // if network is available, validate the user input
                if (CheckNetworkStatus.isNetworkAvailable(getActivity().getApplicationContext())) {

                    // iterate through optional fields & assign a variable for any filled in fields, assign to a variable dynamically created from hint
//                    int[] optionalEditTextIds = new int[]{R.id.venue_tagline, R.id.venue_location, R.id.venue_description, R.id.venue_create_profile};
//                    int i = 1;
//                    for (int id : ids) {
//                        EditText editText = v.findViewById(id);
//                        String editText.getHint() = editText.getText().toString();
//                    }


                    if (TextUtils.isEmpty(venueTaglineEditText.getText())){
                        venueTaglineInput = venueTaglineEditText.getText().toString();
                    } else {
                        venueTaglineInput = "not populated";
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

                    User newUser = new User
                            .UserBuilder("1", "Stone Temple Pilots", "stone@temple.com", "123")
                            .withTagline(venueTaglineInput)
                            .withLocation(venueLocationInput)
                            .withDescription(venueDescriptionInput)
                            .build();

                    venue = new Venue(newUser,
                            "an address",
                            "postcode",
                    "faq",
                    123456);


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
