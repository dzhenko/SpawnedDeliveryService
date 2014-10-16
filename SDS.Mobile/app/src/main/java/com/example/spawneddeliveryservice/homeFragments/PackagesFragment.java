package com.example.spawneddeliveryservice.homeFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spawneddeliveryservice.R;

public class PackagesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        this.updatePageInfo();

        return rootView;
    }

    public void updatePageInfo() {

    }
}
