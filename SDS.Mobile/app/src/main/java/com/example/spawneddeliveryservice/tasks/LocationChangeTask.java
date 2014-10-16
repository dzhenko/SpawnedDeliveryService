package com.example.spawneddeliveryservice.tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.example.spawneddeliveryservice.webData.UserData;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class LocationChangeTask extends AsyncTask<String, Void, Void> {
    private Context context;

    public LocationChangeTask(Context context){
        this.context = context;
    }

    @Override
    protected Void doInBackground(String... params) {
        String latitude = params[0].toString();
        String longitude = params[1].toString();
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://spawndeliveryservice.apphb.com" + "/api/users/" + "Coordinates");
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.addHeader("Authorization", "bearer " + UserData.getToken());
        List<NameValuePair> registrationData = new ArrayList<NameValuePair>();
        registrationData.add(new BasicNameValuePair("Longitude", longitude));
        registrationData.add(new BasicNameValuePair("Latitude", latitude));

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(registrationData));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            InputStream inputStream = httpResponse.getEntity().getContent();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String chunk = null;

            while ((chunk = bufferedReader.readLine()) != null) {
                stringBuilder.append(chunk);
            }

//            Toast.makeText(context, latitude + " " + longitude, Toast.LENGTH_LONG);
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
