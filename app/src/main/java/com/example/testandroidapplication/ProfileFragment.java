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
import com.example.testandroidapplication.objects.Review;
import com.example.testandroidapplication.objects.Venue;
import com.example.testandroidapplication.utils.ArtistResult;
import com.example.testandroidapplication.utils.VenueResult;
import com.example.testandroidapplication.utils.WebClientMethods;
import com.google.android.gms.common.util.Strings;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    private String userId;

    private TextView userName;
    private TextView userTagline;
    private TextView userDescription;
    private TextView userRatingLabel;
    private RatingBar userRating;
    private WebView webView;
    private ImageButton userFacebook;
    private ImageButton userInstagram;
    private ImageButton userTwitter;
    private ImageButton userSoundcloud;
    private ImageButton userWebsite;
    private TextView userReviewOne;
    private TextView userReviewDate;
    private TextView userTags;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_profile, container, false);

        webView = v.findViewById(R.id.profile_web_view);

        userName = v.findViewById(R.id.profile_username);
        userTagline = v.findViewById(R.id.profile_tagline);
        userDescription = v.findViewById(R.id.profile_bio);
        userRating = v.findViewById(R.id.profile_rating);
        userRatingLabel = v.findViewById(R.id.profile_rating_text);
        userFacebook = v.findViewById(R.id.profile_facebook);
        userInstagram = v.findViewById(R.id.profile_instagram);
        userTwitter = v.findViewById(R.id.profile_twitter);
        userSoundcloud = v.findViewById(R.id.profile_soundcloud);
        // image should be changed from email to website - want to encourage contact within app messaging, not email
        userWebsite = v.findViewById(R.id.profile_email);
        userReviewOne = v.findViewById(R.id.comment_1);
        userReviewDate = v.findViewById(R.id.comment_1_reviewer_and_date);
        userTags = v.findViewById(R.id.profile_tags);

        return v;
    }

    public void accessUserIdForDB(JSONObject user) throws JSONException {
        Log.i("user", String.valueOf(user));
        userId = user.getString("User_Id");
        if (user.getString("User_type").equals("Artist")) {
            new ReadArtistProfileAsyncTask(this).execute(userId);
        } else {
            new ReadVenueProfileAsyncTask(this).execute(userId);
        }

    }


    private static class ReadArtistProfileAsyncTask extends AsyncTask<String, String, Artist> {

        final ProfileFragment profileFragment;

        private ReadArtistProfileAsyncTask(ProfileFragment profileFragment) {
            this.profileFragment = profileFragment;
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Artist doInBackground(String... params) {

            ArtistResult artistResult = WebClientMethods.readArtistProfile(profileFragment.userId);
            return artistResult.getArtist();
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        protected void onPostExecute(final Artist artist) {

            populateArtistUI(artist);

        }

        private void populateArtistUI(final Artist artist) {

            profileFragment.userName.setText(artist.getUser().getName());

            final ProfileInformation profile = artist.getProfileInformation();
            setTextIfExists(profileFragment.userName, artist.getUser().getName());
            setTextIfExists(profileFragment.userTagline, profile.getTagLine());
            setTextIfExists(profileFragment.userDescription, profile.getDescription());
            setTextIfExists(profileFragment.userDescription, profile.getDescription());

            String listOfTags = String.valueOf(profile.getSearchTags().getTag("Genre"));
            listOfTags = listOfTags.replaceAll("\\[", "").replaceAll("]", "");
            setTextIfExists(profileFragment.userTags, listOfTags);

            String rating = profile.getOverallRatingNum();
            if (!rating.equals("null")) {
                profileFragment.userRating.setRating(Float.parseFloat(rating));
            } else {
                profileFragment.userRating.setVisibility(View.GONE);
                profileFragment.userRatingLabel.setVisibility(View.GONE);
            }


            setImageIfExists(profileFragment.webView, profileFragment.userFacebook, "https://www.facebook.com/", profile.getFacebookLink());
            setImageIfExists(profileFragment.webView, profileFragment.userInstagram, "https://www.instagram.com/", profile.getInstagramLink());
            setImageIfExists(profileFragment.webView, profileFragment.userTwitter, "https://www.twitter.com/", profile.getTwitterLink());
            setImageIfExists(profileFragment.webView, profileFragment.userSoundcloud, "https://www.soundcloud.com/", artist.getSoundCloudLink());
            setImageIfExists(profileFragment.webView, profileFragment.userWebsite, "", profile.getWebPageLink());

            ArrayList<Review> reviews;
            if (profile.getReviews() != null) {
                reviews = profile.getReviews();
                for (Review review : reviews) {
                    setTextIfExists(profileFragment.userReviewOne, review.getVenueComment());
                    setTextIfExists(profileFragment.userReviewDate, review.getVenueName() + ", " + review.getGigDate());
                }
            }

        }
    }


    private static class ReadVenueProfileAsyncTask extends AsyncTask<String, String, Venue> {

        final ProfileFragment profileFragment;

        private ReadVenueProfileAsyncTask(ProfileFragment profileFragment) {
            this.profileFragment = profileFragment;
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Venue doInBackground(String... params) {

            VenueResult venueResult = WebClientMethods.readVenueProfile(profileFragment.userId);
            return venueResult.getVenue();
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        protected void onPostExecute(final Venue venue) {

            populateVenueUI(venue);

        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        private void populateVenueUI(final Venue venue) {

            profileFragment.userName.setText(venue.getUser().getName());

            final ProfileInformation profile = venue.getProfileInformation();
            setTextIfExists(profileFragment.userName, venue.getUser().getName());
            setTextIfExists(profileFragment.userTagline, profile.getTagLine());
            setTextIfExists(profileFragment.userDescription, profile.getDescription());
            setTextIfExists(profileFragment.userDescription, profile.getDescription());

            String listOfTags = String.valueOf(profile.getSearchTags().getTag("Genre"));
            listOfTags = listOfTags.replaceAll("\\[", "").replaceAll("]", "");
            setTextIfExists(profileFragment.userTags, listOfTags);

            String rating = profile.getOverallRatingNum();
            if (!rating.equals("null")) {
                profileFragment.userRating.setRating(Float.parseFloat(rating));
            } else {
                profileFragment.userRating.setVisibility(View.GONE);
                profileFragment.userRatingLabel.setVisibility(View.GONE);
            }

            setImageIfExists(profileFragment.webView, profileFragment.userFacebook, "https://www.facebook.com/", profile.getFacebookLink());
            setImageIfExists(profileFragment.webView, profileFragment.userInstagram, "https://www.instagram.com/", profile.getInstagramLink());
            setImageIfExists(profileFragment.webView, profileFragment.userTwitter, "https://www.twitter.com/", profile.getTwitterLink());
            setImageIfExists(profileFragment.webView, profileFragment.userWebsite, "", profile.getWebPageLink());

            setTextIfExists(profileFragment.userReviewOne, String.valueOf(profile.getReviews()));

        }
    }

    private static void setTextIfExists(TextView textView, @Nullable String text) {
        if (!Strings.isEmptyOrWhitespace(text)) {
            textView.setText(text);
        } else {
            textView.setVisibility(View.GONE);
        }
    }

    private static void setImageIfExists(final WebView webView, ImageButton imageButton, final String baseUrl, final @Nullable String text) {
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
}
