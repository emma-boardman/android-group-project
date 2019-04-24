package com.example.testandroidapplication.Presenter.createProfile;

import android.os.AsyncTask;

import com.example.testandroidapplication.objects.Artist;
import com.example.testandroidapplication.objects.Tags;
import com.example.testandroidapplication.utils.WebClientMethods;

import java.util.List;

public class ArtistProfileCreationPresenter implements IArtistProfileCreationContract.Presenter {

    private final IArtistProfileCreationContract.View view;

    public ArtistProfileCreationPresenter(IArtistProfileCreationContract.View view) {
        this.view = view;
    }

    public void validateArtistObject(Artist artist) {
        // add validation methods for each field via an artist class validation methods
        new CreateNewProfileAsyncTask(view, artist).execute();
    }

    public void readArtistTags() {

        new ReadArtistTagsAsyncTask(view).execute();

    }

    private static class CreateNewProfileAsyncTask extends AsyncTask<String, String, String> {
        private final IArtistProfileCreationContract.View view;
        private final Artist artistForAsync;

        private CreateNewProfileAsyncTask(IArtistProfileCreationContract.View view, Artist artistForAsync) {
            this.view = view;
            this.artistForAsync = artistForAsync;
        }

        @Override
        protected String doInBackground(String... params) {
            return WebClientMethods.createArtistProfile(artistForAsync);
        }

    }

    private static class ReadArtistTagsAsyncTask extends AsyncTask<String, String, Tags> {

        private final IArtistProfileCreationContract.View view;

        private ReadArtistTagsAsyncTask(IArtistProfileCreationContract.View view) {
            this.view = view;
        }

        @Override
        protected Tags doInBackground(String... params) {
            return WebClientMethods.readArtistTags();
        }

        protected void onPostExecute(final Tags tagResult) {
            List<String> experienceList = initialiseSpinner(tagResult, "Experience", "Select Years of Experience");
            List<String> genreList = initialiseSpinner(tagResult, "Genre", "Select Genre");
            List<String> instrumentsList = initialiseSpinner(tagResult, "Instruments", "Select Instruments");
            List<String> groupTypeList = initialiseSpinner(tagResult, "Group Type", "Select Artist Type");
            List<String> lookingForList = initialiseSpinner(tagResult, "Looking For", "Select Looking For");

            view.showExperienceSpinner(experienceList);
            view.showGenreSpinner(genreList);
            view.showInstrumentsSpinner(instrumentsList);
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
