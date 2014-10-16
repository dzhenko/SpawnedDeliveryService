package com.example.spawneddeliveryservice.homeFragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.spawneddeliveryservice.R;
import com.example.spawneddeliveryservice.adapters.FinishedPackagesAdapter;
import com.example.spawneddeliveryservice.models.FinishedPackageDataModel;
import com.example.spawneddeliveryservice.tasks.FinishedPackagesTask;

import java.util.ArrayList;

public class FinishedPackagesFragment extends Fragment {
    private ArrayList<FinishedPackageDataModel> mFinishedPackages;
    private Context context;
    private GridView gvFinishedPackages;

    public FinishedPackagesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_packages_finished_packages, container, false);

        this.context = container.getContext();
        this.reloadPageInfo();
        this.gvFinishedPackages = (GridView) rootView.findViewById(R.id.gvHomePackagesFinishedPackages);

        return rootView;
    }

    private void reloadPageInfo() {
        FinishedPackagesTask packagesTask = new FinishedPackagesTask(this);
        packagesTask.execute();
    }

    public void updatePageInfo(ArrayList<FinishedPackageDataModel> finishedPackages) {
        this.mFinishedPackages = finishedPackages;
        this.gvFinishedPackages.setAdapter(new FinishedPackagesAdapter(this.context, this.mFinishedPackages));
    }
}
