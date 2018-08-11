package com.example.plasma.smartoffice.intro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.plasma.smartoffice.Login;
import com.example.plasma.smartoffice.R;
import com.github.paolorotolo.appintro.AppIntro;

public class SmartOfficeIntro extends AppIntro {
  @Override
  public void init(@Nullable Bundle savedInstanceState) {
    super.init(savedInstanceState);
    
    addSlide(IntroSlider.newInstance(R.layout.intro_1));
    addSlide(IntroSlider.newInstance(R.layout.intro_2));
    addSlide(IntroSlider.newInstance(R.layout.intro_3));
    
    showStatusBar(false);
    showSkipButton(false);
    
    setVibrate(true);
    setVibrateIntensity(30);
    
    setDepthAnimation();
  }
  
  @Override
  public void onSkipPressed(Fragment currentFragment) {
    super.onSkipPressed(currentFragment);
    startActivity(new Intent(getApplicationContext(), Login.class));
  }
  
  @Override
  public void onDonePressed() {
    finish();
  }
}
