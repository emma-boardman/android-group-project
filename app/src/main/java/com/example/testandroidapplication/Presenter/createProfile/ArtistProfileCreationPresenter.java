package com.example.testandroidapplication.Presenter.createProfile;

import android.os.AsyncTask;

import com.example.testandroidapplication.objects.Artist;
import com.example.testandroidapplication.objects.Venue;
import com.example.testandroidapplication.utils.WebClientMethods;

public class ArtistProfileCreationPresenter implements IArtistProfileCreationContract.Presenter {

    private final IArtistProfileCreationContract.View view;
    private Artist artistForAsync;

    public ArtistProfileCreationPresenter(IArtistProfileCreationContract.View view){
        this.view = view;
    }

    public void validateArtistObject(Artist artist) {
        // add validation methods for each field from validation class
//        if (venue.getUser().getName().contains("invalid")){
//            // show error for that text input
//        }
        artistForAsync = artist;

        new CreateNewProfileAsyncTask().execute();
    }

    public class CreateNewProfileAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            new WebClientMethods();
            return WebClientMethods.createArtistProfile(artistForAsync);
        }



        protected void onPostExecute(final String result) {
            if (result.equals("1")) {
                //Display success message
//                view.showToast("Profile Added");
            } else {
//                view.showToast("Some error occurred while adding profile");
            }
        }
    }
        }
