package com.example.testandroidapplication.Presenter.createProfile;

import com.example.testandroidapplication.objects.Artist;

public interface IArtistProfileCreationContract {

    interface Presenter{
        void validateArtistObject(Artist artist);
    }

    interface View{
        void buildArtistObject();
        void showToast(String msg);
        void showError(String error);
    }
}
