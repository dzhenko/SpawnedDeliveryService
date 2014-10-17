package com.example.spawneddeliveryservice;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.spawneddeliveryservice.appData.DAO;
import com.example.spawneddeliveryservice.eventListeners.SimpleGestureFilter;
import com.example.spawneddeliveryservice.homeFragments.HomeFragment;
import com.example.spawneddeliveryservice.models.Stats;
import com.example.spawneddeliveryservice.tasks.ApiConstants;

import java.lang.reflect.Constructor;
import java.util.List;

public class HomeActivity extends Activity implements SimpleGestureFilter.SimpleGestureListener, View.OnClickListener {
    private final Context context = this;
    private List<Class<?>> mCurrentPageFragments;
    private Integer mCurrentFragmentIndex = 0;
    private SimpleGestureFilter detector;
    private Button mbtnHomeMenuPackages;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (savedInstanceState == null) {
            this.loadHome();
//            this.loadPackages();
        }

        this.mbtnHomeMenuPackages = (Button) this.findViewById(R.id.btnHomeMenuPackages);
        this.mbtnHomeMenuPackages.setOnClickListener(this);

        // Detect touched area
        detector = new SimpleGestureFilter(this, this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent me) {
        // Call onTouchEvent of SimpleGestureFilter class
        this.detector.onTouchEvent(me);
        return super.dispatchTouchEvent(me);
    }

    @Override
    public void onSwipe(int direction) {
        String str = "";

        switch (direction) {

            case SimpleGestureFilter.SWIPE_RIGHT:
                if (this.mCurrentPageFragments != null) {
                    this.nextFragment();
                }
                str = "Swipe Right";
                break;
            case SimpleGestureFilter.SWIPE_LEFT:
                if (this.mCurrentPageFragments != null) {
                    this.previousFragment();
                }
                str = "Swipe Left";
                break;
//            case SimpleGestureFilter.SWIPE_DOWN :  str = "Swipe Down";
//                break;
//            case SimpleGestureFilter.SWIPE_UP :    str = "Swipe Up";
//                break;

        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDoubleTap() {
        Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
    }

    protected void changeActivity(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .replace(R.id.home_container, fragment).addToBackStack("tag").commit();
    }

    public void loadHome() {
        Stats stats = DAO.getStats(this.context);
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("statsOverview", stats.toString());
        homeFragment.setArguments(bundle);
        this.changeActivity(homeFragment);
    }

    public void loadPackages() {
        this.mCurrentPageFragments = ApiConstants.PACKAGE_FRAGMENTS;
        this.mCurrentFragmentIndex = 0;
        this.changeActivity(getFragment(this.mCurrentFragmentIndex));
    }

    public void nextFragment() {
        this.mCurrentFragmentIndex++;
        if (this.mCurrentFragmentIndex >= this.mCurrentPageFragments.size()) {
            this.mCurrentFragmentIndex = 0;
        }

        Fragment fragment = getFragment(this.mCurrentFragmentIndex);
        this.changeActivity(fragment);
    }

    public void previousFragment() {
        this.mCurrentFragmentIndex--;
        if (this.mCurrentFragmentIndex < 0) {
            this.mCurrentFragmentIndex = this.mCurrentPageFragments.size() - 1;
        }

        Fragment fragment = getFragment(this.mCurrentFragmentIndex);
        this.changeActivity(fragment);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnHomeMenuPackages) {
            this.loadPackages();
        }
    }

    private Fragment getFragment(Integer index) {
        Fragment fragment = null;

        try {
            Constructor ctor = (this.mCurrentPageFragments.get(this.mCurrentFragmentIndex)).getDeclaredConstructor();
            fragment = (Fragment) ctor.newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return fragment;
    }
}
