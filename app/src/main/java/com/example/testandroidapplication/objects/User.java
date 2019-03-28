package com.example.testandroidapplication.objects;

import java.util.ArrayList;

public class User {

    private String userID;
    private String name;
    private String email;
    private String tagLine;
    private String description;
    private Tags searchTags;
    private String facebookLink;
    private String instagramLink;
    private String twitterLink;
    private String webPageLink;
    private String location;
    private String overallRating;
    private String profileImage;
    private ArrayList<Review> reviews;

    public User(UserBuilder builder){
        this.userID = builder.userID;
        this.name = builder.name;
        this.email = builder.email;
        this.tagLine = builder.tagLine;
        this.searchTags = builder.searchTags;
        this.description = builder.description;
        this.facebookLink = builder.facebookLink;
        this.instagramLink = builder.instagramLink;
        this.twitterLink = builder.twitterLink;
        this.webPageLink = builder.webPageLink;
        this.location = builder.location;
        this.reviews = builder.reviews;
        this.overallRating = builder.overallRating;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getTagLine() {return tagLine;}

    public Tags getSearchTags() { return searchTags; }

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

    public String getOverallRatingNum() {
        return overallRating;
    }

    public ArrayList<Review> getReviews() {return reviews; }

    public String getProfileImage() {
        return profileImage;
    }

    public static class UserBuilder {

        private String userID;
        private String name;
        private String email;
        private String tagLine;
        private Tags searchTags;
        private String description;
        private String facebookLink;
        private String instagramLink;
        private String twitterLink;
        private String webPageLink;
        private String location;
        private String overallRating;
        private String profileImage;
        private ArrayList<Review> reviews;

        public UserBuilder(String userID, String name, String email){
            this.userID = userID;
            this.name = name;
            this.email = email;
        }

        public UserBuilder withName(String name){
            this.name = name;
            return this;
        }

        public UserBuilder withTagline(String tagLine){
            this.tagLine = tagLine;
            return this;
        }

        public UserBuilder withSearchTags(Tags searchTags){
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

        public UserBuilder withOverallRating(String overallRating){
            this.overallRating = overallRating;
            return this;
        }

        public UserBuilder withReviews(ArrayList<Review> reviews){
            this.reviews = reviews;
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
