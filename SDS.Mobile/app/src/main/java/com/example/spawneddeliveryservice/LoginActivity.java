package com.example.spawneddeliveryservice;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spawneddeliveryservice.webData.UserData;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends Fragment implements View.OnClickListener {
    static EditText email, password, confirmPassword, phoneNumber;
    static Button btnLogin, btnRegister;
    Context context;
    MainActivity mainActivity;

    public LoginActivity() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_login, container,
                false);

        this.context = container.getContext();
        this.mainActivity = (MainActivity) this.context;

        //EditText views
        email = (EditText) rootView.findViewById(R.id.etLoginEmail);
        password = (EditText) rootView.findViewById(R.id.etLoginPassword);

        //Button views
        btnLogin = (Button) rootView.findViewById(R.id.btnLogin);
        btnRegister = (Button) rootView.findViewById(R.id.btnRegister);

        this.attachEvents();

        return rootView;
    }

    public void attachEvents() {
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    private class LoginUser extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String loginUrl = params[0];
            String email = params[1];
            String password = params[2];
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(loginUrl);
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            List<NameValuePair> registrationData = new ArrayList<NameValuePair>();
            registrationData.add(new BasicNameValuePair("Username", email));
            registrationData.add(new BasicNameValuePair("Password", password));
            registrationData.add(new BasicNameValuePair("grant_type", "password"));

            try {
                httpPost.setEntity(new UrlEncodedFormEntity(registrationData));
                HttpResponse httpResponse = httpClient.execute(httpPost);
                InputStream inputStream = httpResponse.getEntity().getContent();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();
                String chunk = null;

                while ((chunk = bufferedReader.readLine()) != null) {
                    stringBuilder.append(chunk);
                }

                return stringBuilder.toString();
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
                                context,
                                "Login failed",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                    UserData data = new UserData();
                    data.setToken(token);
                    data.setUsername(userName);
                    Toast.makeText(
                            context,
                            "Hello, " + data.getUsername(),
                            Toast.LENGTH_SHORT).show();
                    mainActivity.redirectHome();
                } catch (JSONException e) {
                    String errorDescription = jObject.getString("error_description");
                    Toast.makeText(
                            context,
                            errorDescription,
                            Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                Toast.makeText(
                        context,
                        "Login failed",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void loginUser(String emailText, String passwordText) {

        if (passwordText == "") {
            Toast.makeText(
                    this.context,
                    "Please type a password.",
                    Toast.LENGTH_SHORT).show();
        } else if (emailText == "") {
            Toast.makeText(
                    this.context,
                    "Please type email.",
                    Toast.LENGTH_SHORT).show();
        }

        String loginUrl = getResources().getString(R.string.apiBaseUrl) + getResources().getString(R.string.apiUserLogin);
        LoginUser loginUser = new LoginUser();
        loginUser.execute(loginUrl, emailText, passwordText);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnRegister) {
            this.mainActivity.loadRegister();
        } else if (v.getId() == R.id.btnLogin) {
            String emailText = email.getText().toString();
            String passwordText = password.getText().toString();
            this.loginUser(emailText, passwordText);
        }
    }
}