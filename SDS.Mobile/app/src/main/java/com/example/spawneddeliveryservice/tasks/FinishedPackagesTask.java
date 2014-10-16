package com.example.spawneddeliveryservice.tasks;

import android.os.AsyncTask;

import com.example.spawneddeliveryservice.models.FinishedPackageDataModel;
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

public class FinishedPackagesTask extends AsyncTask<String, Void, ArrayList<FinishedPackageDataModel>> {
    @Override
    protected ArrayList<FinishedPackageDataModel> doInBackground(String... params) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(ApiConstants.PACKAGES_FINISHED);
        httpGet.addHeader("Authorization", "bearer " + UserData.getToken());

        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            String response = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            JSONArray jObject = new JSONArray(response);

            ArrayList<FinishedPackageDataModel> list = new ArrayList<FinishedPackageDataModel>();

            for(int i=0;i<jObject.length();i++)
            {
                FinishedPackageDataModel modelToAdd = FinishedPackageDataModel.FromModel(jObject.getString(i));
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

        return new ArrayList<FinishedPackageDataModel>();
    }
}
