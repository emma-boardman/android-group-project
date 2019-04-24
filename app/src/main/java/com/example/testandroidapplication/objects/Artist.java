package com.example.testandroidapplication.objects;

import com.example.testandroidapplication.utils.JsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.testandroidapplication.utils.JsonUtils.addStringToJson;
import static com.example.testandroidapplication.utils.JsonUtils.getStringOrNull;
import static com.example.testandroidapplication.utils.JsonUtils.merge;

public class Artist implements Entity, JsonWritable {

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
                    .withReviews(Review.fromJson(JsonUtils.getJSONArrayOrNull(jsonObject, "Reviews")))
                    .withSearchTags(Tags.fromJson(JsonUtils.getJSONObjectOrNull(jsonObject, "Tags")))
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

    @Override
    public JSONObject toJson() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        addStringToJson(jsonObject, "Soundcloud", this.getSoundCloudLink());
        final JSONObject user = getUser().toJson();
        final JSONObject profile = getProfileInformation().toJson();
        return merge(jsonObject, profile, user);
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public ProfileInformation getProfileInformation() {
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

        public ArtistBuilder withProfileInformation(ProfileInformation profileInformation) {
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

