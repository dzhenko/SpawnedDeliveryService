package com.example.spawneddeliveryservice.tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.example.spawneddeliveryservice.appData.TownsDataSource;

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

public class DbTownsPopulationTask extends AsyncTask<String, Void, Void> {

    private Context mContext;

    public DbTownsPopulationTask(Context context){
        this.mContext = context;
    }

    @Override
    protected Void doInBackground(String... params) {
        TownsDataSource mTownsDb = new TownsDataSource(mContext);
        mTownsDb.open();
        if (mTownsDb.getAllTowns().size() > 0) {
            return null;
        }

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(ApiConstants.TOWNS);

        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            String response = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            JSONArray jArray = new JSONArray(response);

            for(int i=0;i<jArray.length();i++)
            {
                String townName = jArray.getString(i);
                mTownsDb.createTown(jArray.getString(i));
            }
            mTownsDb.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            mTownsDb.close();
        }

        mTownsDb.close();
        return null;
    }
}
