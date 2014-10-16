package com.example.spawneddeliveryservice.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.spawneddeliveryservice.models.NewTransportDataModel;
import com.example.spawneddeliveryservice.webData.UserData;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class CreateTransportTask extends AsyncTask<NewTransportDataModel, Void, String> {
    private Context mContext;

    public CreateTransportTask(Context context){
        this.mContext = context;
    }
    @Override
    protected String doInBackground(NewTransportDataModel... models) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(ApiConstants.TRANSPORTS_CREATE);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.addHeader("Authorization", "bearer " + UserData.getToken());

        NewTransportDataModel model = models[0];
        List<NameValuePair> registrationData = new ArrayList<NameValuePair>();
        registrationData.add(new BasicNameValuePair("FromTown", model.FromTown));
        registrationData.add(new BasicNameValuePair("ToTown", model.ToTown));
        registrationData.add(new BasicNameValuePair("AvailableSpace", Integer.toString(model.AvailableSpace)));
        registrationData.add(new BasicNameValuePair("AvailableKilograms", Integer.toString(model.AvailableKilograms)));
        registrationData.add(new BasicNameValuePair("Arrival", model.Arrival));
        registrationData.add(new BasicNameValuePair("Departure", model.Departure));
        registrationData.add(new BasicNameValuePair("DriverName", model.DriverName));
        registrationData.add(new BasicNameValuePair("DriverPhone", model.DriverPhone));

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(registrationData));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            return EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "1";
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        Toast.makeText(
                mContext,
                "Transport created",
                Toast.LENGTH_SHORT).show();
    }
}
