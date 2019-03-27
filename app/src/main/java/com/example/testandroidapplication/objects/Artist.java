package com.example.testandroidapplication.objects;

import org.json.JSONException;
import org.json.JSONObject;

public class Artist {

    private User user;
    private String soundCloudLink;

    public Artist(ArtistBuilder builder) {
        this.user = builder.user;
        this.soundCloudLink = builder.soundCloudLink;
    }

        public static Artist fromJson(JSONObject jsonObject) {

            Artist artist;
            // Deserialize json into object fields
            try {
                User user = new User
                        .UserBuilder(jsonObject.getString("User_Id"),
                        jsonObject.getString("User_Name"),
                        jsonObject.getString("Email"),
                        jsonObject.getString("Password"))
                        .withTagline(jsonObject.getString("Tagline"))
                        .withSearchTags("tag tag tag")
                        .withDescription(jsonObject.getString("Description"))
                        .withLocation(jsonObject.getString("Location"))
                        .withFacebookLink(jsonObject.getString("Facebook"))
                        .withWebPageLink(jsonObject.getString("Website"))
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

    public ArtistBuilder(User user) {
        this.user = user;
    }

    public ArtistBuilder updateUser(User user) {
        this.user = user;
        return this;
    }

    public ArtistBuilder withSoundcloudLink(String soundCloudLink) {
        this.soundCloudLink = soundCloudLink;
        return this;
    }

    public Artist build() {
        return new Artist(this);
    }
}

}

