package com.example.testandroidapplication.objects;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.testandroidapplication.utils.JsonUtils.addStringToJson;
import static com.example.testandroidapplication.utils.JsonUtils.getJSONArrayOrNull;
import static com.example.testandroidapplication.utils.JsonUtils.getJSONObjectOrNull;
import static com.example.testandroidapplication.utils.JsonUtils.getStringOrNull;
import static com.example.testandroidapplication.utils.JsonUtils.merge;

public class Venue implements Entity, JsonWritable {

    private User user;
    private ProfileInformation profileInformation;
    private String address1;
    private String postcode;
    private String phoneNumber;

    public Venue(VenueBuilder builder) {
        this.user = builder.user;
        this.profileInformation = builder.profileInformation;
        this.address1 = builder.address1;
        this.postcode = builder.postcode;
        this.phoneNumber = builder.phoneNumber;
        ArrayList<Faq> faqs = builder.faqs;
    }

    public static Venue fromJson(JSONObject jsonObject) {

        Venue venue;
        try {
            User user = new User(jsonObject.getString("User_Id"),
                                 jsonObject.getString("User_Name"),
                                 jsonObject.getString("User_Email"));

            ProfileInformation profileInformation = new ProfileInformation
                    .ProfileBuilder()
                    .withTagline(getStringOrNull(jsonObject, "Tagline"))
                    .withDescription(getStringOrNull(jsonObject, "Description"))
                    .withLocation(getStringOrNull(jsonObject, "Location"))
                    .withFacebookLink(getStringOrNull(jsonObject, "Facebook"))
                    .withWebPageLink(getStringOrNull(jsonObject, "Website"))
                    .withOverallRating(getStringOrNull(jsonObject, "Overall_Rating"))
                    .withReviews(Review.fromJson(getJSONArrayOrNull(jsonObject,"Reviews")))
                    .withSearchTags(Tags.fromJson(getJSONObjectOrNull(jsonObject, "Tags")))
                    .build();

            venue = new Venue
                    .VenueBuilder(user)
                    .withProfileInformation(profileInformation)
                    .withAddress1(getStringOrNull(jsonObject, "Address1"))
                    .withPostcode(getStringOrNull(jsonObject, "PostCode"))
                    .withPhoneNumber(getStringOrNull(jsonObject, "Phone_Number"))
                    .withFAQs(Faq.fromJson(getJSONArrayOrNull(jsonObject,"FAQs")))
                    .build();

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return venue;
    }

    @Override
    public JSONObject toJson() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        addStringToJson(jsonObject,"Address1", this.getAddress1());
        addStringToJson(jsonObject,"PostCode", this.getPostcode());
        addStringToJson(jsonObject,"Phone_Number", this.getPhoneNumber());
        final JSONObject user = getUser().toJson();
        final JSONObject profile = getProfileInformation().toJson();
        return merge(jsonObject, profile, user);
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
