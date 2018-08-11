package com.example.plasma.smartoffice.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionHelper {
  public static final String PREF_SMARTOFFICE = "PREF_SMARTOFFICE";
  
  public static final String LOGGED_IN = "isLoggedIn";
  public static final String ISFIRST_START= "isFirstStart";
  
  SharedPreferences sp;
  SharedPreferences.Editor spEditor;
  
  public SessionHelper(Context context){
    sp = context.getSharedPreferences(PREF_SMARTOFFICE, Context.MODE_PRIVATE);
    spEditor = sp.edit();
  }
  
  public void saveSPString(String keySP, String value){
    spEditor.putString(keySP, value);
    spEditor.commit();
  }
  
  public void saveSPInt(String keySP, int value){
    spEditor.putInt(keySP, value);
    spEditor.commit();
  }
  
  public void saveSPBoolean(String keySP, boolean value){
    spEditor.putBoolean(keySP, value);
    spEditor.commit();
  }
  
  public Boolean getSPSudahLogin(){
    return sp.getBoolean(LOGGED_IN, false);
  }
  
  public Boolean getSPFirtsStart(){
    return sp.getBoolean(ISFIRST_START, true);
  }
}

