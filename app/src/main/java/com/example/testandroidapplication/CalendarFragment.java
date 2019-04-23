package com.example.testandroidapplication;

import android.annotation.TargetApi;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

import android.support.v7.widget.LinearLayoutManager;

import com.example.testandroidapplication.Adapter.CustomAdapter;
import com.example.testandroidapplication.objects.Gig;


public class CalendarFragment extends Fragment {

    CalendarView simpleCalendarView;
    ArrayList<Gig> theGig;

    @Nullable
    @Override
    @TargetApi(27)
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.calendar_view, container, false);

        simpleCalendarView = v.findViewById(R.id.simpleCalendarView); // get the reference of CalendarView


        // perform setOnDateChangeListener event on CalendarView
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // display the selected date by using a toast
                Toast.makeText(Objects.requireNonNull(getActivity()).getApplicationContext(), dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
            }
        });

        //RecylcerView
        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
        //LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Objects.requireNonNull(getActivity()).getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        //  call the constructor of CustomAdapter to get gigs in places


        CustomAdapter customAdapter = new CustomAdapter(getActivity(), theGig = new ArrayList<>());
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView

        return v;
    }
}


    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_view);
        simpleCalendarView = (CalendarView) findViewById(R.id.simpleCalendarView); // get the reference of CalendarView


        // perform setOnDateChangeListener event on CalendarView
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth){
                // display the selected date by using a toast
                Toast.makeText(getApplicationContext(), dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
            }
        });

        //RecylcerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        //  call the constructor of CustomAdapter to get gigs in places



        CustomAdapter customAdapter = new CustomAdapter(CalendarFragment.this, theGig = new ArrayList<>());
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
    }
}*/



