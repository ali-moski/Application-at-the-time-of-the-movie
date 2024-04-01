package com.example.timemovie.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.timemovie.R;

public class LoginActivity extends AppCompatActivity {

    private EditText userEdt, pasEdt;

    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        userEdt = findViewById(R.id.editTextTextPersonName);
        pasEdt = findViewById(R.id.editTextTextPassword);
        loginBtn = findViewById(R.id.liginBtn);
        loginBtn.setOnClickListener(v -> {
            if (userEdt.getText().toString().isEmpty() || pasEdt.getText().toString().isEmpty()){
                Toast.makeText(LoginActivity.this, "Please Fill your user and password", Toast.LENGTH_SHORT).show();
            }else if (userEdt.getText().toString().equals("aliakhavan") && pasEdt.getText().toString().equals("ali")){
                startActivity( new Intent(LoginActivity.this,MainActivity.class));
            }
        });
    }
}