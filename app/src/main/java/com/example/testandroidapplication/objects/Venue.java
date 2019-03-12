package com.example.testandroidapplication.objects;

public class Venue extends User {

    private String faq;
    private int phoneNumber;

    public Venue(String name, String email, String password, String tagLine, String searchTags, String description, String facebookLink, String twitterLink, String webPageLink, String location, int userID, int overallRating, byte[] profileImage, String faq, int phoneNumber) {
        super(name, email, password, tagLine, searchTags, description, facebookLink, twitterLink, webPageLink, location, userID, overallRating, profileImage);
        setFaq(faq);
        setPhoneNumber(phoneNumber);
    }

    public Venue(){

    }

    public String toString() {
        String output;

        output = "" + this.name + " , " + this.userID;

        return output;
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
