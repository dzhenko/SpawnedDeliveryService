package com.example.spawneddeliveryservice.tasks;

import android.os.AsyncTask;

import com.example.spawneddeliveryservice.webData.UserData;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class LocationChangeTask extends AsyncTask<String, Void, Void> {
    @Override
    protected Void doInBackground(String... params) {
        String latitude = params[0];
        String longitude = params[1];
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(ApiConstants.COORDINATES);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.addHeader("Authorization", "bearer " + UserData.getToken());
        List<NameValuePair> registrationData = new ArrayList<NameValuePair>();
        registrationData.add(new BasicNameValuePair("Longitude", longitude));
        registrationData.add(new BasicNameValuePair("Latitude", latitude));

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(registrationData));
            httpClient.execute(httpPost);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
