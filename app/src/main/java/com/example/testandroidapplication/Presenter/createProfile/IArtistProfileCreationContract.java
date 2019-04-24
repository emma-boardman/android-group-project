package com.example.testandroidapplication.Presenter.createProfile;

import com.example.testandroidapplication.objects.Artist;

import java.util.List;

public interface IArtistProfileCreationContract {

    interface Presenter{
        void validateArtistObject(Artist artist);
        void readArtistTags();
    }

    interface View{
        void buildArtistObject();

        void showExperienceSpinner(List<String> tagList);
        void showGenreSpinner(List<String> tagList);
        void showInstrumentsSpinner(List<String> tagList);
        void showGroupTypeSpinner(List<String> tagList);
        void showLookingForSpinner(List<String> tagList);
    }
}
