package com.example.testandroidapplication.objects;

import org.json.JSONException;
import org.json.JSONObject;

public class Artist extends User {

    private String soundCloudLink, comments;

    public Artist(String name, String email, String password, String tagLine, String searchTags, String description, String facebookLink, String instagramLink, String twitterLink, String webPageLink, String location, int userID, int overallRating, byte[] profileImage, String comments, String soundCloudLink) {
        super(name, email, password, tagLine, searchTags, description, facebookLink, instagramLink, twitterLink, webPageLink, location, userID, overallRating, profileImage);
        setComments(comments);
        setSoundCloudLink(soundCloudLink);
    }

    public Artist(){

    }

// Questions:
// Should the tags be an array?
// Should the comments be an array?

    public static Artist fromJson(JSONObject jsonObject) {

        Artist artist = new Artist();
        // Deserialize json into object fields
        try {
            artist.name = jsonObject.getString("User_Name");
            artist.tagLine = jsonObject.getString("Tagline");
            artist.searchTags = "tag tag tag";
            artist.description = jsonObject.getString("Description");
            artist.facebookLink = jsonObject.getString("Facebook");
            artist.instagramLink = jsonObject.getString("Instagram");
            artist.twitterLink = jsonObject.getString("Twitter");
            artist.webPageLink = jsonObject.getString("Website");
            artist.location = jsonObject.getString("Location");
            artist.userID = jsonObject.getInt("User_Id");
            artist.overallRating = 4;
            artist.profileImage = null;
            artist.comments = "comments";
            artist.soundCloudLink = jsonObject.getString("Soundcloud");

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return artist;
    }


    public String toString() {
        String output;

        output = "" + this.name + " , " + this.userID;

        return output;
    }

    public String getSoundCloudLink() {
        return soundCloudLink;
    }

    public void setSoundCloudLink(String soundCloudLink) {
        this.soundCloudLink = soundCloudLink;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}