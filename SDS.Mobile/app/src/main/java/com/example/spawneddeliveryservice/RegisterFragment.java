package com.example.spawneddeliveryservice;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spawneddeliveryservice.tasks.ActiveTransportsTask;
import com.example.spawneddeliveryservice.tasks.RegisterTask;

public class RegisterFragment extends Fragment implements View.OnClickListener {
    static EditText email, password, confirmPassword, phoneNumber;
    static Button btnLogin, btnRegister;
    Context context;
    MainActivity mainActivity;

    public RegisterFragment() {
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

        RegisterTask registerTask = new RegisterTask(this.context);
        registerTask.execute(emailText, passwordText, confirmPasswordText, number);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLogin) {
            this.mainActivity.loadLogin();
        } else if (v.getId() == R.id.btnRegister) {
            (new ActiveTransportsTask()).execute();
            this.registerUser();
        }
    }
}