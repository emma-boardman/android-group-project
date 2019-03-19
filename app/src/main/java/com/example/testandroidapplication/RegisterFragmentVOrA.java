package com.example.testandroidapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class RegisterFragmentVOrA extends Fragment {

    private VenueRegistration venueRegistration = new VenueRegistration();
    private ArtistRegistration artistRegistration = new ArtistRegistration();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.register_vora, container,false);

        Button venueBtn = v.findViewById(R.id.user_venue);
        Button artistBtn = v.findViewById(R.id.user_artist);

        venueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container,
                        venueRegistration).commit();

            }
        });

        artistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container,
                        artistRegistration).commit();

            }
        });

        return v;
    }
}
