package com.example.testandroidapplication.objects;

public class Artist extends User {

    private String soundCloudLink, comments;

    public Artist(String name, String email, String password, String tagLine, String searchTags, String description, String facebookLink, String twitterLink, String webPageLink, String location, int userID, int overallRating, byte[] profileImage, String comments, String soundCloudLink) {
        super(name, email, password, tagLine, searchTags, description, facebookLink, twitterLink, webPageLink, location, userID, overallRating, profileImage);
        setComments(comments);
        setSoundCloudLink(soundCloudLink);
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
