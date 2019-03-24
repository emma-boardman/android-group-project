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
        this.tagLine = builder.tagLine;
        this.searchTags = builder.searchTags;
        this.description = builder.description;
        this.facebookLink = builder.facebookLink;
        this.instagramLink = builder.instagramLink;
        this.twitterLink = builder.twitterLink;
        this.webPageLink = builder.webPageLink;
        this.location = builder.location;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getTagLine() {return tagLine;}

    public String getSearchTags() {
        return searchTags;
    }

    public String getDescription() {
        return description;
    }

    public String getInstagramLink() {
        return instagramLink;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public String getTwitterLink() {
        return twitterLink;
    }

    public String getWebPageLink() {
        return webPageLink;
    }

    public String getLocation() {
        return location;
    }

    public String getUserID() { return userID; }

    public int getOverallRating() {
        return overallRating;
    }

    public String getProfileImage() {
        return profileImage;
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

        public UserBuilder withName(String name){
            this.name = name;
            return this;
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
