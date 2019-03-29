package com.example.testandroidapplication.Presenter.createProfile;

import com.example.testandroidapplication.objects.Venue;

public interface IVenueProfileCreationContract {

    interface Presenter{
        void validateVenueObject(Venue venue);
    }

    interface View{
        void buildVenueObject();
        void showToast(String msg);
        void showError(String error);
    }
}
