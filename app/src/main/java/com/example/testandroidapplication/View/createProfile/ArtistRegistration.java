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

import com.example.testandroidapplication.R;

public class ArtistRegistration extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.artist_profile_creation, container, false);

        Button artistRegister = v.findViewById(R.id.venue_register);
        Button venueCancel = v.findViewById(R.id.venue_cancel);
        EditText artistEmail = v.findViewById(R.id.user_email);
        EditText artistPassword = v.findViewById(R.id.user_password);
        EditText venueConfirmPassword = v.findViewById(R.id.user_confirm_password);
        final RegisterFragmentVOrA registerFragmentVOrA = new RegisterFragmentVOrA();

        venueCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container,
                        registerFragmentVOrA).commit();

            }
        });

        return v;
    }
}
