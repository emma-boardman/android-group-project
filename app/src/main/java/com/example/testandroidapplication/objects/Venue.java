package com.example.testandroidapplication.objects;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Venue implements Entity {

    private User user;
    private ProfileInformation profileInformation;
    private String address1;
    private String postcode;
    private String phoneNumber;
    private ArrayList<Faq> faqs;

    public Venue(VenueBuilder builder) {
        this.user = builder.user;
        this.profileInformation = builder.profileInformation;
        this.address1 = builder.address1;
        this.postcode = builder.postcode;
        this.phoneNumber = builder.phoneNumber;
        this.faqs = builder.faqs;
    }

    public static Venue fromJson(JSONObject jsonObject) {

        Venue venue;
        try {
            User user = new User(jsonObject.getString("User_Id"),
                                 jsonObject.getString("User_Name"),
                                 jsonObject.getString("User_Email"));

            ProfileInformation profileInformation = new ProfileInformation
                    .ProfileBuilder()
                    .withTagline(jsonObject.getString("Tagline"))
                    .withDescription(jsonObject.getString("Description"))
                    .withLocation(jsonObject.getString("Location"))
                    .withFacebookLink(jsonObject.getString("Facebook"))
                    .withWebPageLink(jsonObject.getString("Website"))
                    .withOverallRating(jsonObject.getString("Overall_Rating"))
                    .withReviews(Review.fromJson(jsonObject.getJSONArray("Reviews")))
                    .withSearchTags(Tags.fromJson(jsonObject.getJSONObject("Tags")))
                    .build();

            venue = new Venue
                    .VenueBuilder(user)
                    .withProfileInformation(profileInformation)
                    .withAddress1(jsonObject.getString("Address1"))
                    .withPostcode(jsonObject.getString("PostCode"))
                    .withPhoneNumber(jsonObject.getString("Phone_Number"))
                    .withFAQs(Faq.fromJson(jsonObject.getJSONArray("FAQs")))
                    .build();

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return venue;
    }


    public JSONObject toJson(Venue venue) throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("User_Id", venue.getUser().getUserID());
        jsonObject.accumulate("User_Name", venue.getUser().getName());
        jsonObject.accumulate("Email", venue.getUser().getEmail());
        jsonObject.accumulate("Facebook", venue.getProfileInformation().getFacebookLink());
        jsonObject.accumulate("Instagram", venue.getProfileInformation().getInstagramLink());
        jsonObject.accumulate("Twitter", venue.getProfileInformation().getTwitterLink());
        jsonObject.accumulate("Website", venue.getProfileInformation().getWebPageLink());
        jsonObject.accumulate("Tagline", venue.getProfileInformation().getTagLine());
        jsonObject.accumulate("Description", venue.getProfileInformation().getDescription());
        jsonObject.accumulate("Location", venue.getProfileInformation().getLocation());
        jsonObject.accumulate("Address1", venue.getAddress1());
        jsonObject.accumulate("PostCode", venue.getPostcode());
        jsonObject.accumulate("Phone_Number", venue.getPhoneNumber());

        return jsonObject;

    }

    @Override
    public User getUser(){
        return user;
    }

    @Override
    public ProfileInformation getProfileInformation(){
        return profileInformation;
    }

    public String getAddress1(){
        return address1;
    }

    public String getPostcode(){
        return postcode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static class VenueBuilder  {

        private User user;
        private ProfileInformation profileInformation;
        private String address1;
        private String postcode;
        private String phoneNumber;
        private ArrayList<Faq> faqs;

        public VenueBuilder(User user){
                this.user = user;
        }

        public VenueBuilder updateUser(User user){
            this.user = user;
            return this;
        }

        public VenueBuilder withProfileInformation(ProfileInformation profileInformation){
            this.profileInformation = profileInformation;
            return this;
        }

        public VenueBuilder withAddress1(String address1){
            this.address1 = address1;
            return this;
        }

        public VenueBuilder withPostcode(String postcode){
            this.postcode = postcode;
            return this;
        }

        public VenueBuilder withPhoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }

        public VenueBuilder withFAQs(ArrayList<Faq> faqs){
            this.faqs = faqs;
            return this;
        }

        public Venue build() {
            return new Venue(this);
        }

    }
}
