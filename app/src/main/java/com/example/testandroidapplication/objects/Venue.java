package com.example.testandroidapplication.objects;

import org.json.JSONException;
import org.json.JSONObject;

public class Venue {

    private User user;
    private String address1;
    private String postcode;
    private String faq;
    private int phoneNumber;

    public Venue(VenueBuilder builder) {
        this.user = builder.user;
        this.address1 = builder.address1;
        this.postcode = builder.postcode;
        this.faq = builder.faq;
        this.phoneNumber = builder.phoneNumber;
    }

    public static Venue fromJson(JSONObject jsonObject) {

        Venue venue;
        try {
            User user = new User
                    .UserBuilder(jsonObject.getString("User_Id"),
                                 jsonObject.getString("User_Name"),
                                 jsonObject.getString("Email"))
                    .withTagline(jsonObject.getString("Tagline"))
                    .withSearchTags(Tags.fromJson(jsonObject.getJSONObject("Tags")))
                    .withDescription(jsonObject.getString("Description"))
                    .withLocation(jsonObject.getString("Location"))
                    .withFacebookLink(jsonObject.getString("Facebook"))
                    .withWebPageLink(jsonObject.getString("Website"))
                    .build();

            venue = new Venue
                    .VenueBuilder(user)
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
