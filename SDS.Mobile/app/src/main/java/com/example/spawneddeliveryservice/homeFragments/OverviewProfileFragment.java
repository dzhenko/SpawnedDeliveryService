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
import android.widget.Button;
import android.widget.CompoundButton;

import com.example.spawneddeliveryservice.CompassActivity;
import com.example.spawneddeliveryservice.R;
import com.example.spawneddeliveryservice.services.LocationTrackerService;
import com.example.spawneddeliveryservice.services.MusicService;

public class OverviewProfileFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    boolean mBound = false;
    MusicService mService;
    Integer[] songs = {R.raw.panther, R.raw.alpachino, R.raw.jingle};
    private ServiceConnection mPlayerConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get
            // LocalService instance
            MusicService.LocalBinder binder = (MusicService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
            mService.loadSong(songs[0]);
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
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
    private Button btnShowCompass;

    public OverviewProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        Intent intent = new Intent(this.context, LocationTrackerService.class);
        this.context.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

        Intent playerIntent = new Intent(this.context, MusicService.class);
        this.context.bindService(playerIntent, mPlayerConnection, Context.BIND_AUTO_CREATE);

        this.btnShowCompass = (Button) rootView.findViewById(R.id.btnShowCompass);
        this.btnShowCompass.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnShowCompass) {
            Intent intent = new Intent(this.context, CompassActivity.class);
            startActivity(intent);
        }
//        else if (v.getId() == R.id.btnTransportsNewTransportTakeAPicture) {
//        }
    }
}
