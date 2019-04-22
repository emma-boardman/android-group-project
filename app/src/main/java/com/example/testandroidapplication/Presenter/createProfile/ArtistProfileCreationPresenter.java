package com.example.testandroidapplication.Presenter.createProfile;

import android.os.AsyncTask;
import android.util.Log;

import com.example.testandroidapplication.objects.Artist;
import com.example.testandroidapplication.objects.Tags;
import com.example.testandroidapplication.utils.WebClientMethods;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ArtistProfileCreationPresenter implements IArtistProfileCreationContract.Presenter {

    private final IArtistProfileCreationContract.View view;
    private Artist artistForAsync;
    private String tagCategoryForAsync;

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

    public void readArtistTags(){

        new ReadArtistTagsAsyncTask().execute();

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

    public class ReadArtistTagsAsyncTask extends AsyncTask<String, String, Tags> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Tags doInBackground(String... params) {
            new WebClientMethods();
            return WebClientMethods.readArtistTags();
        }

        protected void onPostExecute(final Tags tagResult) {
            populateSpinners(tagResult);
        }
    }

    public void populateSpinners(Tags tagResult){

        ArrayList<String>  experienceList = (ArrayList<String>) tagResult.getTag("Experience");
        experienceList.add(0, "Select Years of Experience");
        ArrayList<String>  genreList = (ArrayList<String>) tagResult.getTag("Genre");
        genreList.add(0, "Select Genre");
        ArrayList<String>  instrumentsList = (ArrayList<String>) tagResult.getTag("Instruments");
        instrumentsList.add(0, "Select Instruments");
        ArrayList<String>  groupTypeList = (ArrayList<String>) tagResult.getTag("Group Type");
        groupTypeList.add(0, "Select Artist Type");
        ArrayList<String>  lookingForList = (ArrayList<String>) tagResult.getTag("Looking For");
        lookingForList.add(0, "Select Looking For");

        view.showExperienceSpinner(experienceList);
        view.showGenreSpinner(genreList);
        view.showInstrumentsSpinner(instrumentsList);
        view.showGroupTypeSpinner(groupTypeList);
        view.showLookingForSpinner(lookingForList);

    }




 }
