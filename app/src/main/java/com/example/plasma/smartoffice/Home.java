package com.example.plasma.smartoffice;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.plasma.smartoffice.network.response.ParameterValue;
import com.example.plasma.smartoffice.network.response.SetParamValue;
import com.example.plasma.smartoffice.network.RetrofitHelper;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity {
  
  @BindView(R.id.rb_satu_nyala)
  RadioButton rbSatuNyala;
  @BindView(R.id.rb_satu_mati)
  RadioButton rbSatuMati;
  @BindView(R.id.rg_lampu_satu)
  RadioGroup rgLampuSatu;
  @BindView(R.id.rb_dua_nyala)
  RadioButton rbDuaNyala;
  @BindView(R.id.rb_dua_mati)
  RadioButton rbDuaMati;
  @BindView(R.id.rg_lampu_dua)
  RadioGroup rgLampuDua;
  @BindView(R.id.rb_tiga_nyala)
  RadioButton rbTigaNyala;
  @BindView(R.id.rb_tiga_mati)
  RadioButton rbTigaMati;
  @BindView(R.id.rg_lampu_tiga)
  RadioGroup rgLampuTiga;
  
  boolean firstLamp, secondLamp, thirdLamp;
  String perintah;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    ButterKnife.bind(this);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    
    getValueOne();
    getValueTwo();
    getValueThree();
    
    rgLampuSatu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (rgLampuSatu.getCheckedRadioButtonId() == R.id.rb_satu_nyala) {
          setValueOne("1");
        } else if (rgLampuSatu.getCheckedRadioButtonId() == R.id.rb_satu_mati) {
          setValueOne("0");
        }
      }
    });
    
    rgLampuDua.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (rgLampuDua.getCheckedRadioButtonId() == R.id.rb_dua_nyala) {
          setValueTwo("1");
        } else if (rgLampuDua.getCheckedRadioButtonId() == R.id.rb_dua_mati) {
          setValueTwo("0");
        }
      }
    });
    
    rgLampuTiga.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (rgLampuTiga.getCheckedRadioButtonId() == R.id.rb_tiga_nyala) {
          setValueThree("1");
        } else if (rgLampuTiga.getCheckedRadioButtonId() == R.id.rb_tiga_mati) {
          setValueThree("0");
        }
      }
    });
  }
  
  private void getValueOne() {
    final SweetAlertDialog load = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
    load.setTitleText("Refreshing..")
        .setCancelable(false);
    load.show();
    RetrofitHelper.getSmartOfficeService().getParameterValue("qu5FVRoThCYGinP5", BuildConfig.EIOT_AUTH).enqueue(new Callback<ParameterValue>() {
      @Override
      public void onResponse(Call<ParameterValue> call, Response<ParameterValue> response) {
        if (response.body() != null) {
          load.dismiss();
          ParameterValue parameterValue = response.body();
          if (parameterValue.getValue().equalsIgnoreCase("1")) {
            firstLamp = true;
            rbSatuNyala.setChecked(firstLamp);
            rbSatuMati.setChecked(!firstLamp);
          } else if (parameterValue.getValue().equalsIgnoreCase("0")) {
            firstLamp = false;
            rbSatuNyala.setChecked(firstLamp);
            rbSatuMati.setChecked(!firstLamp);
          }
        }
      }
      
      @Override
      public void onFailure(Call<ParameterValue> call, Throwable t) {
        load.dismiss();
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
    final SweetAlertDialog load = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
    load.setTitleText("Refreshing..")
        .setCancelable(false);
    load.show();
    RetrofitHelper.getSmartOfficeService().getParameterValue("rh1eNVcyZpn8mIvv", BuildConfig.EIOT_AUTH).enqueue(new Callback<ParameterValue>() {
      @Override
      public void onResponse(Call<ParameterValue> call, Response<ParameterValue> response) {
        if (response.body() != null) {
          load.dismiss();
          ParameterValue parameterValue = response.body();
          if (parameterValue.getValue().equalsIgnoreCase("1")) {
            secondLamp = true;
            rbDuaNyala.setChecked(secondLamp);
            rbDuaMati.setChecked(!secondLamp);
          } else if (parameterValue.getValue().equalsIgnoreCase("0")) {
            secondLamp = false;
            rbDuaNyala.setChecked(secondLamp);
            rbDuaMati.setChecked(!secondLamp);
          }
        }
      }
      
      @Override
      public void onFailure(Call<ParameterValue> call, Throwable t) {
        load.dismiss();
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
    final SweetAlertDialog load = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
    load.setTitleText("Refreshing..")
        .setCancelable(false);
    load.show();
    RetrofitHelper.getSmartOfficeService().getParameterValue("AEyf4grzMgiLufC9", BuildConfig.EIOT_AUTH).enqueue(new Callback<ParameterValue>() {
      @Override
      public void onResponse(Call<ParameterValue> call, Response<ParameterValue> response) {
        if (response.body() != null) {
          load.dismiss();
          ParameterValue parameterValue = response.body();
          if (parameterValue.getValue().equalsIgnoreCase("1")) {
            thirdLamp = true;
            rbTigaNyala.setChecked(thirdLamp);
            rbTigaMati.setChecked(!thirdLamp);
          } else if (parameterValue.getValue().equalsIgnoreCase("0")) {
            thirdLamp = false;
            rbTigaNyala.setChecked(thirdLamp);
            rbTigaMati.setChecked(!thirdLamp);
          }
        }
      }
      
      @Override
      public void onFailure(Call<ParameterValue> call, Throwable t) {
        load.dismiss();
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
  
  private void promptSpeechInput() {
    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
        Locale.getDefault());
    intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
        "Say something!");
    try {
      startActivityForResult(intent, 100);
    } catch (ActivityNotFoundException a) {
      Toast.makeText(this, "" + a.getMessage(), Toast.LENGTH_SHORT).show();
    }
  }
  
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    switch (requestCode) {
      case 100: {
        if (resultCode == RESULT_OK && null != data) {
          ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
          perintah = result.get(0);
          Log.d("Perintah", "" + perintah);
          switch (perintah) {
            case "lampu satu nyala":
              setValueOne("1");
              break;
            case "lampu dua nyala":
              setValueTwo("1");
              break;
            case "lampu tiga nyala":
              setValueThree("1");
              break;
            case "lampu satu mati":
              setValueOne("0");
              break;
            case "lampu dua mati":
              setValueTwo("0");
              break;
            case "lampu tiga mati":
              setValueThree("0");
              break;
          }

//          if (perintah.equalsIgnoreCase("Lampu Satu Nyala")) {
//            setValueOne("1");
//          } else if (perintah.equalsIgnoreCase("Lampu Satu Mati")) {
//            setValueOne("0");
//          } else if (perintah.equalsIgnoreCase("Lampu Dua Nyala")) {
//            setValueTwo("1");
//          } else if (perintah.equalsIgnoreCase("Lampu Dua Mati")) {
//            setValueTwo("0");
//          } else if (perintah.equalsIgnoreCase("Lampu Tiga Nyala")) {
//            setValueThree("1");
//          } else if (perintah.equalsIgnoreCase("Lampu Tiga Mati")) {
//            setValueThree("0");
//          }
        }
        break;
      }
    }
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
      case R.id.speech:
        promptSpeechInput();
      default:
        return super.onOptionsItemSelected(item);
    }
  }
  
  private void logout() {
    startActivity(new Intent(Home.this, Login.class));
    finish();
  }
}
