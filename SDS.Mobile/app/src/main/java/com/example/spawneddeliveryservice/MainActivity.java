package com.example.spawneddeliveryservice;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.spawneddeliveryservice.tasks.DbStatsPopulationTask;
import com.example.spawneddeliveryservice.tasks.DbTownsPopulationTask;

public class MainActivity extends Activity {
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        (new DbTownsPopulationTask(this.context)).execute("");
        (new DbStatsPopulationTask(this.context)).execute("");

        if (savedInstanceState == null) {
            this.loadLogin();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void changeActivity(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .add(R.id.container, fragment).commit();
    }

    public void loadLogin(){
        changeActivity(new LoginActivity());
    }

    public void loadRegister(){
        changeActivity(new RegisterActivity());
    }

    public void redirectHome(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
