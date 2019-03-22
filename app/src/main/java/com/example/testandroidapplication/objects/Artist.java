package com.example.testandroidapplication.objects;

import org.json.JSONException;
import org.json.JSONObject;

public class Artist {

    private User user;
    private String soundCloudLink, comments;

    public Artist(User user, String comments, String soundCloudLink) {
        this.user = user;
        setComments(comments);
        setSoundCloudLink(soundCloudLink);
    }

// Questions:
// Should the tags be an array?
// Should the comments be an array?

    public static Artist fromJson(JSONObject jsonObject) {

        Artist artist;
        // Deserialize json into object fields
        try {
            String artistUserID = jsonObject.getString("User_Id");
            String artistName = jsonObject.getString("User_Name");
            String artistEmail = jsonObject.getString("User_Email");
            String artistPassword = jsonObject.getString("Password");
            String artistTagLine = jsonObject.getString("Tagline");
            String artistSearchTags = "tag tag tag";
            String artistDescription = jsonObject.getString("Description");
            String artistFacebookLink = jsonObject.getString("Facebook");
            String artistInstagramLink = jsonObject.getString("Instagram");
            String artistTwitterLink = jsonObject.getString("Twitter");
            String artistWebPageLink = jsonObject.getString("Website");
            String artistLocation = jsonObject.getString("Location");
            int artistOverallRating = 4;
            String artistProfileImage = ".jpg";
            String artistComments = "comments";
            String artistSoundCloudLink = jsonObject.getString("Soundcloud");



            User newUser = new User
                    .UserBuilder(artistUserID, artistName, artistEmail, artistPassword)
                    .withTagline(artistTagLine)
                    .build();

            artist = new Artist(newUser,
                    "comment", "soundcloud");


        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return artist;
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