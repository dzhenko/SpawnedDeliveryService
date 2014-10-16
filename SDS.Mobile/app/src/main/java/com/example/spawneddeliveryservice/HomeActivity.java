package com.example.spawneddeliveryservice;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import com.example.spawneddeliveryservice.appData.DAO;
import com.example.spawneddeliveryservice.homeFragments.HomeFragment;
import com.example.spawneddeliveryservice.models.Stats;

public class HomeActivity extends Activity {
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);

        if (savedInstanceState == null) {
            this.loadHome();
        }
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
}
