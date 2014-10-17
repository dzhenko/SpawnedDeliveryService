package com.example.spawneddeliveryservice.homeFragments;

import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.spawneddeliveryservice.R;
import com.example.spawneddeliveryservice.services.LocationTrackerService;

public class OverviewProfileFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private Context context;
    private LocationTrackerService locationService;
    private Boolean isConnected = false;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocationTrackerService.LocalBinder binder = (LocationTrackerService.LocalBinder) service;
            locationService = binder.getService();
            isConnected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConnected = false;
        }
    };

    public OverviewProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this.context, LocationTrackerService.class);
        this.context.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.swLocatorToggleTracker && isConnected) {
            if (isChecked) {
                locationService.enableUpdates();
            } else {
                locationService.disableUpdates();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile_overview, container, false);

        this.context = container.getContext();

        return rootView;
    }

    @Override
    public void onClick(View v) {
//        if (v.getId() == R.id.btnPackagesAddPackage) {
//        } else if (v.getId() == R.id.btnTransportsNewTransportAdditionalContacts) {
//        } else if (v.getId() == R.id.btnTransportsNewTransportTakeAPicture) {
//        }
    }
}