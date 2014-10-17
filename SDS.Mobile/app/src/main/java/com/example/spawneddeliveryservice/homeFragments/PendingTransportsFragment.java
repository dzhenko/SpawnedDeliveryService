package com.example.spawneddeliveryservice.homeFragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.spawneddeliveryservice.R;
import com.example.spawneddeliveryservice.adapters.PendingTransportsAdapter;
import com.example.spawneddeliveryservice.models.PendingTransportDataModel;
import com.example.spawneddeliveryservice.tasks.PendingTransportsTask;

import java.util.ArrayList;

public class PendingTransportsFragment extends Fragment {
    private ArrayList<PendingTransportDataModel> mFinishedTransport;
    private Context context;
    private GridView gvPendingTransports;

    public PendingTransportsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_transports_pending_transports, container, false);

        this.context = container.getContext();
        this.reloadPageInfo();
        this.gvPendingTransports = (GridView) rootView.findViewById(R.id.gvHomeTransportsPendingTransports);

        return rootView;
    }

    private void reloadPageInfo() {
        PendingTransportsTask transportsTask = new PendingTransportsTask(this);
        transportsTask.execute();
    }

    public void updatePageInfo(ArrayList<PendingTransportDataModel> finishedPackages) {
        this.mFinishedTransport = finishedPackages;
        this.gvPendingTransports.setAdapter(new PendingTransportsAdapter(this.context, this.mFinishedTransport));
    }
}
