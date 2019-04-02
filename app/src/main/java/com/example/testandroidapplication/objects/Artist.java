package com.example.testandroidapplication.objects;

import android.provider.ContactsContract;

import org.json.JSONException;
import org.json.JSONObject;

public class Artist {

    private User user;
    private ProfileInformation profileInformation;
    private String soundCloudLink;

    private Artist(ArtistBuilder builder) {
        this.user = builder.user;
        this.profileInformation = builder.profileInformation;
        this.soundCloudLink = builder.soundCloudLink;
    }

        public static Artist fromJson(JSONObject jsonObject) {

            Artist artist;
            // Deserialize json into object fields
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

                artist = new Artist
                        .ArtistBuilder(user)
                        .withProfileInformation(profileInformation)
                        .withSoundcloudLink(jsonObject.getString("Soundcloud"))
                        .build();

            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
            // Return new object
            return artist;
        }

        public String getSoundCloudLink() {
            return soundCloudLink;
        }


public static class ArtistBuilder {

    private User user;
    private ProfileInformation profileInformation;
    private String soundCloudLink;

    ArtistBuilder(User user) {
        this.user = user;
    }

    public ArtistBuilder updateUser(User user) {
        this.user = user;
        return this;
    }

    ArtistBuilder withProfileInformation(ProfileInformation profileInformation){
        this.profileInformation = profileInformation;
        return this;
    }

    ArtistBuilder withSoundcloudLink(String soundCloudLink) {
        this.soundCloudLink = soundCloudLink;
        return this;
    }

    Artist build() {
        return new Artist(this);
    }
}

}

