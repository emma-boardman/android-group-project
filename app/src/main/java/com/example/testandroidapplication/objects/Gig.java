package com.example.testandroidapplication.objects;

import android.annotation.TargetApi;


import org.json.JSONException;
import org.json.JSONObject;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

//public abstract class Gig {

public class Gig {
    protected int gigID, userIDArtist, userIDVenue, artistReview, venueReview;
    protected String notes, venueComment, artistComment;
    protected LocalTime startTime, endTime;
    protected LocalDate date;

    public Gig(int gigID, int userIDArtist, int userIDVenue, int artistReview, int venueReview, String notes, String venueComment, String artistComment, LocalTime startTime, LocalTime endTime, LocalDate date) {
        setGigID(gigID);
        setUserIDArtist(userIDArtist);
        setUserIDVenue(userIDVenue);
        setArtistReview(artistReview);
        setVenueReview(venueReview);
        setNotes(notes);
        setVenueComment(venueComment);
        setArtistComment(artistComment);
        setStartTime(startTime);
        setEndTime(endTime);
        setDate(date);
    }

    public Gig(){

    }

    @TargetApi(26)
    public static Gig fromJson(JSONObject jsonObject){

        Gig gig = new Gig();

        try {

            String stringDate = jsonObject.getString("Gig_Date");
            DateTimeFormatter formatterDate = DateTimeFormatter.ISO_LOCAL_DATE;
            String stringStartTime = jsonObject.getString("Start_Time");
            String stringEndTime = jsonObject.getString("End_Time");
            DateTimeFormatter formatterTime = DateTimeFormatter.ISO_LOCAL_TIME;

            gig.date = LocalDate.parse(stringDate, formatterDate);
            gig.userIDArtist = jsonObject.getInt("Artist_Id");
            gig.userIDVenue = jsonObject.getInt("Venue_Id");
            gig.startTime = LocalTime.parse(stringStartTime, formatterTime);
            gig.endTime = LocalTime.parse(stringEndTime, formatterTime);


        } catch (JSONException e){
            e.printStackTrace();
            return null;
        }

        return gig;
    }

    public String toString() {
        String output;

        output = "" + this.gigID + " , " + this.date;

        return output;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getGigID() {
        return gigID;
    }

    public void setGigID(int gigID) {
        this.gigID = gigID;
    }

    public int getUserIDArtist() {
        return userIDArtist;
    }

    public void setUserIDArtist(int userIDArtist) {
        this.userIDArtist = userIDArtist;
    }

    public int getUserIDVenue() {
        return userIDVenue;
    }

    public void setUserIDVenue(int userIDVenue) {
        this.userIDVenue = userIDVenue;
    }

    public int getArtistReview() {
        return artistReview;
    }

    public void setArtistReview(int artistReview) {
        this.artistReview = artistReview;
    }

    public int getVenueReview() {
        return venueReview;
    }

    public void setVenueReview(int venueReview) {
        this.venueReview = venueReview;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getVenueComment() {
        return venueComment;
    }

    public void setVenueComment(String venueComment) {
        this.venueComment = venueComment;
    }

    public String getArtistComment() {
        return artistComment;
    }

    public void setArtistComment(String artistComment) {
        this.artistComment = artistComment;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}