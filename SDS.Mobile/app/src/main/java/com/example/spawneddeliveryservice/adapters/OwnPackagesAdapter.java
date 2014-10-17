package com.example.spawneddeliveryservice.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.spawneddeliveryservice.R;
import com.example.spawneddeliveryservice.models.OwnPackageDataModel;

import java.util.ArrayList;

public class OwnPackagesAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<OwnPackageDataModel> mFinishedPackages;

    public OwnPackagesAdapter(Context context, ArrayList<OwnPackageDataModel> finishedPackages) {
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

        OwnPackageDataModel finishedPackage = (OwnPackageDataModel) this.getItem(position);
        TextView tvPackagesFrom = (TextView) rootView.findViewById(R.id.tvOwnPackagesFrom);
        TextView tvPackagesTo = (TextView) rootView.findViewById(R.id.tvOwnPackagesTo);
        TextView tvPackagesDeadline = (TextView) rootView.findViewById(R.id.tvOwnPackagesDeadline);

        tvPackagesFrom.setText(finishedPackage.fromTown);
        tvPackagesTo.setText(finishedPackage.toTown);
        tvPackagesDeadline.setText(finishedPackage.deadline.toString());

        return rootView;
    }
}
