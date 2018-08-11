package com.example.plasma.smartoffice;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.plasma.smartoffice.helper.SessionHelper;
import com.example.plasma.smartoffice.network.RetrofitHelper;
import com.example.plasma.smartoffice.network.response.ParameterValue;
import com.example.plasma.smartoffice.network.response.SetParamValue;
import com.github.ybq.android.spinkit.SpinKitView;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity {
  
  @BindView(R.id.spin_1)
  SpinKitView spin1;
  @BindView(R.id.lamp_1)
  ImageView lamp1;
  @BindView(R.id.spin_2)
  SpinKitView spin2;
  @BindView(R.id.lamp_2)
  ImageView lamp2;
  @BindView(R.id.spin_3)
  SpinKitView spin3;
  @BindView(R.id.lamp_3)
  ImageView lamp3;
  @BindView(R.id.profile_photo)
  ImageView profilePhoto;
  @BindView(R.id.txt_name)
  TextView txtName;
  @BindView(R.id.txt_status)
  TextView txtStatus;
  @BindView(R.id.txt_icons)
  TextView txtIcons;
  @BindView(R.id.txt_controller)
  TextView txtController;
  @BindView(R.id.text_lamp_1)
  TextView textLamp1;
  @BindView(R.id.text_lamp_2)
  TextView textLamp2;
  @BindView(R.id.text_lamp_3)
  TextView textLamp3;
  @BindView(R.id.txt_icons_all)
  TextView txtIconsAll;
  @BindView(R.id.txt_controller_all)
  TextView txtControllerAll;
  @BindView(R.id.lamp_all)
  ImageView lampAll;
  @BindView(R.id.text_all)
  TextView textAll;
  @BindView(R.id.spin_all)
  SpinKitView spinAll;
  @BindView(R.id.txt_icons_session)
  TextView txtIconsSession;
  @BindView(R.id.txt_user_session)
  TextView txtUserSession;
  @BindView(R.id.tap)
  TextView tap;
  @BindView(R.id.btn_logout)
  CardView btnLogout;
  
  private boolean oneOn, twoOn, threeOn;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    ButterKnife.bind(this);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().hide();
    
    Glide.with(Home.this).load("https://husnikamal.com/wp-content/uploads/2017/04/cropped-boy-270x270.png").into(profilePhoto);
    
    Typeface fontAwesome = Typeface.createFromAsset(this.getAssets(), "FontAwesome.otf");
    txtIcons.setTypeface(fontAwesome);
    txtIconsAll.setTypeface(fontAwesome);
    txtIconsSession.setTypeface(fontAwesome);
    
    Typeface libreFranklin = Typeface.createFromAsset(this.getAssets(), "LibreFranklin.ttf");
    txtName.setTypeface(libreFranklin);
    txtController.setTypeface(libreFranklin);
    textLamp1.setTypeface(libreFranklin);
    textLamp2.setTypeface(libreFranklin);
    textLamp3.setTypeface(libreFranklin);
    txtControllerAll.setTypeface(libreFranklin);
    textAll.setTypeface(libreFranklin);
    txtUserSession.setTypeface(libreFranklin);
    tap.setTypeface(libreFranklin);
    
    getValueOne();
    getValueTwo();
    getValueThree();
  
    if (oneOn && twoOn && threeOn) {
      Glide.with(Home.this).load(R.drawable.on).into(lampAll);
    } else {
      Glide.with(Home.this).load(R.drawable.off).into(lampAll);
    }
  
    btnLogout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        final SweetAlertDialog logout = new SweetAlertDialog(Home.this, SweetAlertDialog.WARNING_TYPE);
        logout.setTitleText("Logout Confirmation")
            .setConfirmText("Yes")
            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
              @Override
              public void onClick(SweetAlertDialog sweetAlertDialog) {
                logout();
              }
            })
            .setCancelText("No")
            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
              @Override
              public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
              }
            }).show();
      }
    });
  }
  
  private void getValueOne() {
    spin1.setVisibility(View.VISIBLE);
    lamp1.setVisibility(View.INVISIBLE);
    RetrofitHelper.getSmartOfficeService().getParameterValue("qu5FVRoThCYGinP5", BuildConfig.EIOT_AUTH).enqueue(new Callback<ParameterValue>() {
      @Override
      public void onResponse(Call<ParameterValue> call, Response<ParameterValue> response) {
        if (response.body() != null) {
          spin1.setVisibility(View.GONE);
          spinAll.setVisibility(View.GONE);
          lamp1.setVisibility(View.VISIBLE);
          
          ParameterValue parameterValue = response.body();
          oneOn = parameterValue.getValue().equalsIgnoreCase("1");
          
          if (parameterValue.getValue().equalsIgnoreCase("1")) {
            Glide.with(Home.this).load(R.drawable.on).into(lamp1);
          } else if (parameterValue.getValue().equalsIgnoreCase("0")) {
            Glide.with(Home.this).load(R.drawable.off).into(lamp1);
          }
          
          validation();
        }
      }
      
      @Override
      public void onFailure(Call<ParameterValue> call, Throwable t) {
        spin1.setVisibility(View.GONE);
        spinAll.setVisibility(View.GONE);
        lamp1.setVisibility(View.VISIBLE);
      }
    });
  }
  
  private void setValueOne(String value) {
    RetrofitHelper.getSmartOfficeService().setParameterValue("qu5FVRoThCYGinP5", value, BuildConfig.EIOT_AUTH).enqueue(new Callback<SetParamValue>() {
      @Override
      public void onResponse(Call<SetParamValue> call, Response<SetParamValue> response) {
        getValueOne();
      }
      
      @Override
      public void onFailure(Call<SetParamValue> call, Throwable t) {
        Log.e("Huwiw", "" + t);
      }
    });
  }
  
  private void getValueTwo() {
    spin2.setVisibility(View.VISIBLE);
    lamp2.setVisibility(View.INVISIBLE);
    RetrofitHelper.getSmartOfficeService().getParameterValue("rh1eNVcyZpn8mIvv", BuildConfig.EIOT_AUTH).enqueue(new Callback<ParameterValue>() {
      @Override
      public void onResponse(Call<ParameterValue> call, Response<ParameterValue> response) {
        if (response.body() != null) {
          spin2.setVisibility(View.GONE);
          spinAll.setVisibility(View.GONE);
          lamp2.setVisibility(View.VISIBLE)
          ;
          ParameterValue parameterValue = response.body();
          twoOn = parameterValue.getValue().equalsIgnoreCase("1");
          
          if (parameterValue.getValue().equalsIgnoreCase("1")) {
            Glide.with(Home.this).load(R.drawable.on).into(lamp2);
          } else if (parameterValue.getValue().equalsIgnoreCase("0")) {
            Glide.with(Home.this).load(R.drawable.off).into(lamp2);
          }
          
          validation();
        }
      }
      
      @Override
      public void onFailure(Call<ParameterValue> call, Throwable t) {
        spin2.setVisibility(View.GONE);
        spinAll.setVisibility(View.GONE);
        lamp2.setVisibility(View.VISIBLE);
      }
    });
  }
  
  private void setValueTwo(String value) {
    RetrofitHelper.getSmartOfficeService().setParameterValue("rh1eNVcyZpn8mIvv", value, BuildConfig.EIOT_AUTH).enqueue(new Callback<SetParamValue>() {
      @Override
      public void onResponse(Call<SetParamValue> call, Response<SetParamValue> response) {
        getValueTwo();
      }
      
      @Override
      public void onFailure(Call<SetParamValue> call, Throwable t) {
        Log.e("Huwiw", "" + t);
      }
    });
  }
  
  private void getValueThree() {
    spin3.setVisibility(View.VISIBLE);
    lamp3.setVisibility(View.INVISIBLE);
    RetrofitHelper.getSmartOfficeService().getParameterValue("AEyf4grzMgiLufC9", BuildConfig.EIOT_AUTH).enqueue(new Callback<ParameterValue>() {
      @Override
      public void onResponse(Call<ParameterValue> call, Response<ParameterValue> response) {
        if (response.body() != null) {
          spin3.setVisibility(View.GONE);
          spinAll.setVisibility(View.GONE);
          lamp3.setVisibility(View.VISIBLE);
          
          ParameterValue parameterValue = response.body();
          threeOn = parameterValue.getValue().equalsIgnoreCase("1");
          
          if (parameterValue.getValue().equalsIgnoreCase("1")) {
            Glide.with(Home.this).load(R.drawable.on).into(lamp3);
          } else if (parameterValue.getValue().equalsIgnoreCase("0")) {
            Glide.with(Home.this).load(R.drawable.off).into(lamp3);
          }
          
          validation();
        }
      }
      
      @Override
      public void onFailure(Call<ParameterValue> call, Throwable t) {
        spin3.setVisibility(View.GONE);
        spinAll.setVisibility(View.GONE);
        lamp3.setVisibility(View.VISIBLE);
      }
    });
  }
  
  private void setValueThree(String value) {
    RetrofitHelper.getSmartOfficeService().setParameterValue("AEyf4grzMgiLufC9", value, BuildConfig.EIOT_AUTH).enqueue(new Callback<SetParamValue>() {
      @Override
      public void onResponse(Call<SetParamValue> call, Response<SetParamValue> response) {
        getValueThree();
      }
      
      @Override
      public void onFailure(Call<SetParamValue> call, Throwable t) {
        Log.e("Huwiw", "" + t);
      }
    });
  }
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_main, menu);
    return true;
  }
  
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_settings:
        final SweetAlertDialog logout = new SweetAlertDialog(Home.this, SweetAlertDialog.WARNING_TYPE);
        logout.setTitleText("Logout Confirmation")
            .setConfirmText("Yes")
            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
              @Override
              public void onClick(SweetAlertDialog sweetAlertDialog) {
                logout();
              }
            })
            .setCancelText("No")
            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
              @Override
              public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
              }
            }).show();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
  
  private void logout() {
    startActivity(new Intent(Home.this, Login.class));
    SessionHelper helper = new SessionHelper(Home.this);
    helper.saveSPBoolean(SessionHelper.LOGGED_IN, false);
    finish();
  }
  
  @OnClick({R.id.lamp_1, R.id.lamp_2, R.id.lamp_3})
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.lamp_1:
        if (oneOn) {
          setValueOne("0");
          return;
        }
        setValueOne("1");
        break;
      case R.id.lamp_2:
        if (twoOn) {
          setValueTwo("0");
          return;
        }
        setValueTwo("1");
        break;
      case R.id.lamp_3:
        if (threeOn) {
          setValueThree("0");
          return;
        }
        setValueThree("1");
        break;
    }
  }
  
  @OnClick(R.id.lamp_all)
  public void onClick() {
    spinAll.setVisibility(View.VISIBLE);
    
    if (!oneOn && !twoOn && !threeOn) {
      setValueOne("1");
      setValueTwo("1");
      setValueThree("1");
    } else if (!oneOn || !twoOn || !threeOn) {
      setValueOne("1");
      setValueTwo("1");
      setValueThree("1");
    } else if (oneOn && twoOn && threeOn){
      setValueOne("0");
      setValueTwo("0");
      setValueThree("0");
    }
  }
  
  private void validation() {
    if (oneOn && twoOn && threeOn) {
      Glide.with(Home.this).load(R.drawable.on).into(lampAll);
      spinAll.setVisibility(View.GONE);
    } else {
      Glide.with(Home.this).load(R.drawable.off).into(lampAll);
      spinAll.setVisibility(View.GONE);
    }
  }
}
