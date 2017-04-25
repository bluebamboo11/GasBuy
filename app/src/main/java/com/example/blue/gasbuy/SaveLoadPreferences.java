package com.example.blue.gasbuy;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by blue on 04/03/2017.
 */

public class SaveLoadPreferences {
     private Context context;
    public static final  String KEY_VIEW="keyView";
    public static final  String X="X";
    public static final  String Y="Y";
    public static final  String Ten="ten";
    public static final  String DIA_CHi="diachi";
    public static final  String SDT="sdt";

    public SaveLoadPreferences(Context context) {
        this.context = context;
    }

    public void seveBoolean(String key, boolean value){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean(key,value);
        editor.commit();
    }
    public boolean loadBoolean(String key){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
       return sharedPreferences.getBoolean(key,true);

    }

    public void saveInteger(String key, int value) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int loadInteger(String key, int df) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getInt(key, df);
    }

    public void saveString(String key, String value) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String loadString(String key, String df) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key, df);
    }

}
