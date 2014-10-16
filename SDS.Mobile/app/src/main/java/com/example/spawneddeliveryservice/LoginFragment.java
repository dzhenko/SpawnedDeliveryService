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

import com.example.spawneddeliveryservice.tasks.LoginTask;

public class LoginFragment extends Fragment implements View.OnClickListener {
    static EditText email, password, confirmPassword, phoneNumber;
    static Button btnLogin, btnRegister;
    Context context;
    MainActivity mainActivity;

    public LoginFragment() {
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

        LoginTask loginTask = new LoginTask(this.context);
        loginTask.execute(emailText, passwordText);
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