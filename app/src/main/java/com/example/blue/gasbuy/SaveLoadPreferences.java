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
}
