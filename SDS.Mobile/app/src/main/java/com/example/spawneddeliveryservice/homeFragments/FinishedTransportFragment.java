package com.example.spawneddeliveryservice.homeFragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.spawneddeliveryservice.R;
import com.example.spawneddeliveryservice.adapters.FinishedTransportsAdapter;
import com.example.spawneddeliveryservice.models.FinishedTransportDataModel;
import com.example.spawneddeliveryservice.tasks.FinishedTransportsTask;

import java.util.ArrayList;

public class FinishedTransportFragment extends Fragment {
    private ArrayList<FinishedTransportDataModel> mFinishedTransport;
    private Context context;
    private GridView gvFinishedTransports;

    public FinishedTransportFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_transports_finished_transports, container, false);

        this.context = container.getContext();
        this.reloadPageInfo();
        this.gvFinishedTransports = (GridView) rootView.findViewById(R.id.gvHomeTransportsFinishedTransports);

        return rootView;
    }

    private void reloadPageInfo() {
        FinishedTransportsTask transportsTask = new FinishedTransportsTask(this);
        transportsTask.execute();
    }

    public void updatePageInfo(ArrayList<FinishedTransportDataModel> finishedPackages) {
        this.mFinishedTransport = finishedPackages;
        this.gvFinishedTransports.setAdapter(new FinishedTransportsAdapter(this.context, this.mFinishedTransport));
    }
}
