package com.example.testandroidapplication.objects;

import org.json.JSONException;
import org.json.JSONObject;

public class Artist extends User {

    private String soundCloudLink, comments;

    public Artist(String userID, String name, String email, String password, String tagLine, String searchTags, String description, String facebookLink, String instagramLink, String twitterLink, String webPageLink, String location, int overallRating, String profileImage, String comments, String soundCloudLink) {
        super(userID, name, email, password, tagLine, searchTags, description, facebookLink, instagramLink, twitterLink, webPageLink, location, overallRating, profileImage);
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

            artist = new Artist(artistUserID,
                                artistName,
                                artistEmail,
                                artistPassword,
                                artistTagLine,
                                artistSearchTags,
                                artistDescription,
                                artistFacebookLink,
                                artistInstagramLink,
                                artistTwitterLink,
                                artistWebPageLink,
                                artistLocation,
                                artistOverallRating,
                                artistProfileImage,
                                artistComments,
                                artistSoundCloudLink);

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