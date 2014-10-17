package com.example.spawneddeliveryservice.homeFragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.spawneddeliveryservice.R;
import com.example.spawneddeliveryservice.adapters.OwnPackagesAdapter;
import com.example.spawneddeliveryservice.models.OwnPackageDataModel;
import com.example.spawneddeliveryservice.tasks.OwnPackagesTask;

import java.util.ArrayList;

public class OwnPackagesFragment extends Fragment {
    private ArrayList<OwnPackageDataModel> mOwnPackages;
    private Context context;
    private GridView gvOwnPackages;

    public OwnPackagesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_packages_own_packages, container, false);

        this.context = container.getContext();
        this.reloadPageInfo();
        this.gvOwnPackages = (GridView) rootView.findViewById(R.id.gvHomePackagesOwnPackages);

        return rootView;
    }

    private void reloadPageInfo() {
        OwnPackagesTask packagesTask = new OwnPackagesTask(this);
        packagesTask.execute();
    }

    public void updatePageInfo(ArrayList<OwnPackageDataModel> ownPackages) {
        this.mOwnPackages = ownPackages;
        this.gvOwnPackages.setAdapter(new OwnPackagesAdapter(this.context, this.mOwnPackages));
    }
}
