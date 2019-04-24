package com.example.testandroidapplication;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testandroidapplication.Adapter.ContentUserAdapter;
import com.example.testandroidapplication.Model.HomeItemList;
import com.example.testandroidapplication.objects.Entity;
import com.example.testandroidapplication.utils.WebClientMethods;

import java.util.List;

public class HomeFragment extends Fragment {

    private ContentUserAdapter contentUserAdapter;

    private List<HomeItemList> homeItems;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container,false);

        RecyclerView recyclerView = v.findViewById(R.id.home_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // populate mUsers with Artist objects (to be refactored to populate artist or venue dependent on logged in user
        new ReadUserListAsyncTask().execute();

        return v;
    }


    // to be refactored out of fragment
    private class ReadUserListAsyncTask extends AsyncTask<String, String, List<Entity>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected List<Entity> doInBackground(String... params) {
            new WebClientMethods();
            List<Entity> mUsers = WebClientMethods.readUserIds();
            return mUsers;
        }

    }

}
