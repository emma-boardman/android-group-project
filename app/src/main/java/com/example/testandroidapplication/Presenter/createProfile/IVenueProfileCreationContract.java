package com.example.testandroidapplication.Presenter.createProfile;

import com.example.testandroidapplication.objects.Venue;

import java.util.List;

public interface IVenueProfileCreationContract {

    interface Presenter{
        void validateVenueObject(Venue venue);
        void readVenueTags();
    }

    interface View{
        void buildVenueObject();

        void showGenreSpinner(List<String> tagList);
        void showGroupTypeSpinner(List<String> tagList);
        void showLookingForSpinner(List<String> tagList);
    }
}
