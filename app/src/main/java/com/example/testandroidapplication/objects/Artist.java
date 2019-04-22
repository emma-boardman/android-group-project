package com.example.testandroidapplication.objects;

import android.support.annotation.Nullable;

import com.google.android.gms.common.util.Strings;

import org.json.JSONArray;
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
                        .withTagline(getStringOrNull(jsonObject, "Tagline"))
                        .withDescription(getStringOrNull(jsonObject, "Description"))
                        .withLocation(getStringOrNull(jsonObject, "Location"))
                        .withFacebookLink(getStringOrNull(jsonObject, "Facebook"))
                        .withInstagramLink(getStringOrNull(jsonObject, "Instagram"))
                        .withTwitterLink(getStringOrNull(jsonObject, "Twitter"))
                        .withWebPageLink(getStringOrNull(jsonObject, "Website"))
                        .withOverallRating(getStringOrNull(jsonObject, "Overall_Rating"))
                        .withReviews(Review.fromJson(getJSONArrayOrNull(jsonObject,"Reviews")))
                        .withSearchTags(Tags.fromJson(getJSONObjectOrNull(jsonObject, "Tags")))
                        .build();

                artist = new Artist
                        .ArtistBuilder(user)
                        .withProfileInformation(profileInformation)
                        .withSoundcloudLink(getStringOrNull(jsonObject, "Soundcloud"))
                        .build();

            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
            // Return new object
            return artist;
        }

        @Nullable
        private static String getStringOrNull(JSONObject jsonObject, String name) throws JSONException {
            if (jsonObject.has(name)) return jsonObject.getString(name);
            else return null;
        }

        @Nullable
        private static JSONArray getJSONArrayOrNull(JSONObject jsonObject, String name) throws JSONException {
            if (jsonObject.has(name)) return jsonObject.getJSONArray(name);
            else return null;
        }

        @Nullable
        private static JSONObject getJSONObjectOrNull(JSONObject jsonObject, String name) throws JSONException {
            if (jsonObject.has(name)) return jsonObject.getJSONObject(name);
            else return null;
        }

    public JSONObject toJson() throws JSONException {

        JSONObject jsonObject = new JSONObject();
        addStringToJson(jsonObject, "User_Id", this.getUser().getUserID());
        addStringToJson(jsonObject, "User_Name", this.getUser().getName());
        addStringToJson(jsonObject, "Email", this.getUser().getEmail());
        addStringToJson(jsonObject, "Facebook", this.getProfileInformation().getFacebookLink());
        addStringToJson(jsonObject, "Instagram", this.getProfileInformation().getInstagramLink());
        addStringToJson(jsonObject, "Twitter", this.getProfileInformation().getTwitterLink());
        addStringToJson(jsonObject, "Website", this.getProfileInformation().getWebPageLink());
        addStringToJson(jsonObject, "Tagline", this.getProfileInformation().getTagLine());
        addStringToJson(jsonObject, "Description", this.getProfileInformation().getDescription());
        addStringToJson(jsonObject, "Location", this.getProfileInformation().getLocation());
        addStringToJson(jsonObject, "Soundcloud", this.getSoundCloudLink());
        Tags searchTags = this.getProfileInformation().getSearchTags();
        if (searchTags.hasTags()) {
            jsonObject.accumulate("Tags", searchTags.toJson());
        }
        return jsonObject;
    }
    
    private void addStringToJson(JSONObject jsonObject, String name, String value) throws JSONException {
        if (!Strings.isEmptyOrWhitespace(value)) {
            jsonObject.accumulate(name, value);
        }
    }

    public User getUser(){
        return user;
    }

    public ProfileInformation getProfileInformation(){
        return profileInformation;
    }

    public String getSoundCloudLink() {
            return soundCloudLink;
        }


public static class ArtistBuilder {

    private User user;
    private ProfileInformation profileInformation;
    private String soundCloudLink;

    public ArtistBuilder(User user) {
        this.user = user;
    }

    public ArtistBuilder updateUser(User user) {
        this.user = user;
        return this;
    }

    public ArtistBuilder withProfileInformation(ProfileInformation profileInformation){
        this.profileInformation = profileInformation;
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

