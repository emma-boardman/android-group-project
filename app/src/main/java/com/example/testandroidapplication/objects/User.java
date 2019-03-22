package com.example.testandroidapplication.objects;

public class User {

    private String userID, name, email, password, tagLine, searchTags, description, facebookLink, instagramLink, twitterLink, webPageLink, location;
    private int overallRating;
    private String profileImage;

    public User(UserBuilder builder){
        this.userID = builder.userID;
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;
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

    public String getTagLine() {return tagLine;}

    public void setTagLine(String tagLine) {this.tagLine = tagLine; }

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

    public String getInstagramLink() {
        return instagramLink;
    }

    public void setInstagramLink(String instagramLink) {
        this.instagramLink = instagramLink;
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

    public String getUserID() { return userID; }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(int overallRating) {
        this.overallRating = overallRating;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }


    public static class UserBuilder {

        private String userID, name, email, password, tagLine, searchTags, description, facebookLink, instagramLink, twitterLink, webPageLink, location;
        private int overallRating;
        private String profileImage;

        public UserBuilder(String userID, String name, String email, String password){
            this.userID = userID;
            this.name = name;
            this.email = email;
            this.password = password;
        }

        public UserBuilder withTagline(String tagLine){
            this.tagLine = tagLine;
            return this;
        }

        public UserBuilder withSearchTags(String searchTags){
            this.searchTags = searchTags;
            return this;
        }

        public UserBuilder withDescription(String description){
            this.description = description;
            return this;
        }

        public UserBuilder withFacebookLink(String facebookLink){
            this.facebookLink = facebookLink;
            return this;
        }

        public UserBuilder withInstagramLink(String instagramLink){
            this.instagramLink = instagramLink;
            return this;
        }

        public UserBuilder withTwitterLink(String twitterLink){
            this.twitterLink = twitterLink;
            return this;
        }

        public UserBuilder withWebPageLink(String webPageLink){
            this.webPageLink = webPageLink;
            return this;
        }

        public UserBuilder withLocation(String location){
            this.location = location;
            return this;
        }

        public UserBuilder withOverallRating(int overallRating){
            this.overallRating = overallRating;
            return this;
        }

        public UserBuilder withProfileImage(String profileImage){
            this.profileImage = profileImage;
            return this;
        }

        public User build() {
            return new User(this);
        }


    }


}
