package com.example.testandroidapplication.Presenter.createProfile;

import com.example.testandroidapplication.objects.Artist;
import com.example.testandroidapplication.objects.Tags;

import java.util.ArrayList;

public interface IArtistProfileCreationContract {

    interface Presenter{
        void validateArtistObject(Artist artist);
        void readArtistTags();
        void populateSpinners(Tags tagResult);
    }

    interface View{
        void buildArtistObject();
        void showToast(String msg);
        void showError(String error);
        void showExperienceSpinner(ArrayList<String> tagList);
        void showGenreSpinner(ArrayList<String> tagList);
        void showInstrumentsSpinner(ArrayList<String> tagList);
        void showGroupTypeSpinner(ArrayList<String> tagList);
        void showLookingForSpinner(ArrayList<String> tagList);
    }
}
