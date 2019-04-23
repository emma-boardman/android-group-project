package com.example.testandroidapplication.objects;

import android.annotation.TargetApi;


import org.json.JSONException;
import org.json.JSONObject;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

//public abstract class Gig {

public class Gig {
    private int gigID, artistRating, venueRating;
    private String userIDArtist, userIDVenue, notes, venueComment, artistComment;
    private LocalTime startTime, endTime;
    private LocalDate date;

    public Gig(GigBuilder builder){
        this.gigID = builder.gigID;
        this.userIDArtist = builder.userIDArtist;
        this.userIDVenue = builder.userIDVenue;
        this.date = builder.date;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.artistRating = builder.artistRating;
        this.venueRating = builder.venueRating;
        this.notes = builder.notes;
        this.venueComment = builder.venueComment;
        this.artistComment = builder.artistComment;
    }

    @TargetApi(26)
    public static Gig fromJson(JSONObject jsonObject){

        Gig gig;

        try {

            String stringDate = jsonObject.getString("Gig_Date");
            DateTimeFormatter formatterDate = DateTimeFormatter.ISO_LOCAL_DATE;
            String stringStartTime = jsonObject.getString("Start_Time");
            String stringEndTime = jsonObject.getString("End_Time");
            DateTimeFormatter formatterTime = DateTimeFormatter.ISO_LOCAL_TIME;

            gig = new Gig.GigBuilder(jsonObject.getInt("Gig_Id"),
                                     jsonObject.getString("Artist_Id"),
                                     jsonObject.getString("Venue_Id"),
                                     LocalDate.parse(stringDate, formatterDate),
                                     LocalTime.parse(stringStartTime, formatterTime),
                                     LocalTime.parse(stringEndTime, formatterTime))
                                    .withNotes(jsonObject.getString("Notes"))
                                    .withArtistRating(0)
                                    .withVenueRating(0)
                                    .withArtistComment(jsonObject.getString("Comment_On_Artist"))
                                    .withVenueComment(jsonObject.getString("Comment_On_Venue"))
                                    .build();

        } catch (JSONException e){
            e.printStackTrace();
            return null;
        }

        return gig;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getGigID() {
        return gigID;
    }

    public String getUserIDArtist() {
        return userIDArtist;
    }

    public String getUserIDVenue() {
        return userIDVenue;
    }

    public int getArtistRating() { return artistRating; }

    public int getVenueRating() {
        return venueRating;
    }

    public String getNotes() {
        return notes;
    }

    public String getVenueComment() {
        return venueComment;
    }

    public String getArtistComment() {
        return artistComment;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public static class GigBuilder {

        protected int gigID, artistRating, venueRating;
        protected String userIDArtist, userIDVenue, notes, venueComment, artistComment;
        protected LocalTime startTime, endTime;
        protected LocalDate date;

        public GigBuilder(int gigID, String userIDArtist, String userIDVenue, LocalDate date, LocalTime startTime, LocalTime endTime){
            this.gigID = gigID;
            this.userIDArtist = userIDArtist;
            this.userIDVenue = userIDVenue;
            this.date = date;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public GigBuilder withNotes(String notes){
            this.notes = notes;
            return this;
        }

        public GigBuilder withArtistRating(int artistRating){
            this.artistRating = artistRating;
            return this;
        }

        public GigBuilder withVenueRating(int venueRating){
            this.venueRating = venueRating;
            return this;
        }

        public GigBuilder withArtistComment(String artistComment){
            this.artistComment = artistComment;
            return this;
        }

        public GigBuilder withVenueComment(String venueComment){
            this.venueComment = venueComment;
            return this;
        }

        public Gig build(){
            return new Gig(this);
        }
    }
}