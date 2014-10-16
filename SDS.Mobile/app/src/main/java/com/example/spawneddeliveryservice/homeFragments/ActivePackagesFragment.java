package com.example.spawneddeliveryservice.homeFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spawneddeliveryservice.R;

public class ActivePackagesFragment extends Fragment {

    public ActivePackagesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_packages_active_packages, container, false);
        return rootView;
    }
}
