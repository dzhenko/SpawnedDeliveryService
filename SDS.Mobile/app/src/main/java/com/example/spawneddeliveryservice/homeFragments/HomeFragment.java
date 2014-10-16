package com.example.spawneddeliveryservice.homeFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.spawneddeliveryservice.R;

public class HomeFragment extends Fragment {
    private TextView tvHomeInformation;
    private String mStatsOverview;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        Bundle bundle = this.getArguments();
        this.mStatsOverview = bundle.getString("statsOverview");
        this.tvHomeInformation = (TextView) rootView.findViewById(R.id.tvHomeInformation);

        this.updatePageInfo();

        return rootView;
    }

    public void updatePageInfo() {
        this.tvHomeInformation.setText(this.mStatsOverview);
    }
}
