package com.example.spawneddeliveryservice;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.spawneddeliveryservice.appData.DAO;
import com.example.spawneddeliveryservice.eventListeners.SimpleGestureFilter;
import com.example.spawneddeliveryservice.homeFragments.HomeFragment;
import com.example.spawneddeliveryservice.homeFragments.PackagesFragment;
import com.example.spawneddeliveryservice.models.Stats;

public class HomeActivity extends Activity implements SimpleGestureFilter.SimpleGestureListener{
    private final Context context = this;
    private SimpleGestureFilter detector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (savedInstanceState == null) {
//            this.loadHome();
            this.loadPackages();
        }

        // Detect touched area
        detector = new SimpleGestureFilter(this, this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent me){
        // Call onTouchEvent of SimpleGestureFilter class
        this.detector.onTouchEvent(me);
        return super.dispatchTouchEvent(me);
    }

    @Override
    public void onSwipe(int direction) {
        String str = "";

        switch (direction) {

            case SimpleGestureFilter.SWIPE_RIGHT : str = "Swipe Right";
                break;
            case SimpleGestureFilter.SWIPE_LEFT :  str = "Swipe Left";
                break;
            case SimpleGestureFilter.SWIPE_DOWN :  str = "Swipe Down";
                break;
            case SimpleGestureFilter.SWIPE_UP :    str = "Swipe Up";
                break;

        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDoubleTap() {
        Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
    }

    protected void changeActivity(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .add(R.id.home_container, fragment).commit();
    }

    public void loadHome() {
        Stats stats =  DAO.getStats(this.context);
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("statsOverview", stats.toString());
        homeFragment.setArguments(bundle);
        this.changeActivity(homeFragment);
    }

    public void loadPackages() {
        this.changeActivity(new PackagesFragment());
    }
}
