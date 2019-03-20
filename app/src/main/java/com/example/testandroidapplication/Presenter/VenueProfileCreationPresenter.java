package com.example.testandroidapplication.Presenter;

import android.util.Log;
import android.view.View;

import com.example.testandroidapplication.helper.HttpJsonParser;
import com.example.testandroidapplication.helper.Validator;
import com.example.testandroidapplication.helper.WebClientMethods;
import com.example.testandroidapplication.objects.Venue;

import java.io.IOException;

public class VenueProfileCreationPresenter {

    private View view;
    private static Validator validator;
    private Venue venue;
;

    public static void processVenueObject(Venue venue){

//        if (validator.isVenueObjectValid(venue)){
//            HttpJsonParser httpJsonParser = new HttpJsonParser();
//            try {
//                String jsonString = httpJsonParser.toJSONObject(venue);
//                Log.i("JSON String:", jsonString);
                WebClientMethods.createVenueProfile(venue);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

        }


    }

