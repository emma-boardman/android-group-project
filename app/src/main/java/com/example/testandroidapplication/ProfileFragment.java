package com.example.testandroidapplication;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;


import com.example.testandroidapplication.objects.Artist;
import com.example.testandroidapplication.objects.ProfileInformation;
import com.example.testandroidapplication.objects.Venue;
import com.example.testandroidapplication.utils.ArtistResult;
import com.example.testandroidapplication.utils.VenueResult;
import com.example.testandroidapplication.utils.WebClientMethods;
import com.google.android.gms.common.util.Strings;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileFragment extends Fragment {

    private String userId;

    private TextView userName;
    private TextView userTagline;
    private TextView userDescription;
    private RatingBar userRating;
    private WebView webView;
    private ImageButton userFacebook;
    private ImageButton userInstagram;
    private ImageButton userTwitter;
    private ImageButton userSoundcloud;
    private ImageButton userWebsite;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_profile,container,false);

        webView = v.findViewById(R.id.profile_web_view);

        userName = v.findViewById(R.id.profile_username);
        userTagline = v.findViewById(R.id.profile_tagline);
        userDescription = v.findViewById(R.id.profile_bio);
        userRating = v.findViewById(R.id.profile_rating);
        userFacebook = v.findViewById(R.id.profile_facebook);
        userInstagram = v.findViewById(R.id.profile_instagram);
        userTwitter = v.findViewById(R.id.profile_twitter);
        userSoundcloud = v.findViewById(R.id.profile_soundcloud);
        // image should be changed from email to website - want to encourage contact within app messaging, not email
        userWebsite = v.findViewById(R.id.profile_email);

        return v;
    }

    public void accessUserIdForDB(JSONObject user) throws JSONException {
           Log.i("user", String.valueOf(user));
           userId = user.getString("User_Id");
           if (user.getString("User_type").equals("Artist")){
               new ReadArtistProfileAsyncTask().execute(userId);
           } else {
               new ReadVenueProfileAsyncTask().execute(userId);
           }

    }


    private class ReadArtistProfileAsyncTask extends AsyncTask<String, String, Artist> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Artist doInBackground(String... params) {

            ArtistResult artistResult = WebClientMethods.readArtistProfile(userId);
            return artistResult.getArtist();
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        protected void onPostExecute(final Artist artist) {

            populateArtistUI(artist);

                }
            }


    private class ReadVenueProfileAsyncTask extends AsyncTask<String, String, Venue> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Venue doInBackground(String... params) {

            VenueResult venueResult = WebClientMethods.readVenueProfile(userId);
            return venueResult.getVenue();
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        protected void onPostExecute(final Venue venue) {

            populateVenueUI(venue);

        }
    }

            private void setTextIfExists(TextView textView, @Nullable String text) {
                if (!Strings.isEmptyOrWhitespace(text)) {
                    textView.setText(text);
                } else {
                    textView.setVisibility(View.GONE);
                }
            }

            private void setImageIfExists(ImageButton imageButton, final String baseUrl, final @Nullable String text) {
                if (!Strings.isEmptyOrWhitespace(text)) {
                    imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            webView.loadUrl(baseUrl + text);
                        }
                    });
                } else {
                    imageButton.setVisibility(View.GONE);
                }
            }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)

    private void populateArtistUI(final Artist artist) {

        userName.setText(artist.getUser().getName());

        final ProfileInformation profile = artist.getProfileInformation();
        setTextIfExists(userName, artist.getUser().getName());
        setTextIfExists(userTagline, profile.getTagLine());
        setTextIfExists(userDescription, profile.getDescription());
        setTextIfExists(userDescription, profile.getDescription());

//        userRating.setRating(Float.parseFloat(profile.getOverallRatingNum()));

        setImageIfExists(userFacebook, "https://www.facebook.com/", profile.getFacebookLink());
        setImageIfExists(userInstagram, "https://www.instagram.com/", profile.getInstagramLink());
        setImageIfExists(userTwitter, "https://www.twitter.com/", profile.getTwitterLink());
        setImageIfExists(userSoundcloud, "https://www.soundcloud.com/", artist.getSoundCloudLink());
        setImageIfExists(userWebsite, "", profile.getWebPageLink());

    }

    private void populateVenueUI(final Venue venue) {

        userName.setText(venue.getUser().getName());

        final ProfileInformation profile = venue.getProfileInformation();
        setTextIfExists(userName, venue.getUser().getName());
        setTextIfExists(userTagline, profile.getTagLine());
        setTextIfExists(userDescription, profile.getDescription());
        setTextIfExists(userDescription, profile.getDescription());

//        userRating.setRating(Float.parseFloat(profile.getOverallRatingNum()));

        setImageIfExists(userFacebook, "https://www.facebook.com/", profile.getFacebookLink());
        setImageIfExists(userInstagram, "https://www.instagram.com/", profile.getInstagramLink());
        setImageIfExists(userTwitter, "https://www.twitter.com/", profile.getTwitterLink());
        setImageIfExists(userWebsite, "", profile.getWebPageLink());

    }

}
