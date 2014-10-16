package com.example.spawneddeliveryservice.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.spawneddeliveryservice.MainActivity;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class RegisterTask extends AsyncTask<String, Void, String> {
    private Context mContext;

    public RegisterTask(Context context){
        this.mContext = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String email = params[0];
        String password = params[1];
        String confirmPassword = params[2];
        String phoneNumber = params[3];

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(ApiConstants.REGISTER);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");

        List<NameValuePair> registrationData = new ArrayList<NameValuePair>();
        registrationData.add(new BasicNameValuePair("email", email));
        registrationData.add(new BasicNameValuePair("password", password));
        registrationData.add(new BasicNameValuePair("confirmpassword", confirmPassword));
        registrationData.add(new BasicNameValuePair("phoneNumber", phoneNumber));

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

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if (result.equals("")) {
            Toast.makeText(
                    mContext,
                    "Registration successful",
                    Toast.LENGTH_SHORT).show();
            mContext.startActivity(new Intent(mContext, MainActivity.class));
        } else {
            try {
                JSONObject jObject = new JSONObject(result);
                String errorsAsString = jObject.getString("Message");
                Toast.makeText(
                        mContext,
                        errorsAsString,
                        Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                Toast.makeText(
                        mContext,
                        "Incorrect registration data",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
