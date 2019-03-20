package com.example.testandroidapplication.objects;

import org.json.JSONException;
import org.json.JSONObject;

public class Venue extends User {

    private String address1;
    private String postcode;
    private String faq;
    private int phoneNumber;

    public Venue(String userID, String name, String email, String password, String tagLine, String searchTags, String description, String facebookLink, String instagramLink, String twitterLink, String webPageLink, String location, int overallRating, String profileImage, String faq, String address1, String postcode, int phoneNumber) {
        super(name, email, password, tagLine, searchTags, description, facebookLink, instagramLink, twitterLink, webPageLink, location, userID, overallRating, profileImage);
        setAddress1(address1);
        setPostcode(postcode);
        setFaq(faq);
        setPhoneNumber(phoneNumber);
    }

    // Questions:
        // What happens when any of these are passed in as null
        // Should the faq be an array?
        // Phone number needs to be converted to varchar at some point. where is that point?
        // should the email and password be part of the object?
        // no comments array for the venue?

    public static Venue fromJson(JSONObject jsonObject) {

        Venue venue;

        try {

            String venueUserID = jsonObject.getString("User_Id");
            String venueName = jsonObject.getString("User_Name");
            String venueEmail = jsonObject.getString("Email");
            String venuePassword = jsonObject.getString("Password");
            String venueTagLine = jsonObject.getString("Tagline");
            String venueSearchTags = "tag tag tag";
            String venueDescription = jsonObject.getString("Description");
            String venueFacebookLink = jsonObject.getString("Facebook");
            String venueInstagramLink = jsonObject.getString("Instagram");
            String venueTwitterLink = jsonObject.getString("Twitter");
            String venueWebPageLink = jsonObject.getString("Website");
            String venueLocation = jsonObject.getString("Location");
            int venueOverallRating = 4;
            String venueProfileImage = ".jpg";
            String venueFAQ = "faq";
            String venueAddress1 = jsonObject.getString("Address1");
            String venuePostcode = jsonObject.getString("PostCode");
            int venuePhoneNumber = jsonObject.getInt("Phone_Number");

            venue = new Venue(venueUserID,
                    venueName,
                    venueEmail,
                    venuePassword,
                    venueTagLine,
                    venueSearchTags,
                    venueDescription,
                    venueFacebookLink,
                    venueInstagramLink,
                    venueTwitterLink,
                    venueWebPageLink,
                    venueLocation,
                    venueOverallRating,
                    venueProfileImage,
                    venueFAQ,
                    venueAddress1,
                    venuePostcode,
                    venuePhoneNumber);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return venue;
    }


    public String toString() {
        String output;

        output = "" + this.name + " , " + this.userID;

        return output;
    }

    public String getAddress1(){
        return address1;
    }

    public void setAddress1(String address1){
        this.address1 = address1;
    }

    public String getPostcode(){
        return postcode;
    }

    public void setPostcode(String postcode){
        this.postcode = postcode;
    }

    public String getFaq() {
        return faq;
    }

    public void setFaq(String faq) {
        this.faq = faq;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
