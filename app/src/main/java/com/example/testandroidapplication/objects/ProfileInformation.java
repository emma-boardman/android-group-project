package com.example.testandroidapplication.objects;


import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.testandroidapplication.utils.JsonUtils.addStringToJson;

public class ProfileInformation implements JsonWritable {

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

    public ProfileInformation(ProfileBuilder builder){
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

    public String getOverallRatingNum() {
        return overallRating;
    }

    public ArrayList<Review> getReviews() {return reviews; }

    public String getProfileImage() {
        return profileImage;
    }

    public static class ProfileBuilder {

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

        public ProfileBuilder(){
        }

        public ProfileBuilder withTagline(String tagLine){
            this.tagLine = tagLine;
            return this;
        }

        public ProfileBuilder withSearchTags(Tags searchTags){
            this.searchTags = searchTags;
            return this;
        }

        public ProfileBuilder withDescription(String description){
            this.description = description;
            return this;
        }

        public ProfileBuilder withFacebookLink(String facebookLink){
            this.facebookLink = facebookLink;
            return this;
        }

        public ProfileBuilder withInstagramLink(String instagramLink){
            this.instagramLink = instagramLink;
            return this;
        }

        public ProfileBuilder withTwitterLink(String twitterLink){
            this.twitterLink = twitterLink;
            return this;
        }

        public ProfileBuilder withWebPageLink(String webPageLink){
            this.webPageLink = webPageLink;
            return this;
        }

        public ProfileBuilder withLocation(String location){
            this.location = location;
            return this;
        }

        public ProfileBuilder withOverallRating(String overallRating){
            this.overallRating = overallRating;
            return this;
        }

        public ProfileBuilder withReviews(ArrayList<Review> reviews){
            this.reviews = reviews;
            return this;
        }

        public ProfileBuilder withProfileImage(String profileImage){
            this.profileImage = profileImage;
            return this;
        }

        public ProfileInformation build() {
            return new ProfileInformation(this);
        }

    }

    @Override
    public JSONObject toJson() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        addStringToJson(jsonObject, "Facebook", getFacebookLink());
        addStringToJson(jsonObject, "Instagram", getInstagramLink());
        addStringToJson(jsonObject, "Twitter", getTwitterLink());
        addStringToJson(jsonObject, "Website", getWebPageLink());
        addStringToJson(jsonObject, "Tagline", getTagLine());
        addStringToJson(jsonObject, "Description", getDescription());
        addStringToJson(jsonObject, "Location", getLocation());
        Tags searchTags = getSearchTags();
        if (searchTags.hasTags()) {
            jsonObject.accumulate("Tags", searchTags.toJson());
        }
        return jsonObject;
    }
}
