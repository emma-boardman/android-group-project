package com.example.testandroidapplication.View.createProfile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testandroidapplication.Presenter.createProfile.VenueProfileCreationPresenter;
import com.example.testandroidapplication.Presenter.createProfile.IVenueProfileCreationContract;
import com.example.testandroidapplication.R;
import com.example.testandroidapplication.utils.CheckNetworkStatus;
import com.example.testandroidapplication.objects.User;
import com.example.testandroidapplication.objects.Venue;

public class VenueProfileCreation extends Fragment implements IVenueProfileCreationContract.View{


    private User user;
    private User.UserBuilder userBuilder;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.venue_profile_creation, container, false);

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

        Button createProfileBtn = v.findViewById(R.id.venue_create_profile);

        createProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if network is available, validate the user input
                if (CheckNetworkStatus.isNetworkAvailable(getActivity().getApplicationContext())) {

                    userBuilder = new User.UserBuilder("1", "Stone Temple Pilots", "stone@temple.com");
                    user = userBuilder.build();

                    venueNameInput = venueNameEditText.getText().toString();
                    if (!venueNameInput.equals("")){
                        // run some validation, let the user know if there is a problem
                    }

                    venueTaglineInput = venueTaglineEditText.getText().toString();
                    if (!venueTaglineInput.equals("")){
                        // run some validation, let the user know if there is a problem
                    }

                    venueLocationInput = venueLocationEditText.getText().toString();
                    if (!venueLocationInput.equals("")){
                        // run some validation, let the user know if there is a problem
                    }

                    venueDescriptionInput = VenueProfileCreation.this.venueDescriptionEditText.getText().toString();
                    if (!venueLocationInput.equals("")){
                        // run some validation, let the user know if there is a problem
                    }

                    venueFacebookInput = venueFacebookEditText.getText().toString();
                    if (!venueFacebookInput.equals("")){
                        // run some validation, let the user know if there is a problem
                    }

                    venueTwitterInput = venueTwitterEditText.getText().toString();
                    if (!venueTwitterInput.equals("")){
                        // run some validation, let the user know if there is a problem
                    }

                    venueInstagramInput = venueInstagramEditText.getText().toString();
                    if (!venueInstagramInput.equals("")){
                        // run some validation, let the user know if there is a problem
                    }

                    venueWebsiteInput = venueWebsiteEditText.getText().toString();
                    if (!venueWebsiteInput.equals("")){
                        // run some validation, let the user know if there is a problem
                    }

                    venueAddress1Input = venueAddress1EditText.getText().toString();
                    if (!venueAddress1Input.equals("")){
                        // run some validation, let the user know if there is a problem
                    }

                    venuePostcodeInput = venuePostcodeEditText.getText().toString();
                    if (!venuePostcodeInput.equals("")){
                        // run some validation, let the user know if there is a problem
                        Log.i("tag: ", "validation would be run here");
                    }

                    buildVenueObject();


                } else {
                    //Display error message if not connected to internet
                    Toast.makeText(getActivity(),
                            "Unable to connect to internet",
                            Toast.LENGTH_LONG).show();
                }
            }

        });

        return v;

    }

    public void buildVenueObject(){

        user = userBuilder
                .withName(venueNameInput)
                .withTagline(venueTaglineInput)
                .withLocation(venueLocationInput)
                .withDescription(venueDescriptionInput)
                .withFacebookLink(venueFacebookInput)
                .withTwitterLink(venueTwitterInput)
                .withInstagramLink(venueInstagramInput)
                .withWebPageLink(venueWebsiteInput)
                .build();

        Venue venue = new Venue
                .VenueBuilder(user)
                .withAddress1(venueAddress1Input)
                .withPostcode(venuePostcodeInput)
                .build();

        presenter.processVenueObject(venue);
    }

    public void showToast(String msg){
        Toast.makeText(getActivity(),
                msg, Toast.LENGTH_LONG).show();
    }

    public void showError(String error){
        // target edit text to display an error
    }


}
