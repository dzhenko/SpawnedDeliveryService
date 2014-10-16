package com.example.spawneddeliveryservice.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.spawneddeliveryservice.HomeActivity;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class LoginTask extends AsyncTask<String, Void, String> {

    private Context mContext;

    public LoginTask(Context context){
        this.mContext = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String email = params[0];
        String password = params[1];
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(ApiConstants.LOGIN);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        List<NameValuePair> registrationData = new ArrayList<NameValuePair>();
        registrationData.add(new BasicNameValuePair("Username", email));
        registrationData.add(new BasicNameValuePair("Password", password));
        registrationData.add(new BasicNameValuePair("grant_type", "password"));

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

        try {
            JSONObject jObject = new JSONObject(result);
            try {
                String token = jObject.getString("access_token");
                String userName = jObject.getString("userName");
                if (token == null || userName == null || token == "" || userName == "") {
                    Toast.makeText(
                            mContext,
                            "Login failed",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                UserData.setToken(token);
                UserData.setUsername(userName);
                Toast.makeText(
                        mContext,
                        "Hello, " + UserData.getUsername(),
                        Toast.LENGTH_SHORT).show();

                mContext.startActivity(new Intent(mContext, HomeActivity.class));
            } catch (JSONException e) {
                String errorDescription = jObject.getString("error_description");
                Toast.makeText(
                        mContext,
                        errorDescription,
                        Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            Toast.makeText(
                    mContext,
                    "Login failed",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
