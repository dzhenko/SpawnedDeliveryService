package com.example.spawneddeliveryservice.tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.example.spawneddeliveryservice.models.CoordinatesDataModel;
import com.example.spawneddeliveryservice.webData.UserData;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class CoordinatesTask extends AsyncTask<String, Void, CoordinatesDataModel> {
    private Context mContext;

    public CoordinatesTask(Context context){
        this.mContext = context;
    }
    @Override                                   // pass id of the package
    protected CoordinatesDataModel doInBackground(String... params) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(ApiConstants.PACKAGES_COORDINATES + "/" + params[0]);
        httpGet.addHeader("Authorization", "bearer " + UserData.getToken());

        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            String response = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            return CoordinatesDataModel.FromModel(response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new CoordinatesDataModel();
    }

    @Override
    protected void onPostExecute(CoordinatesDataModel result) {
        super.onPostExecute(result);


    }
}