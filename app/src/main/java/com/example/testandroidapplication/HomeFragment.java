package com.example.testandroidapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testandroidapplication.Adapter.ContentUserAdapter;
import com.example.testandroidapplication.Model.HomeItemList;
import com.example.testandroidapplication.Model.User;

import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;

    private ContentUserAdapter contentUserAdapter;
    private java.util.List<User> mUsers;

    private List<HomeItemList> homeItems;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container,false);

        recyclerView = v.findViewById(R.id.home_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return v;
    }
}
