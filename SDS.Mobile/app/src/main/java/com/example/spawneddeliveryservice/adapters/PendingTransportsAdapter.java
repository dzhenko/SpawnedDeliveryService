package com.example.spawneddeliveryservice.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.spawneddeliveryservice.R;
import com.example.spawneddeliveryservice.models.PendingTransportDataModel;

import java.util.ArrayList;

public class PendingTransportsAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<PendingTransportDataModel> mPendingTransports;

    public PendingTransportsAdapter(Context context, ArrayList<PendingTransportDataModel> pendingTransports) {
        this.mContext = context;
        this.mPendingTransports = pendingTransports;
    }

    @Override
    public int getCount() {
        return mPendingTransports.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mPendingTransports.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_pending_transports, parent, false);

        PendingTransportDataModel pendingTransport = (PendingTransportDataModel) this.getItem(position);
        TextView tvTransportFrom = (TextView) rootView.findViewById(R.id.tvPendingTransportsFrom);
        TextView tvTransportTo = (TextView) rootView.findViewById(R.id.tvPendingTransportsTo);
        TextView tvTransportDeadline = (TextView) rootView.findViewById(R.id.tvPendingTransportsDeadline);

        tvTransportFrom.setText(pendingTransport.fromTown);
        tvTransportTo.setText(pendingTransport.toTown);
        tvTransportDeadline.setText(pendingTransport.deadline.toString());

        return rootView;
    }
}
