package com.example.spawneddeliveryservice;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        confirmPassword = (EditText) rootView
                .findViewById(R.id.etLoginConfirmPassword);
        phoneNumber = (EditText) rootView.findViewById(R.id.etLoginPhoneNumber);

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

    private void registerUser() {
        Toast.makeText(
                this.context,
                "register button clicked",
                Toast.LENGTH_SHORT).show();
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