package com.example.spawneddeliveryservice.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.spawneddeliveryservice.models.ActiveTransportDataModel;
import com.example.spawneddeliveryservice.webData.UserData;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ActiveTransportTask extends AsyncTask<String, Void, ActiveTransportDataModel[]> {
    @Override
    protected ActiveTransportDataModel[] doInBackground(String... params) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet("http://spawndeliveryservice.apphb.com" + "/api/transports/" + "active");
        httpGet.addHeader("Authorization", "bearer " + UserData.getToken());

        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            String encoding = httpResponse.getEntity().getContentEncoding().getValue();

            String body = EntityUtils.toString(httpResponse.getEntity(), encoding == null ? "UTF-8" : encoding);
            JSONArray jObject = new JSONArray(body);



            Log.d("ASD", body);

            return new ActiveTransportDataModel[10];
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ActiveTransportDataModel[10];
    }
}
