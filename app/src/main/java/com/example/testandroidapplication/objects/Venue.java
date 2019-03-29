package com.example.testandroidapplication.objects;

import org.json.JSONException;
import org.json.JSONObject;

public class Venue {

    private User user;
    private ProfileInformation profileInformation;
    private String address1;
    private String postcode;
    private String faq;
    private int phoneNumber;

    public Venue(VenueBuilder builder) {
        this.user = builder.user;
        this.profileInformation = builder.profileInformation;
        this.address1 = builder.address1;
        this.postcode = builder.postcode;
        this.faq = builder.faq;
        this.phoneNumber = builder.phoneNumber;
    }

    public static Venue fromJson(JSONObject jsonObject) {

        Venue venue;
        try {
            User user = new User(jsonObject.getString("User_Id"),
                                 jsonObject.getString("User_Name"),
                                 jsonObject.getString("Email"));

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
                    .withPhoneNumber(jsonObject.getInt("Phone_Number"))
                    .build();

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return venue;
    }

    public User getUser(){
        return user;
    }

    public ProfileInformation getProfileInformation(){
        return profileInformation;
    }

    public String getAddress1(){
        return address1;
    }

    public String getPostcode(){
        return postcode;
    }

    public String getFaq() {
        return faq;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public static class VenueBuilder  {

        private User user;
        private ProfileInformation profileInformation;
        private String address1;
        private String postcode;
        private String faq;
        private int phoneNumber;

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

        public VenueBuilder withFAQ(String faq){
            this.faq = faq;
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

        public VenueBuilder withPhoneNumber(int phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Venue build() {
            return new Venue(this);
        }

    }

}
