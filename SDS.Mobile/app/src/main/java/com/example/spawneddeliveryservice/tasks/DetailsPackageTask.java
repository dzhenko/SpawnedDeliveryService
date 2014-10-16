package com.example.spawneddeliveryservice.tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.example.spawneddeliveryservice.models.DetailsPackageDataModel;
import com.example.spawneddeliveryservice.webData.UserData;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class DetailsPackageTask extends AsyncTask<String, Void, DetailsPackageDataModel> {
    private Context mContext;

    public DetailsPackageTask(Context context){
        this.mContext = context;
    }

    @Override                                   // pass id of the package
    protected DetailsPackageDataModel doInBackground(String... params) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(ApiConstants.PACKAGES_DETAILS + "/" + params[0]);
        httpGet.addHeader("Authorization", "bearer " + UserData.getToken());

        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            String response = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            return DetailsPackageDataModel.FromModel(response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new DetailsPackageDataModel();
    }

    @Override
    protected void onPostExecute(DetailsPackageDataModel result) {
        super.onPostExecute(result);


    }
}
