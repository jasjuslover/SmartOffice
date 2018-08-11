package com.example.plasma.smartoffice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dd.processbutton.iml.ActionProcessButton;
import com.example.plasma.smartoffice.helper.SessionHelper;
import com.example.plasma.smartoffice.intro.SmartOfficeIntro;

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
  
  public boolean isFirstStart;
  private SessionHelper helper;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);
  
    helper = new SessionHelper(Login.this);
  
    Thread t = new Thread(new Runnable() {
      @Override
      public void run() {
        helper.getSPFirtsStart();
      
        if (isFirstStart) {
          startActivity(new Intent(Login.this, SmartOfficeIntro.class));
          helper.saveSPBoolean(SessionHelper.ISFIRST_START, false);
        }
      }
    });
    t.start();
    
    Glide.with(this).load(R.drawable.piksi).into(logoLogin);
    
    if (helper.getSPSudahLogin()) {
      startActivity(new Intent(this, Home.class));
      finish();
    }
  }
  
  @OnClick(R.id.btn_login)
  public void onClick() {
    btnLogin.setProgress(1);
    boolean isSecretKeyTrue = secretKey.getText().toString().equals("secret123");
    if (isSecretKeyTrue) {
      btnLogin.setProgress(100);
      startActivity(new Intent(this, Home.class));
      helper.saveSPBoolean(SessionHelper.LOGGED_IN, true);
      finish();
    } else {
      Toast.makeText(this, "Wrong Secret Key!", Toast.LENGTH_SHORT).show();
    }
  }
}
