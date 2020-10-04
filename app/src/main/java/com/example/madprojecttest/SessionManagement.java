package com.example.madprojecttest;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagement {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME="session";
    String SESSION_KEY="session_user";

    public SessionManagement(Context context){

        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();


    }

    public void saveSession(Civilian civilian){

        String un=civilian.getNIC();
        editor.putString(SESSION_KEY,un).commit();

    }

    public String getSession(){

        return sharedPreferences.getString(SESSION_KEY,"No user");
    }

    public void removeSession(){

        editor.putString(SESSION_KEY,"No user").commit();


    }

}
