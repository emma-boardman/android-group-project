package com.example.testandroidapplication;

import android.annotation.TargetApi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import android.widget.Toast;



import java.util.Objects;

public class NewGigFragment extends Fragment {


    @Nullable
    @Override
    @TargetApi(27)
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.add_gig_to_calendar, container, false);



        CalendarView addingGigCalendarView = v.findViewById(R.id.addingGigCalendarView); // get the reference of CalendarView
        // perform setOnDateChangeListener event on CalendarView
        addingGigCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth){
                // display the selected date by using a toast, this is over-ridden.
                Toast.makeText(Objects.requireNonNull(getActivity()).getApplicationContext(), dayOfMonth + "/" + month++ + "/" + year, Toast.LENGTH_LONG).show();

                if ((dayOfMonth == 28) && (month == 4) && (year == 2019)) {
                    Toast.makeText(getActivity().getApplicationContext(), "Gig Already!", Toast.LENGTH_SHORT).show();

                } else { if ((dayOfMonth == 5) && (month == 5) && (year == 2019)){
                    Toast.makeText(getActivity().getApplicationContext(), "Gig Already!", Toast.LENGTH_SHORT).show();


                } else { if ((dayOfMonth == 8) && (month == 5) && (year == 2019)){
                    Toast.makeText(getActivity().getApplicationContext(), "Gig Already!", Toast.LENGTH_SHORT).show();

                } else { if ((dayOfMonth == 17) && (month == 5) && (year == 2019)){
                    Toast.makeText(getActivity().getApplicationContext(), "Gig Already!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Free Day!", Toast.LENGTH_SHORT).show();

                }
                }
                }
                }
            }
        });
    return v;
    }
}