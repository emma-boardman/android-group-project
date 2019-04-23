package com.example.testandroidapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.testandroidapplication.View.registerUser.RegisterFragment;

public class VenueProfileCreation extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.venue_profile_creation, container, false);

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
                WelcomeFragement welcomeFragement = new WelcomeFragement();

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container,
                        welcomeFragement).commit();
            }
        });

        return v;


    }
}
