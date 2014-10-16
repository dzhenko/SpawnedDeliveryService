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
        registrationData.add(new BasicNameValuePair("FromTown", model.fromTown));
        registrationData.add(new BasicNameValuePair("ToTown", model.toTown));
        registrationData.add(new BasicNameValuePair("AvailableSpace", Integer.toString(model.availableSpace)));
        registrationData.add(new BasicNameValuePair("AvailableKilograms", Integer.toString(model.availableKilograms)));
        registrationData.add(new BasicNameValuePair("Arrival", model.arrival));
        registrationData.add(new BasicNameValuePair("Departure", model.departure));
        registrationData.add(new BasicNameValuePair("DriverName", model.driverName));
        registrationData.add(new BasicNameValuePair("DriverPhone", model.driverPhone));
        registrationData.add(new BasicNameValuePair("AdditionalContact", model.additionalContact));

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
