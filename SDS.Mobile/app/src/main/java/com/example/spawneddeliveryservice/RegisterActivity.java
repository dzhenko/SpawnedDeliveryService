package com.example.spawneddeliveryservice;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class RegisterActivity extends Fragment implements View.OnClickListener {
    static EditText email, password, confirmPassword, phoneNumber;
    static Button btnLogin, btnRegister;
    Context context;
    MainActivity mainActivity;

    public RegisterActivity() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_register, container,
                false);

        this.context = container.getContext();
        this.mainActivity = (MainActivity) this.context;

        //EditText views
        email = (EditText) rootView.findViewById(R.id.etLoginEmail);
        password = (EditText) rootView.findViewById(R.id.etLoginPassword);
        confirmPassword = (EditText) rootView.findViewById(R.id.etLoginConfirmPassword);
        phoneNumber = (EditText) rootView.findViewById(R.id.etLoginPhoneNumber);

        //Button views
        btnLogin = (Button) rootView.findViewById(R.id.btnLogin);
        btnRegister = (Button) rootView.findViewById(R.id.btnRegister);

        this.attachEvents();
        this.fillPhoneNumber();

        return rootView;
    }

    public void fillPhoneNumber() {
        if (phoneNumber != null) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String locatedPhoneNumber = telephonyManager.getLine1Number();

            if (locatedPhoneNumber != null && locatedPhoneNumber != "") {
                phoneNumber.setText(locatedPhoneNumber);
            }
        }
    }

    public void attachEvents() {
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    private class RegisterUser extends AsyncTask<String, Void, String> {
        private String registrationUrl, email, password, confirmPassword, phoneNumber;

        @Override
        protected String doInBackground(String... params) {
            this.registrationUrl = params[0];
            this.email = params[1];
            this.password = params[2];
            this.confirmPassword = params[3];
            this.phoneNumber = params[4];
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(registrationUrl);
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            List<NameValuePair> registrationData = new ArrayList<NameValuePair>();
            registrationData.add(new BasicNameValuePair("email", email));
            registrationData.add(new BasicNameValuePair("password", password));
            registrationData.add(new BasicNameValuePair("confirmpassword", confirmPassword));
            registrationData.add(new BasicNameValuePair("phoneNumber", phoneNumber));

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

            if (result.equals("")) {
                Toast.makeText(
                        context,
                        "Registration successful",
                        Toast.LENGTH_SHORT).show();
                mainActivity.loadLogin();
            } else {
                try {
                    JSONObject jObject = new JSONObject(result);
                    String errorsAsString = jObject.getString("Message");
                    Toast.makeText(
                            context,
                            errorsAsString,
                            Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    Toast.makeText(
                            context,
                            "Incorrect registration data",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void registerUser() {
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();
        String confirmPasswordText = confirmPassword.getText().toString();
        String number = phoneNumber.getText().toString();

        if (passwordText == "" || confirmPasswordText == "") {
            Toast.makeText(
                    this.context,
                    "Please type a password.",
                    Toast.LENGTH_SHORT).show();
        } else if (passwordText.compareTo(confirmPasswordText) != 0) {
            Toast.makeText(
                    this.context,
                    "Password does not match the confirm password.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        String registrationUrl = getResources().getString(R.string.apiBaseUrl) + getResources().getString(R.string.apiUserRegistration);
        RegisterUser registerUser = new RegisterUser();
        registerUser.execute(registrationUrl, emailText, passwordText, confirmPasswordText, number);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLogin) {
            this.mainActivity.loadLogin();
        } else if (v.getId() == R.id.btnRegister) {
            this.registerUser();
        }
    }
}