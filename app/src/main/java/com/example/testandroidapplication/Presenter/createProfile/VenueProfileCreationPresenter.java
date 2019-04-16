package com.example.testandroidapplication.Presenter.createProfile;

import android.os.AsyncTask;

import com.example.testandroidapplication.utils.WebClientMethods;
import com.example.testandroidapplication.objects.Venue;

public class VenueProfileCreationPresenter implements IVenueProfileCreationContract.Presenter {

    private final IVenueProfileCreationContract.View view;
    private Venue venueForAsync;

    public VenueProfileCreationPresenter(IVenueProfileCreationContract.View view){
        this.view = view;
    }

    public void validateVenueObject(Venue venue) {
        // add validation methods for each field from validation class
        if (venue.getUser().getName().contains("invalid")){
            // show error for that text input
        }
        venueForAsync = venue;

        new CreateNewProfileAsyncTask().execute();
    }

    private class CreateNewProfileAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            return WebClientMethods.createVenueProfile(venueForAsync);
        }

        protected void onPostExecute(final String result) {
                    if (result.equals("1")) {
                        //Display success message
                        view.showToast("Profile Added");
                    } else {
                        view.showToast("Some error occurred while adding profile");
                    }
                }
            };
        }
