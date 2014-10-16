package com.example.spawneddeliveryservice.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
        FinishedPackageDataModel finishedPackage = (FinishedPackageDataModel) this.getItem(position);
        TextView textView = new TextView(mContext);
        textView.setText(finishedPackage.driverName);
//        textView.setLayoutParams(new GridView.LayoutParams(70, 70));
        return textView;
    }
}
