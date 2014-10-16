package com.example.spawneddeliveryservice.tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.example.spawneddeliveryservice.models.FinishedTransportDataModel;
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

public class FinishedTransportsTask extends AsyncTask<String, Void, ArrayList<FinishedTransportDataModel>> {
    private Context mContext;

    public FinishedTransportsTask(Context context){
        this.mContext = context;
    }

    @Override
    protected ArrayList<FinishedTransportDataModel> doInBackground(String... params) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(ApiConstants.TRANSPORTS_FINISHED);
        httpGet.addHeader("Authorization", "bearer " + UserData.getToken());

        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            String response = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            JSONArray jObject = new JSONArray(response);

            ArrayList<FinishedTransportDataModel> list = new ArrayList<FinishedTransportDataModel>();

            for(int i=0;i<jObject.length();i++)
            {
                FinishedTransportDataModel modelToAdd = FinishedTransportDataModel.FromModel(jObject.getString(i));
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

        return new ArrayList<FinishedTransportDataModel>();
    }

    @Override
    protected void onPostExecute(ArrayList<FinishedTransportDataModel> result) {
        super.onPostExecute(result);


    }
}

