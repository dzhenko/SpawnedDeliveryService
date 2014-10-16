package com.example.spawneddeliveryservice.tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.example.spawneddeliveryservice.appData.StatsDataSource;
import com.example.spawneddeliveryservice.models.Stats;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

public class DbStatsPopulationTask extends AsyncTask<String, Void, Stats> {

    private Context mContext;

    public DbStatsPopulationTask(Context context){
        this.mContext = context;
    }

    @Override
    protected Stats doInBackground(String... params) {
        StatsDataSource mStatsDb = new StatsDataSource(mContext);
        mStatsDb.open();
        List<Stats> lastStatsList = mStatsDb.getAllStats();
        Stats lastStats = null;
        // no need for update - less than day passed
        if (!lastStatsList.isEmpty()) {
            lastStats = lastStatsList.get(0);
            if (lastStats.getUpdated().getTime() - (new Date()).getTime() < 1000 * 60 * 60 * 24) {
                return null;
            }
        }

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(ApiConstants.STATS);

        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            String response = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            JSONObject jObject = new JSONObject(response);
            if (lastStats != null) {
                mStatsDb.deleteStats(lastStats);
            }
            mStatsDb.createStats(jObject.getInt("UsersCount"), jObject.getInt("TransportsCount"), jObject.getInt("PackagesCount"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            mStatsDb.close();
        }

        mStatsDb.close();
        return null;
    }
}