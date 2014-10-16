package com.example.spawneddeliveryservice.tasks;

import android.os.AsyncTask;

import com.example.spawneddeliveryservice.models.ActiveTransportDataModel;

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
import java.util.ArrayList;

public class DbPopulationTask extends AsyncTask<String, Void, ArrayList<ActiveTransportDataModel>> {
    @Override
    protected ArrayList<ActiveTransportDataModel> doInBackground(String... params) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet("http://spawndeliveryservice.apphb.com" + "/api/stats/" + "active");

        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            String body = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            JSONArray jObject = new JSONArray(body);

            ArrayList<ActiveTransportDataModel> list = new ArrayList<ActiveTransportDataModel>();

            for(int i=0;i<jObject.length();i++)
            {
                ActiveTransportDataModel modelToAdd = ActiveTransportDataModel.FromModel(jObject.getString(i));
                list.add(modelToAdd);
            }

            return list;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}