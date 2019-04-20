package com.example.testandroidapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ArtistProfileCreation extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.artist_profile_creation, container, false);

        Button createProfile = v.findViewById(R.id.artist_create_profile);

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
