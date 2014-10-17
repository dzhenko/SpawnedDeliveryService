package com.example.spawneddeliveryservice.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.spawneddeliveryservice.R;
import com.example.spawneddeliveryservice.models.FinishedTransportDataModel;

import java.util.ArrayList;

public class FinishedTransportsAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<FinishedTransportDataModel> mFinishedTransports;

    public FinishedTransportsAdapter(Context context, ArrayList<FinishedTransportDataModel> finishedPackages) {
        this.mContext = context;
        this.mFinishedTransports = finishedPackages;
    }

    @Override
    public int getCount() {
        return mFinishedTransports.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mFinishedTransports.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_finished_transports, parent, false);

        FinishedTransportDataModel finishedPackage = (FinishedTransportDataModel) this.getItem(position);
//        TextView textView = new TextView(mContext);
        TextView tvTransportsFrom = (TextView) rootView.findViewById(R.id.tvTransportsFinishedFrom);
        TextView tvTransportsTo = (TextView) rootView.findViewById(R.id.tvTransportsFinishedTo);
        TextView tvTransportsArrival = (TextView) rootView.findViewById(R.id.tvTransportsFinishedArrival);
        TextView tvTransportsDriverName = (TextView) rootView.findViewById(R.id.tvTransportsFinishedDriverName);
        TextView tvTransportsDriverPhone = (TextView) rootView.findViewById(R.id.tvTransportsFinishedDriverPhone);

        tvTransportsFrom.setText(finishedPackage.fromTown);
        tvTransportsTo.setText(finishedPackage.toTown);
        tvTransportsArrival.setText(finishedPackage.arrival.toString());
        tvTransportsDriverName.setText(finishedPackage.driverName);
        tvTransportsDriverPhone.setText(finishedPackage.driverPhone);

        return rootView;
    }
}
