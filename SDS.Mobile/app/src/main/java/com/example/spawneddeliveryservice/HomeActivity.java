package com.example.spawneddeliveryservice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity implements View.OnClickListener {
    Button btnActiveTransports, btnActivePackages, btnDeliveredPackages, btnFinishedTransports, btnLocationTracker;
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);

        //Buttons
        this.btnActivePackages = (Button)findViewById(R.id.btnHomeActivePackages);
        this.btnActiveTransports= (Button)findViewById(R.id.btnHomeActiveTransports);
        this.btnDeliveredPackages = (Button)findViewById(R.id.btnHomeDeliveredPackages);
        this.btnFinishedTransports = (Button)findViewById(R.id.btnHomeFinishedTransports);
        this.btnLocationTracker = (Button)findViewById(R.id.btnHomeLocationTracker);

        this.attachEvents();
    }

    private void attachEvents() {
        this.btnLocationTracker.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnHomeLocationTracker){
            Intent intent = new Intent(this, LocatorActivity.class);
            startActivity(intent);
        }
    }
}
