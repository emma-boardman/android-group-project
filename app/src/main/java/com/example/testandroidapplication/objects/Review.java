package com.example.testandroidapplication.objects;

import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Review {

    private String venueName;
    private String venueComment;
    private String gigDate;

    public Review(String venueName, String venueComment, String gigDate) {
        this.venueName = venueName;
        this.venueComment = venueComment;
        this.gigDate = gigDate;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueNme(String venueName) {
        this.venueName = venueName;
    }

    public String getVenueComment() {
        return venueComment;
    }

    public void setVenueComment(String venueComment) {
        this.venueComment = venueComment;
    }

    public String getGigDate() {
        return gigDate;
    }

    public void setGigDate(String gigDate) {
        this.gigDate = gigDate;
    }


    public static ArrayList<Review> fromJson(@Nullable JSONArray jsonArray) throws JSONException {

        ArrayList<Review> reviews = new ArrayList<>();
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                Review review = new Review(
                        jsonArray.getJSONObject(i).getString("Reviewer"),
                        jsonArray.getJSONObject(i).getString("Comment"),
                        jsonArray.getJSONObject(i).getString("Gig_Date"));
                reviews.add(review);
            }
        }
        return reviews;
    }


}
