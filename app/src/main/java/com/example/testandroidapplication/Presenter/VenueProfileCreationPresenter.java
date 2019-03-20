package com.example.testandroidapplication.Presenter;

import android.view.View;

import com.example.testandroidapplication.helper.Validator;
import com.example.testandroidapplication.helper.WebClientMethods;
import com.example.testandroidapplication.objects.Venue;

public class VenueProfileCreationPresenter {

    private View view;
    private Validator validator;
    private Venue venue;
;
    public VenueProfileCreationPresenter(View view){
        this.view = view;
    }

    public void processVenueObject(Venue venue){
        if (validator.isVenueObjectValid(venue)){
            WebClientMethods.createVenueProfile(venue);
        };


    }


}
