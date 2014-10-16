package com.example.spawneddeliveryservice.tasks;

import android.os.AsyncTask;

import com.example.spawneddeliveryservice.models.FoundTransportDataModel;
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
import java.util.ArrayList;

public class FoundTransportsTask extends AsyncTask<String, Void, ArrayList<FoundTransportDataModel>> {
    @Override                       //page = 0, sort = price, find = towns, value = Sofia-Burgas
    protected ArrayList<FoundTransportDataModel> doInBackground(String... params) {
        HttpClient httpClient = new DefaultHttpClient();

        String url = ApiConstants.TRANSPORTS_FIND + "?";
        url+="page="+params[0];
        url+="sort="+params[1];
        url+="find="+params[2];
        url+="value="+params[3];

        HttpGet httpGet = new HttpGet(url);

        httpGet.addHeader("Authorization", "bearer " + UserData.getToken());

        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            String response = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            JSONArray jObject = new JSONArray(response);

            ArrayList<FoundTransportDataModel> list = new ArrayList<FoundTransportDataModel>();

            for(int i=0;i<jObject.length();i++)
            {
                FoundTransportDataModel modelToAdd = FoundTransportDataModel.FromModel(jObject.getString(i));
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

        return new ArrayList<FoundTransportDataModel>();
    }
}

