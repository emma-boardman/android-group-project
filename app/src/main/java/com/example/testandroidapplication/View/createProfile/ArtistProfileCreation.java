package com.example.testandroidapplication.View.createProfile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testandroidapplication.Presenter.createProfile.ArtistProfileCreationPresenter;
import com.example.testandroidapplication.Presenter.createProfile.IArtistProfileCreationContract;
import com.example.testandroidapplication.Presenter.createProfile.VenueProfileCreationPresenter;
import com.example.testandroidapplication.R;
import com.example.testandroidapplication.WelcomeFragement;
import com.example.testandroidapplication.objects.Artist;
import com.example.testandroidapplication.objects.ProfileInformation;
import com.example.testandroidapplication.objects.User;
import com.example.testandroidapplication.utils.CheckNetworkStatus;

public class ArtistProfileCreation extends Fragment implements IArtistProfileCreationContract.View{


    private User user;
    private ProfileInformation profileInformation;
    private ArtistProfileCreationPresenter presenter;

    private EditText artistNameEditText;
    private EditText artistTaglineEditText;
    private EditText artistLocationEditText;
    private EditText artistDescriptionEditText;
    private EditText artistFacebookEditText;
    private EditText artistTwitterEditText;
    private EditText artistInstagramEditText;
    private EditText artistWebsiteEditText;
    private EditText artistSoundcloudEditText;

    private String artistNameInput;
    private String artistTaglineInput;
    private String artistLocationInput;
    private String artistDescriptionInput;
    private String artistFacebookInput;
    private String artistTwitterInput;
    private String artistInstagramInput;
    private String artistWebsiteInput;
    private String artistSoundcloudInput;

    Artist artist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.artist_profile_creation, container, false);

        presenter = new ArtistProfileCreationPresenter(this);

        artistNameEditText = v.findViewById(R.id.artist_name);
        artistTaglineEditText = v.findViewById(R.id.artist_tagline);
        artistLocationEditText = v.findViewById(R.id.artist_location);
        artistDescriptionEditText = v.findViewById(R.id.artist_description);
        artistFacebookEditText = v.findViewById(R.id.artist_facebook);
        artistTwitterEditText = v.findViewById(R.id.artist_twitter);
        artistInstagramEditText = v.findViewById(R.id.artist_instagram);
        artistWebsiteEditText = v.findViewById(R.id.artist_webpage);
        artistSoundcloudEditText = v.findViewById(R.id.artist_soundcloud_link);

        Button createProfile = v.findViewById(R.id.artist_create_profile);

        createProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (CheckNetworkStatus.isNetworkAvailable(getActivity().getApplicationContext())) {

                    user = new User("Ud0ZuQMdhoaNHGOUktI6BTjLzWS2", "TestArtist", "oasistest@gmail.com");

                    artistNameInput = artistNameEditText.getText().toString();
                    artistTaglineInput = artistTaglineEditText.getText().toString();
                    artistLocationInput = artistLocationEditText.getText().toString();
                    artistDescriptionInput = artistDescriptionEditText.getText().toString();
                    artistFacebookInput = artistFacebookEditText.getText().toString();
                    artistTwitterInput = artistTwitterEditText.getText().toString();
                    artistInstagramInput = artistInstagramEditText.getText().toString();
                    artistWebsiteInput = artistWebsiteEditText.getText().toString();
                    artistSoundcloudInput = artistSoundcloudEditText.getText().toString();
                    buildArtistObject();

                } else {
                    //Display error message if not connected to internet
                    Toast.makeText(getActivity(),
                            "Unable to connect to internet",
                            Toast.LENGTH_LONG).show();
                }
                WelcomeFragement welcomeFragement = new WelcomeFragement();

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container,
                        welcomeFragement).commit();

            }
        });

        return v;
    }

    public void buildArtistObject(){

        user.setName(artistNameInput);

        profileInformation = new ProfileInformation
                .ProfileBuilder()
                .withTagline(artistTaglineInput)
                .withLocation(artistLocationInput)
                .withDescription(artistDescriptionInput)
                .withFacebookLink(artistFacebookInput)
                .withTwitterLink(artistTwitterInput)
                .withInstagramLink(artistInstagramInput)
                .withWebPageLink(artistWebsiteInput)
                .build();

        artist = new Artist
                .ArtistBuilder(user)
                .withProfileInformation(profileInformation)
                .withSoundcloudLink(artistSoundcloudInput)
                .build();

        presenter.validateArtistObject(artist);

    }

    public void showToast(String msg){
//        Toast.makeText(getActivity(),
//                msg, Toast.LENGTH_LONG).show();
    }

    public void showError(String error){
        // target edit text to display an error
    }
}
