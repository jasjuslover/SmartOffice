package com.example.plasma.smartoffice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dd.processbutton.iml.ActionProcessButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Login extends AppCompatActivity {

    @BindView(R.id.logo_login)
    ImageView logoLogin;
    @BindView(R.id.secret_key)
    EditText secretKey;
    @BindView(R.id.btn_login)
    ActionProcessButton btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        Glide.with(this).load(R.drawable.piksi).into(logoLogin);
    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        btnLogin.setProgress(1);
        boolean isSecretKeyTrue = secretKey.getText().toString().equals("secret123");
        if (isSecretKeyTrue) {
            btnLogin.setProgress(100);
            startActivity(new Intent(this, Home.class));
            finish();
        } else {
            Toast.makeText(this, "Wrong Secret Key!", Toast.LENGTH_SHORT).show();
        }
    }
}
