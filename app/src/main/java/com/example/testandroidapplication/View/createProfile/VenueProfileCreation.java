package com.example.testandroidapplication.View.createProfile;

import android.os.AsyncTask;
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

import com.example.testandroidapplication.Presenter.createProfile.IVenueProfileCreationContract;
import com.example.testandroidapplication.Presenter.createProfile.VenueProfileCreationPresenter;
import com.example.testandroidapplication.R;
import com.example.testandroidapplication.View.registerUser.RegisterFragment;
import com.example.testandroidapplication.WelcomeFragement;
import com.example.testandroidapplication.objects.ProfileInformation;
import com.example.testandroidapplication.objects.User;
import com.example.testandroidapplication.objects.Venue;
import com.example.testandroidapplication.utils.CheckNetworkStatus;
import com.example.testandroidapplication.utils.WebClientMethods;

public class VenueProfileCreation extends Fragment implements IVenueProfileCreationContract.View{


    private User user;
    private ProfileInformation profileInformation;
    private VenueProfileCreationPresenter presenter;

    private EditText venueNameEditText;
    private EditText venueTaglineEditText;
    private EditText venueLocationEditText;
    private EditText venueDescriptionEditText;
    private EditText venueFacebookEditText;
    private EditText venueTwitterEditText;
    private EditText venueInstagramEditText;
    private EditText venueWebsiteEditText;
    private EditText venueAddress1EditText;
    private EditText venuePostcodeEditText;

    private String venueNameInput;
    private String venueTaglineInput;
    private String venueLocationInput;
    private String venueDescriptionInput;
    private String venueFacebookInput;
    private String venueTwitterInput;
    private String venueInstagramInput;
    private String venueWebsiteInput;
    private String venueAddress1Input;
    private String venuePostcodeInput;

    Venue venue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.venue_profile_creation, container, false);

        presenter = new VenueProfileCreationPresenter(this);

        venueNameEditText = v.findViewById(R.id.venue_name);
        venueTaglineEditText = v.findViewById(R.id.venue_tagline);
        venueLocationEditText = v.findViewById(R.id.venue_location);
        venueDescriptionEditText = v.findViewById(R.id.venue_description);
        venueFacebookEditText = v.findViewById(R.id.venue_facebook);
        venueTwitterEditText = v.findViewById(R.id.venue_twitter);
        venueInstagramEditText = v.findViewById(R.id.venue_instagram);
        venueWebsiteEditText = v.findViewById(R.id.venue_webpage);
        venueAddress1EditText = v.findViewById(R.id.venue_address_line_1);
        venuePostcodeEditText = v.findViewById(R.id.venue_address_line_postcode);

        Button cancelBtn = v.findViewById(R.id.venue_profile_cancel);
        Button createProfile = v.findViewById(R.id.venue_create_profile);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterFragment registerFragment = new RegisterFragment();

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container,
                        registerFragment, "registerFragment").commit();
            }
        });

        createProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (CheckNetworkStatus.isNetworkAvailable(getActivity().getApplicationContext())) {

                    user = new User("g2YDuIWhlKP6kKDzeetFZV0oAaW2", "TestVenue", "mergeconflict73@gmail.com");

                    venueNameInput = venueNameEditText.getText().toString();
                    venueTaglineInput = venueTaglineEditText.getText().toString();
                    venueLocationInput = venueLocationEditText.getText().toString();
                    venueDescriptionInput = venueDescriptionEditText.getText().toString();
                    venueFacebookInput = venueFacebookEditText.getText().toString();
                    venueTwitterInput = venueTwitterEditText.getText().toString();
                    venueInstagramInput = venueInstagramEditText.getText().toString();
                    venueWebsiteInput = venueWebsiteEditText.getText().toString();
                    venueAddress1Input = venueAddress1EditText.getText().toString();
                    venuePostcodeInput = venuePostcodeEditText.getText().toString();
                    buildVenueObject();

                } else {
                    //Display error message if not connected to internet
                    Toast.makeText(getActivity(),
                            "Unable to connect to internet",
                            Toast.LENGTH_LONG).show();
                }

//                WelcomeFragement welcomeFragement = new WelcomeFragement();
//
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container,
//                        welcomeFragement).commit();
            }
        });
        return v;
    }

    public void buildVenueObject(){

        user.setName(venueNameInput);

        profileInformation = new ProfileInformation
                .ProfileBuilder()
                .withTagline(venueTaglineInput)
                .withLocation(venueLocationInput)
                .withDescription(venueDescriptionInput)
                .withFacebookLink(venueFacebookInput)
                .withTwitterLink(venueTwitterInput)
                .withInstagramLink(venueInstagramInput)
                .withWebPageLink(venueWebsiteInput)
                .build();

        venue = new Venue
                .VenueBuilder(user)
                .withProfileInformation(profileInformation)
                .withAddress1(venueAddress1Input)
                .withPostcode(venuePostcodeInput)
                .build();

        presenter.validateVenueObject(venue);

    }


    public void showToast(String msg){
        Toast.makeText(getActivity(),
                msg, Toast.LENGTH_LONG).show();
    }

    public void showError(String error){
        // target edit text to display an error
    }


}
