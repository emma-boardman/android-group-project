package com.example.testandroidapplication.View.createProfile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.testandroidapplication.Presenter.createProfile.ArtistProfileCreationPresenter;
import com.example.testandroidapplication.Presenter.createProfile.IArtistProfileCreationContract;
import com.example.testandroidapplication.R;
import com.example.testandroidapplication.WelcomeFragement;
import com.example.testandroidapplication.ProfileFragment;
import com.example.testandroidapplication.objects.Artist;
import com.example.testandroidapplication.objects.ProfileInformation;
import com.example.testandroidapplication.objects.Tags;
import com.example.testandroidapplication.objects.User;
import com.example.testandroidapplication.utils.CheckNetworkStatus;

import java.util.ArrayList;

import static android.R.layout.simple_spinner_item;

public class ArtistProfileCreation extends Fragment implements IArtistProfileCreationContract.View {


    private User user;
    private ProfileInformation profileInformation;
    private ArtistProfileCreationPresenter presenter;

    private EditText artistNameEditText;
    private EditText artistTaglineEditText;
    private EditText artistLocationEditText;
    private Spinner artistExperienceSpinner;
    private Spinner artistGenreSpinner;
    private Spinner artistInstrumentSpinner;
    private Spinner artistGroupTypeSpinner;
    private Spinner artistLookingForSpinner;
    private EditText artistDescriptionEditText;
    private EditText artistFacebookEditText;
    private EditText artistTwitterEditText;
    private EditText artistInstagramEditText;
    private EditText artistWebsiteEditText;
    private EditText artistSoundcloudEditText;


    private String artistNameInput;
    private String artistTaglineInput;
    private String artistLocationInput;
    private Tags artistTags;
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

        presenter.readArtistTags();

        artistNameEditText = v.findViewById(R.id.artist_name);
        artistTaglineEditText = v.findViewById(R.id.artist_tagline);
        artistLocationEditText = v.findViewById(R.id.artist_location);
        artistExperienceSpinner = v.findViewById(R.id.artist_tag_search_spinner_experience);
        artistGenreSpinner = v.findViewById(R.id.artist_tag_search_spinner_genre);
        artistInstrumentSpinner = v.findViewById(R.id.artist_tag_search_spinner_instruments);
        artistGroupTypeSpinner = v.findViewById(R.id.artist_tag_search_spinner_group_type);
        artistLookingForSpinner = v.findViewById(R.id.artist_tag_search_spinner_looking_for);
        artistDescriptionEditText = v.findViewById(R.id.artist_description);
        artistFacebookEditText = v.findViewById(R.id.artist_facebook);
        artistTwitterEditText = v.findViewById(R.id.artist_twitter);
        artistInstagramEditText = v.findViewById(R.id.artist_instagram);
        artistWebsiteEditText = v.findViewById(R.id.artist_webpage);
        artistSoundcloudEditText = v.findViewById(R.id.artist_soundcloud_link);

        Button createProfile = v.findViewById(R.id.artist_create_profile);

        Button viewProfile = v.findViewById(R.id.artist_view_profile);

        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProfileFragment profileFragment = new ProfileFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container,
                        profileFragment).commit();

            }
        });

        createProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (CheckNetworkStatus.isNetworkAvailable(getActivity().getApplicationContext())) {

                    user = new User("Ud0ZuQMdhoaNHGOUktI6BTjLzWS2", "TestArtist", "oasistest@gmail.com");

                    artistNameInput = artistNameEditText.getText().toString();
                    artistTaglineInput = artistTaglineEditText.getText().toString();
                    artistLocationInput = artistLocationEditText.getText().toString();

                    artistTags = new Tags();

                    if (!artistExperienceSpinner.getSelectedItem().toString().contains("Select")){
                        artistTags.addTag("Experience", artistExperienceSpinner.getSelectedItem().toString());
                    }
                    if (!artistGenreSpinner.getSelectedItem().toString().contains("Select")){
                        artistTags.addTag("Genre", artistGenreSpinner.getSelectedItem().toString());
                    }

//                    artistTags.addTag("Experience", artistExperienceSpinner.getSelectedItem().toString());
//                    artistTags.addTag("Genre", artistGenreSpinner.getSelectedItem().toString());
//                    artistTags.addTag("Instrument", artistInstrumentSpinner.getSelectedItem().toString());
//                    artistTags.addTag("Group Type", artistGroupTypeSpinner.getSelectedItem().toString());
//                    artistTags.addTag("Looking For", artistLookingForSpinner.getSelectedItem().toString());

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
                .withSearchTags(artistTags)
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

    public void showExperienceSpinner(ArrayList<String> experienceTagList){

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.spinner_item, experienceTagList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        artistExperienceSpinner.setAdapter(adapter);


    }

    public void showGenreSpinner(ArrayList<String> genreTagList){

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.spinner_item, genreTagList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        artistGenreSpinner.setAdapter(adapter);
    }

    public void showInstrumentsSpinner(ArrayList<String> instrumentsTagList){

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.spinner_item, instrumentsTagList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        artistInstrumentSpinner.setAdapter(adapter);
    }

    public void showGroupTypeSpinner(ArrayList<String> groupTypeTagList){

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.spinner_item, groupTypeTagList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        artistGroupTypeSpinner.setAdapter(adapter);
    }

    public void showLookingForSpinner(ArrayList<String> lookingForTagList){

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.spinner_item, lookingForTagList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        artistLookingForSpinner.setAdapter(adapter);
    }

}

