package com.example.testandroidapplication.objects;

import org.json.JSONException;
import org.json.JSONObject;

public class Venue extends User {

    private String address1;
    private String postcode;
    private String faq;
    private int phoneNumber;

    public Venue(String name, String email, String password, String tagLine, String searchTags, String description, String facebookLink, String instagramLink, String twitterLink, String webPageLink, String location, int userID, int overallRating, byte[] profileImage, String faq, String address1, String postcode, int phoneNumber) {
        super(name, email, password, tagLine, searchTags, description, facebookLink, instagramLink, twitterLink, webPageLink, location, userID, overallRating, profileImage);
        setAddress1(address1);
        setPostcode(postcode);
        setFaq(faq);
        setPhoneNumber(phoneNumber);
    }

    public Venue(){

    }

    // Questions:
        // What happens when any of these are passed in as null
        // Should the faq be an array?
        // Phone number needs to be converted to varchar at some point. where is that point?
        // should the email and password be part of the object?
        // no comments array for the venue?

    public static Venue fromJson(JSONObject jsonObject) {

        Venue venue = new Venue();
        // Deserialize json into object fields
        try {
//            venue.userID = jsonObject.getInt("User_Id");
            venue.name = jsonObject.getString("User_Name");
            venue.tagLine = jsonObject.getString("Tagline");
            venue.searchTags = "tag tag tag";
            venue.description = jsonObject.getString("Description");
            venue.facebookLink = jsonObject.getString("Facebook");
            venue.instagramLink = jsonObject.getString("Instagram");
            venue.twitterLink = jsonObject.getString("Twitter");
            venue.webPageLink = jsonObject.getString("Website");
//            venue.location = jsonObject.getString("Location");
            venue.overallRating = 4;
            venue.profileImage = null;
            venue.address1 = jsonObject.getString("Address1");
            venue.postcode = jsonObject.getString("PostCode");
            venue.phoneNumber = jsonObject.getInt("Phone_Number");

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
