package com.example.spawneddeliveryservice;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.spawneddeliveryservice.appData.DAO;
import com.example.spawneddeliveryservice.eventListeners.SimpleGestureFilter;
import com.example.spawneddeliveryservice.homeFragments.AddPackageFragment;
import com.example.spawneddeliveryservice.homeFragments.HomeFragment;
import com.example.spawneddeliveryservice.models.Stats;
import com.example.spawneddeliveryservice.tasks.ApiConstants;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.List;

public class HomeActivity extends Activity implements SimpleGestureFilter.SimpleGestureListener, View.OnClickListener {
    public static final int REQUEST_CAMERA = 1;
    public static final int SELECT_FILE = 2;
    public static final int PICK_CONTACT = 3;
    private final Context context = this;
    private List<Class<?>> mCurrentPageFragments;
    private Integer mCurrentFragmentIndex = 0;
    private SimpleGestureFilter detector;
    private Button mbtnHomeMenuPackages, mbtnHomeMenuTransports, mbtnHomeMenuProfile;

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
        this.mbtnHomeMenuTransports = (Button) this.findViewById(R.id.btnHomeMenuTransports);
        this.mbtnHomeMenuTransports.setOnClickListener(this);
        this.mbtnHomeMenuProfile = (Button) this.findViewById(R.id.btnHomeMenuProfile);
        this.mbtnHomeMenuProfile.setOnClickListener(this);

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
//                str = "Swipe Right";
                break;
            case SimpleGestureFilter.SWIPE_LEFT:
                if (this.mCurrentPageFragments != null) {
                    this.previousFragment();
                }
//                str = "Swipe Left";
                break;
//            case SimpleGestureFilter.SWIPE_DOWN :  str = "Swipe Down";
//                break;
//            case SimpleGestureFilter.SWIPE_UP :    str = "Swipe Up";
//                break;

        }
//        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
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

    public void loadTransports() {
        this.mCurrentPageFragments = ApiConstants.TRANSPORT_FRAGMENTS;
        this.mCurrentFragmentIndex = 0;
        this.changeActivity(getFragment(this.mCurrentFragmentIndex));
    }

    public void loadProfiles() {
        this.mCurrentPageFragments = ApiConstants.PROFILE_FRAGMENTS;
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
        } else if (v.getId() == R.id.btnHomeMenuTransports) {
            this.loadTransports();
        } else if (v.getId() == R.id.btnHomeMenuProfile) {
            this.loadProfiles();
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

    public void addContact() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        startActivityForResult(intent, HomeActivity.PICK_CONTACT);
    }

    public void takePicture() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Add Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
                        startActivityForResult(takePictureIntent, HomeActivity.REQUEST_CAMERA);
                    }
                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            HomeActivity.SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CAMERA) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] bytes = baos.toByteArray();
            AddPackageFragment.base64Image = Base64.encodeToString(bytes, Base64.NO_WRAP);
        } else if (resultCode == RESULT_OK && requestCode == SELECT_FILE) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] bytes = baos.toByteArray();
                AddPackageFragment.base64Image = Base64.encodeToString(bytes, Base64.NO_WRAP);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (resultCode == RESULT_OK && requestCode == PICK_CONTACT) {
            Uri contactData = data.getData();
            Cursor c =  managedQuery(contactData, null, null, null, null);
            if (c.moveToFirst()) {
                String id =c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

                String hasPhone =c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                if (hasPhone.equalsIgnoreCase("1")) {
                    Cursor phones = HomeActivity.this.getContentResolver().query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id,
                            null, null);
                    phones.moveToFirst();
                    AddPackageFragment.phoneNumber = phones.getString(phones.getColumnIndex("data1"));
                }
            }
        }
    }

    private String base64FromFile(File file) {
        long length = file.length();
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(file));
            byte[] fileData = new byte[(int) length];
            //Read bytes from file
            bis.read(fileData);
            return Base64.encodeToString(fileData, Base64.DEFAULT);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null)
                try {
                    bis.close();
                } catch (IOException e) {
                }
        }

        return "";
    }
}
