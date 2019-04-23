package com.example.testandroidapplication.Presenter.createProfile;

import com.example.testandroidapplication.objects.Artist;
import com.example.testandroidapplication.objects.Tags;

import java.util.List;

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
        void showExperienceSpinner(List<String> tagList);
        void showGenreSpinner(List<String> tagList);
        void showInstrumentsSpinner(List<String> tagList);
        void showGroupTypeSpinner(List<String> tagList);
        void showLookingForSpinner(List<String> tagList);
    }
}
