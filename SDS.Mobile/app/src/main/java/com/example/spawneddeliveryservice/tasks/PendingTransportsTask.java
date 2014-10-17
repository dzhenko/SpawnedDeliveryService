package com.example.spawneddeliveryservice.tasks;

import android.os.AsyncTask;

import com.example.spawneddeliveryservice.homeFragments.PendingTransportsFragment;
import com.example.spawneddeliveryservice.models.PendingTransportDataModel;
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

public class PendingTransportsTask extends AsyncTask<String, Void, ArrayList<PendingTransportDataModel>> {
    private PendingTransportsFragment mContext;

    public PendingTransportsTask(PendingTransportsFragment context){
        this.mContext = context;
    }

    @Override
    protected ArrayList<PendingTransportDataModel> doInBackground(String... params) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(ApiConstants.TRANSPORTS_PENDING);
        httpGet.addHeader("Authorization", "bearer " + UserData.getToken());

        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            String response = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            JSONArray jObject = new JSONArray(response);

            ArrayList<PendingTransportDataModel> list = new ArrayList<PendingTransportDataModel>();

            for(int i=0;i<jObject.length();i++)
            {
                PendingTransportDataModel modelToAdd = PendingTransportDataModel.FromModel(jObject.getString(i));
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

        return new ArrayList<PendingTransportDataModel>();
    }

    @Override
    protected void onPostExecute(ArrayList<PendingTransportDataModel> result) {
        super.onPostExecute(result);

        this.mContext.updatePageInfo(result);
    }
}
