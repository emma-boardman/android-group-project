package com.example.testandroidapplication;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import android.support.v7.widget.LinearLayoutManager;
import com.example.testandroidapplication.Adapter.CustomAdapter;


public class CalendarFragment extends Fragment {


    CalendarView simpleCalendarView;
    ArrayList theGig = new ArrayList<>(Arrays.asList("DippidyDooBAR", "7PM", "Funk", "No money"));

    @Nullable
    @Override
    @TargetApi(27)
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.calendar_view, container, false);
        simpleCalendarView = v.findViewById(R.id.simpleCalendarView); // get the reference of CalendarView


        //RecyclerView
        final RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
        //LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Objects.requireNonNull(getActivity()).getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        //  call the constructor of CustomAdapter to get gigs in places

        final CustomAdapter customAdapter = new CustomAdapter(getActivity(), theGig);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView


        // perform setOnDateChangeListener event on CalendarView
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth){
                // display the selected date by using a toast, this is over-ridden.
                Toast.makeText(Objects.requireNonNull(getActivity()).getApplicationContext(), dayOfMonth + "/" + month++ + "/" + year, Toast.LENGTH_LONG).show();
                TextView name = v.findViewById(R.id.text);
                TextView time = v.findViewById(R.id.text1);
                TextView genre = v.findViewById(R.id.text2);
                TextView notes = v.findViewById(R.id.text3);
                TextView furtherNotes = v.findViewById(R.id.text4);

                //if (dayOfMonth == gigDay && month == gigMonth && year == gigYear){
                // name.setText(getUserInputName);
                // time.setText("getUserInputTime");
                // genre.setText("getUserInputGenre");
                // notes.setText("getUserInputNotes");
                // furtherNotes.setText("getUserInputFurtherNotes");
                //}
                //Hardcoded if
                if ((dayOfMonth == 28) && (month == 4) && (year == 2019)) {
                    Toast.makeText(getActivity().getApplicationContext(), "Gig Today!", Toast.LENGTH_SHORT).show();

                    name.setText("DippidyDooBAR");
                    time.setText("7PM");
                    genre.setText("Funk");
                    notes.setText("Bring Extension");
                    furtherNotes.setText("No Money");

                } else if ((dayOfMonth == 5) && (month == 5) && (year == 2019)){
                    Toast.makeText(getActivity().getApplicationContext(), "Gig Today!", Toast.LENGTH_SHORT).show();
                    name.setText("McSorleys");
                    time.setText("8PM");
                    genre.setText("Banging Irish Tunes");
                    notes.setText("Student Discount");
                    furtherNotes.setText("£5 per song not booed");

                } else if ((dayOfMonth == 8) && (month == 5) && (year == 2019)){
                    Toast.makeText(getActivity().getApplicationContext(), "Gig Today!", Toast.LENGTH_SHORT).show();
                    name.setText("Banshee Labyrinth");
                    time.setText("7.30PM");
                    genre.setText("Korean Pop");
                    notes.setText("Bring drumsticks");
                    furtherNotes.setText("Paid in drinks");

                } else if ((dayOfMonth == 17) && (month == 5) && (year == 2019)){
                    Toast.makeText(getActivity().getApplicationContext(), "Gig Today!", Toast.LENGTH_SHORT).show();
                    name.setText("Banshee Labyrinth");
                    time.setText("7PM-1AM");
                    genre.setText("Jazz-Fusion");
                    notes.setText("Bring Presents");
                    furtherNotes.setText("'Experience'");

                } else Toast.makeText(getActivity().getApplicationContext(),"No Events today!", Toast.LENGTH_SHORT).show();

            }
        });
        return v;
    }


    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_view);
        simpleCalendarView = findViewById(R.id.simpleCalendarView); // get the reference of CalendarView


        //RecyclerView
        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        //LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        //  call the constructor of CustomAdapter to get gigs in places

        final CustomAdapter customAdapter = new CustomAdapter(CalendarActivity.this, theGig);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView


        // perform setOnDateChangeListener event on CalendarView
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth){
                // display the selected date by using a toast, this is over-ridden.
                Toast.makeText(getApplicationContext(), dayOfMonth + "/" + month++ + "/" + year, Toast.LENGTH_LONG).show();
                TextView name = findViewById(R.id.text);
                TextView time = findViewById(R.id.text1);
                TextView genre = findViewById(R.id.text2);
                TextView notes = findViewById(R.id.text3);
                TextView furtherNotes = findViewById(R.id.text4);

                //if (dayOfMonth == gigDay && month == gigMonth && year == gigYear){
                // name.setText(getUserInputName);
                // time.setText("getUserInputTime");
                // genre.setText("getUserInputGenre");
                // notes.setText("getUserInputNotes");
                // furtherNotes.setText("getUserInputFurtherNotes");
                //}
                //Hardcoded if
                if ((dayOfMonth == 28) && (month == 4) && (year == 2019)) {
                    Toast.makeText(getApplicationContext(), "Gig Today!", Toast.LENGTH_SHORT).show();

                    name.setText("DippidyDooBAR");
                    time.setText("7PM");
                    genre.setText("Funk");
                    notes.setText("Bring Extension");
                    furtherNotes.setText("No Money");

                } else if ((dayOfMonth == 5) && (month == 5) && (year == 2019)){
                    Toast.makeText(getApplicationContext(), "Gig Today!", Toast.LENGTH_SHORT).show();
                    name.setText("McSorleys");
                    time.setText("8PM");
                    genre.setText("Banging Irish Tunes");
                    notes.setText("Student Discount");
                    furtherNotes.setText("£5 per song not booed");

                } else if ((dayOfMonth == 8) && (month == 5) && (year == 2019)){
                    Toast.makeText(getApplicationContext(), "Gig Today!", Toast.LENGTH_SHORT).show();
                    name.setText("Banshee Labyrinth");
                    time.setText("7.30PM");
                    genre.setText("Korean Pop");
                    notes.setText("Bring drumsticks");
                    furtherNotes.setText("Paid in drinks");

                } else if ((dayOfMonth == 17) && (month == 5) && (year == 2019)){
                        Toast.makeText(getApplicationContext(), "Gig Today!", Toast.LENGTH_SHORT).show();
                        name.setText("Banshee Labyrinth");
                        time.setText("7PM-1AM");
                        genre.setText("Jazz-Fusion");
                        notes.setText("Bring Presents");
                        furtherNotes.setText("'Experience'");

                } else Toast.makeText(getApplicationContext(),"No Events today!", Toast.LENGTH_SHORT).show();

            }
    });


    }*/

}
