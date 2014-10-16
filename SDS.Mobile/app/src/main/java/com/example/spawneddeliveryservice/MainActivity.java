package com.example.spawneddeliveryservice;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;

import com.example.spawneddeliveryservice.mainFragments.LoginFragment;
import com.example.spawneddeliveryservice.mainFragments.RegisterFragment;
import com.example.spawneddeliveryservice.tasks.DbStatsPopulationTask;
import com.example.spawneddeliveryservice.tasks.DbTownsPopulationTask;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity extends Activity {
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // picture magic test
        // selectImage();

        (new DbTownsPopulationTask(this.context)).execute("");
        (new DbStatsPopulationTask(this.context)).execute("");

        if (savedInstanceState == null) {
            this.loadLogin();
        }

        //this.redirectHome();
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
        changeActivity(new LoginFragment());
    }

    public void loadRegister(){
        changeActivity(new RegisterFragment());
    }

    public void redirectHome(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    // picture magic starts
    private int REQUEST_CAMERA = 1;
    private int SELECT_FILE = 2;
    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takePictureIntent, REQUEST_CAMERA);
                    }
                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    // picture magic final result !
    private String base64String = "";
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CAMERA) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] bytes = baos.toByteArray();
            base64String= Base64.encodeToString(bytes, Base64.NO_WRAP);
        }
        else if (resultCode == RESULT_OK && requestCode == SELECT_FILE) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] bytes = baos.toByteArray();
                base64String= Base64.encodeToString(bytes, Base64.NO_WRAP);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String base64FromFile(File file){
        long length = file.length();
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(file));
            byte[] fileData = new byte[(int)length];
            //Read bytes from file
            bis.read(fileData);
            return Base64.encodeToString(fileData, Base64.DEFAULT);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bis != null)
                try { bis.close(); }
                catch(IOException e) {}
        }

        return "";
    }
    // picture magic ends
}
