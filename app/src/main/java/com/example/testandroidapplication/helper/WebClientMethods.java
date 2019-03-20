package com.example.testandroidapplication.helper;



import org.json.JSONException;
import org.json.JSONObject;


import com.example.testandroidapplication.objects.Artist;
import com.example.testandroidapplication.objects.Gig;
import com.example.testandroidapplication.objects.Venue;

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

    // CREATE

    public String createUserAccount(String userName, String userEmail, String userPassword) {
        HttpJsonParser httpJsonParser = new HttpJsonParser();
        Map<String, String> httpParams = new HashMap<>();
        httpParams.put(KEY_USER_NAME, userName);
        httpParams.put(KEY_EMAIL, userEmail);
        httpParams.put(KEY_PASSWORD, userPassword);
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
        Map<String, String> httpParams = new HashMap<>();
        httpParams.put(KEY_USER_NAME, "test");
        httpParams.put(KEY_EMAIL, "test");
        httpParams.put(KEY_PASSWORD, "test");
        JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                BASE_URL + "createUser.php", "POST", httpParams);
        try {
            return jsonObject.getString("success");
        } catch (JSONException e) {
            e.printStackTrace();
            return e.getMessage();

        }
    }

    // READ


     public ArtistResult readArtistProfile(){
         HttpJsonParser httpJsonParser = new HttpJsonParser();
         Map<String, String> httpParams = new HashMap<>();
         httpParams.put(KEY_USER_ID, "5");

         JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                 BASE_URL + "readArtistProfile.php", "GET", httpParams);

         try {
            JSONObject user = jsonObject.getJSONObject(KEY_DATA);
            Artist artist = Artist.fromJson(user);
            return ArtistResult.success(artist);


         } catch (JSONException e) {
            return ArtistResult.failure();
         }
     }

    public VenueResult readVenueProfile(){
        HttpJsonParser httpJsonParser = new HttpJsonParser();
        Map<String, String> httpParams = new HashMap<>();
        httpParams.put(KEY_USER_ID, "29");

        JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                BASE_URL + "readVenueProfile.php", "GET", httpParams);

        try {
            JSONObject user = jsonObject.getJSONObject(KEY_DATA);
            Venue venue = Venue.fromJson(user);
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
            Gig gig = Gig.fromJson(gigInfo);
            return GigResult.success(gig);


        } catch (JSONException e) {
            return GigResult.failure();
        }
    }




     }


