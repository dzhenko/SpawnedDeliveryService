package com.example.spawneddeliveryservice.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.spawneddeliveryservice.R;
import com.example.spawneddeliveryservice.models.FinishedPackageDataModel;

import java.util.ArrayList;

public class FinishedPackagesAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<FinishedPackageDataModel> mFinishedPackages;

    public FinishedPackagesAdapter(Context context, ArrayList<FinishedPackageDataModel> finishedPackages) {
        this.mContext = context;
        this.mFinishedPackages = finishedPackages;
    }

    @Override
    public int getCount() {
        return mFinishedPackages.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mFinishedPackages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_finished_packages, parent, false);

        FinishedPackageDataModel finishedPackage = (FinishedPackageDataModel) this.getItem(position);
//        TextView textView = new TextView(mContext);
        TextView tvPackagesFrom = (TextView) rootView.findViewById(R.id.tvPackagesFrom);
        TextView tvPackagesTo = (TextView) rootView.findViewById(R.id.tvPackagesTo);
        TextView tvPackagesArrival = (TextView) rootView.findViewById(R.id.tvPackagesArrival);
        TextView tvPackagesDriverName = (TextView) rootView.findViewById(R.id.tvPackagesDriverName);
        TextView tvPackagesDriverPhone = (TextView) rootView.findViewById(R.id.tvPackagesDriverPhone);

        tvPackagesFrom.setText(finishedPackage.fromTown);
        tvPackagesTo.setText(finishedPackage.toTown);
        tvPackagesArrival.setText(finishedPackage.arrival.toString());
        tvPackagesDriverName.setText(finishedPackage.driverName);
        tvPackagesDriverPhone.setText(finishedPackage.driverPhone);

        return rootView;
    }
}
