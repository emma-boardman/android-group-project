package com.example.testandroidapplication.utils;



import org.json.JSONException;
import org.json.JSONObject;


import com.example.testandroidapplication.objects.Artist;
import com.example.testandroidapplication.objects.Gig;
import com.example.testandroidapplication.objects.Venue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WebClientMethods {

    private static final String KEY_USER_ID = "User_Id";
    private static final String KEY_USER_NAME = "User_Name";
    private static final String KEY_EMAIL = "Email";
    private static final String KEY_PASSWORD = "Password";
    private static final String KEY_TAGLINE = "Tagline";
    private static final String KEY_DESCRIPTION = "Description";
    private static final String KEY_GIG_ID = "Gig_Id";
    private static final String KEY_DATA = "data";

    private static final String BASE_URL = "http://40414669.wdd.napier.ac.uk/inc/";

    private Validator validator;


    // CREATE

    public static String createUserAccount(String userID, String userName, String userEmail) {
        HttpJsonParser httpJsonParser = new HttpJsonParser();
        Map<String, String> httpParams = new HashMap<>();
        httpParams.put(KEY_USER_ID, userID);
        httpParams.put(KEY_USER_NAME, userName);
        httpParams.put(KEY_EMAIL, userEmail);
        JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                BASE_URL + "createUser.php", "POST", httpParams);
        try {
            return jsonObject.getString("success");
        } catch (JSONException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public static String createVenueProfile(Venue venue) {
        // write venue object to JSON string
        HttpJsonParser httpJsonParser = new HttpJsonParser();

        JSONObject jsonObject = null;
        JSONObject jsonVenue = null;
        try {
            jsonVenue = venue.toJson(venue);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            jsonObject = httpJsonParser.makeHttpPost(
                    jsonVenue);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            return jsonObject.getString("success");
        } catch (JSONException e) {
            e.printStackTrace();
            return e.getMessage();

        }
    }

    public static String createArtistProfile(Artist artist) {
        // write venue object to JSON string
        HttpJsonParser httpJsonParser = new HttpJsonParser();

        JSONObject jsonObject = null;
        JSONObject jsonArtist = null;
        try {
            jsonArtist = artist.toJson(artist);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            jsonObject = httpJsonParser.makeHttpPostToArtist(
                    jsonArtist);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            return jsonObject.getString("success");
        } catch (JSONException e) {
            e.printStackTrace();
            return e.getMessage();

        }
    }


    // READ


     public ArtistResult readArtistProfile(String artistId){
         HttpJsonParser httpJsonParser = new HttpJsonParser();
         Map<String, String> httpParams = new HashMap<>();
         httpParams.put(KEY_USER_ID, artistId);

         JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                 BASE_URL + "readArtistProfile.php", "GET", httpParams);

         try {
            JSONObject user = jsonObject.getJSONObject(KEY_DATA);
            validator = new Validator();
            JSONObject userWithoutNulls = validator.objectNullToString(user);
            Artist artist = Artist.fromJson(userWithoutNulls);
            return ArtistResult.success(artist);
         } catch (JSONException e) {
            return ArtistResult.failure();
         }
     }

    public VenueResult readVenueProfile(){
        HttpJsonParser httpJsonParser = new HttpJsonParser();
        Map<String, String> httpParams = new HashMap<>();
        httpParams.put(KEY_USER_ID, "testVenue");

        JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                BASE_URL + "readVenueProfile.php", "GET", httpParams);

        try {
            JSONObject user = jsonObject.getJSONObject(KEY_DATA);
            validator = new Validator();
            JSONObject userWithoutNulls = validator.objectNullToString(user);
            Venue venue = Venue.fromJson(userWithoutNulls);
            return VenueResult.success(venue);


        } catch (JSONException e) {
            return VenueResult.failure();
        }
    }

    public GigResult readGigInformation(){
        HttpJsonParser httpJsonParser = new HttpJsonParser();
        Map<String, String> httpParams = new HashMap<>();
        httpParams.put(KEY_GIG_ID, "1");

        JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                BASE_URL + "readGigInformation.php", "GET", httpParams);

        try {
            JSONObject gigInfo = jsonObject.getJSONObject(KEY_DATA);
            validator = new Validator();
            JSONObject gigWithoutNulls = validator.objectNullToString(gigInfo);
            Gig gig = Gig.fromJson(gigWithoutNulls);
            return GigResult.success(gig);


        } catch (JSONException e) {
            return GigResult.failure();
        }
    }




     }


