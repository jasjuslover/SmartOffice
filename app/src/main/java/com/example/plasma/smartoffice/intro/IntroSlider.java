package com.example.plasma.smartoffice.intro;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class IntroSlider extends Fragment {
  private static final String ARG_LAYOUT_RES_ID = "layoutResId";
  
  public static IntroSlider newInstance(int layoutResId) {
    IntroSlider slide = new IntroSlider();
    Bundle bundleArgs = new Bundle();
    bundleArgs.putInt(ARG_LAYOUT_RES_ID, layoutResId);
    slide.setArguments(bundleArgs);
    return slide;
  }
  
  private int layoutResId;
  
  public IntroSlider() {}
  
  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    if (getArguments() != null && getArguments().containsKey(ARG_LAYOUT_RES_ID)) {
      layoutResId = getArguments().getInt(ARG_LAYOUT_RES_ID);
    }
  }
  
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(layoutResId, container, false);
  }
}
