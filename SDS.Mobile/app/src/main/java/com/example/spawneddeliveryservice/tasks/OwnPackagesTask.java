package com.example.spawneddeliveryservice.tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.example.spawneddeliveryservice.models.OwnPackageDataModel;
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

public class OwnPackagesTask extends AsyncTask<String, Void, ArrayList<OwnPackageDataModel>> {
    private Context mContext;

    public OwnPackagesTask(Context context){
        this.mContext = context;
    }

    @Override
    protected ArrayList<OwnPackageDataModel> doInBackground(String... params) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(ApiConstants.PACKAGES_OWN);
        httpGet.addHeader("Authorization", "bearer " + UserData.getToken());

        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            String response = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            JSONArray jObject = new JSONArray(response);

            ArrayList<OwnPackageDataModel> list = new ArrayList<OwnPackageDataModel>();

            for(int i=0;i<jObject.length();i++)
            {
                OwnPackageDataModel modelToAdd = OwnPackageDataModel.FromModel(jObject.getString(i));
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

        return new ArrayList<OwnPackageDataModel>();
    }

    @Override
    protected void onPostExecute(ArrayList<OwnPackageDataModel> result) {
        super.onPostExecute(result);


    }
}

