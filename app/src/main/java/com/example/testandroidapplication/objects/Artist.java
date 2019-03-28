package com.example.testandroidapplication.objects;

import org.json.JSONException;
import org.json.JSONObject;

public class Artist {

    private String soundCloudLink;

    private Artist(ArtistBuilder builder) {
        User user = builder.user;
        this.soundCloudLink = builder.soundCloudLink;
    }

        public static Artist fromJson(JSONObject jsonObject) {

            Artist artist;
            // Deserialize json into object fields
            try {
                User user = new User
                        .UserBuilder(jsonObject.getString("User_Id"),
                        jsonObject.getString("User_Name"),
                        jsonObject.getString("User_Email"))
                        .withTagline(jsonObject.getString("Tagline"))
                        .withSearchTags("tags tags tags")
                        .withDescription(jsonObject.getString("Description"))
                        .withLocation(jsonObject.getString("Location"))
                        .withFacebookLink(jsonObject.getString("Facebook"))
                        .withWebPageLink(jsonObject.getString("Website"))
                        .withOverallRating(jsonObject.getString("Overall_Rating"))
                        .withReviews(Review.fromJson(jsonObject.getJSONArray("Reviews")))
                        .build();

                artist = new Artist
                        .ArtistBuilder(user)
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
    private String soundCloudLink;

    ArtistBuilder(User user) {
        this.user = user;
    }

    public ArtistBuilder updateUser(User user) {
        this.user = user;
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

