package com.example.testandroidapplication.Adapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testandroidapplication.R;
import com.example.testandroidapplication.objects.Gig;

import java.util.ArrayList;




    public class CustomAdapter extends RecyclerView.Adapter {

        ArrayList <Gig> gigArrayList = new ArrayList<>();
        Context context;

        public CustomAdapter(Context context, ArrayList gigArrayList) {
            this.context = context;
            this.gigArrayList = gigArrayList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // infalte the item Layout
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_calendar, parent, false);
            // set the view's size, margins, paddings and layout parameters
            MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        }


        public void onBindViewHolder(MyViewHolder holder, final int position) {
            // set the data in items
            //may need to change to gig title
            //A title of their choice, possible default of "Venue, Time"
            holder.name.setText(gigArrayList.get(position).getNotes());
            // implement setOnClickListener event on item view.
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // display a toast with person name on item click
                    Toast.makeText(context, gigArrayList.get(position).getNotes(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return gigArrayList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView name;// init the item view's

            public MyViewHolder(View itemView) {
                super(itemView);
                // get the reference of item view's
                name = (TextView) itemView.findViewById(R.id.name);
            }
        }
    }
