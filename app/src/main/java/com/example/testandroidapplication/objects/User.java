package com.example.testandroidapplication.objects;

public abstract class User {

    protected String name, email, password, tagLine, searchTags, description, facebookLink, twitterLink, webPageLink, location;
    protected int userID, overallRating;
    protected byte[] profileImage;

    public User(String name, String email, String password, String tagLine, String searchTags, String description, String facebookLink, String twitterLink, String webPageLink, String location, int userID, int overallRating, byte[] profileImage) {
        setName(name);
        setEmail(email);
        setPassword(password);
        setTagLine(tagLine);
        setSearchTags(searchTags);
        setDescription(description);
        setFacebookLink(facebookLink);
        setTwitterLink(twitterLink);
        setWebPageLink(webPageLink);
        setLocation(location);
        setUserID(userID);
        setOverallRating(overallRating);
        setProfileImage(profileImage);
    }

    public User() {
    }


        public String toString() {
        String output;

        output = "" + this.name + " , " + this.userID;

        return output;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public String getSearchTags() {
        return searchTags;
    }

    public void setSearchTags(String searchTags) {
        this.searchTags = searchTags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public String getTwitterLink() {
        return twitterLink;
    }

    public void setTwitterLink(String twitterLink) {
        this.twitterLink = twitterLink;
    }

    public String getWebPageLink() {
        return webPageLink;
    }

    public void setWebPageLink(String webPageLink) {
        this.webPageLink = webPageLink;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(int overallRating) {
        this.overallRating = overallRating;
    }

    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }
}
