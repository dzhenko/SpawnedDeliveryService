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

public class ActiveTransportTask extends AsyncTask<String, Void, ArrayList<ActiveTransportDataModel>> {
    @Override
    protected ArrayList<ActiveTransportDataModel> doInBackground(String... params) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet("http://spawndeliveryservice.apphb.com" + "/api/transports/" + "active");
        String tempAuth = "bearer YKkcT9q6y7tt5LyvVHDqnykDJWtIPWjUqLDI58kCeJm9LTCJf0HH-6E9scbgA2aPvkAaBHRXSzq6bjOx_erO2CelFP5CYsB4rLeKU_zkdl-g_evhc6NNvpiz3oIXxmsr-prkLLp3t5UXMO8ISC_-CidmJNPKdZ3u8x05TriatJjH9MBKpblJx6DxcJP4tqkNCwCRm1h1Hh1uGSoVfcGsEPh7ITiyEXAZ6OzhE2pTbOLesG_s5-Ufkp7hXPsykT32lSSByeDVurv_4TZrFtGksUzCNfrc2vzf3r-oah9ZBdADznqDkYfXgaDL-4kApq4z7C0H3cKOlpznC-m3X1RPm-YwzP1jX3G2aZOsRIklVWFZxypJ4y8yw_m9XP-ABJ8jr8Y1Xhfc_xLm8Iz3Am13DHNb81-sZxZ28JpJT3L1eLWIWYh3fD2L3aBAFA5Sb6TktorMMeOtIERI7HausUfFXLaSXC304EoBt6Iok4ETA64";
        httpGet.addHeader("Authorization", tempAuth);
        //httpGet.addHeader("Authorization", "bearer " + UserData.getToken());

        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            //String encoding = httpResponse.getEntity().getContentEncoding().getValue();

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
