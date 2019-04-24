package com.example.testandroidapplication.Presenter.createProfile;

import android.os.AsyncTask;

import com.example.testandroidapplication.objects.Tags;
import com.example.testandroidapplication.utils.WebClientMethods;
import com.example.testandroidapplication.objects.Venue;

import java.util.List;

public class VenueProfileCreationPresenter implements IVenueProfileCreationContract.Presenter {

    private final IVenueProfileCreationContract.View view;
    private Venue venueForAsync;

    public VenueProfileCreationPresenter(IVenueProfileCreationContract.View view){
        this.view = view;
    }

    public void validateVenueObject(Venue venue) {
        // add validation methods for each field from validation class
//        if (venue.getUser().getName().contains("invalid")){
//            // show error for that text input
//        }
        venueForAsync = venue;

        new CreateNewProfileAsyncTask().execute();
    }

    public void readVenueTags(){

        new ReadVenueTagsAsyncTask(view).execute();

    }

    public class CreateNewProfileAsyncTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            new WebClientMethods();
            return WebClientMethods.createVenueProfile(venueForAsync);
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

    private static class ReadVenueTagsAsyncTask extends AsyncTask<String, String, Tags> {

        private final IVenueProfileCreationContract.View view;

        private ReadVenueTagsAsyncTask(IVenueProfileCreationContract.View view) {
            this.view = view;
        }

        @Override
        protected Tags doInBackground(String... params) {
            return WebClientMethods.readArtistTags();
        }

        protected void onPostExecute(final Tags tagResult) {
            List<String> genreList = initialiseSpinner(tagResult, "Genre", "Select preferred artist genre");
            List<String> groupTypeList = initialiseSpinner(tagResult, "Group Type", "Select preferred artist type");
            List<String> lookingForList = initialiseSpinner(tagResult, "Looking For", "Select the gigs you offer");

            view.showGenreSpinner(genreList);
            view.showGroupTypeSpinner(groupTypeList);
            view.showLookingForSpinner(lookingForList);
        }

        private List<String> initialiseSpinner(Tags tags, String category, String s) {
            List<String> spinnerContents = tags.getTag(category);
            spinnerContents.add(0, s);
            return spinnerContents;
        }
    }



}
