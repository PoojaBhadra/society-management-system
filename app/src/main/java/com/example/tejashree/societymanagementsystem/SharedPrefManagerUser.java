package com.example.tejashree.societymanagementsystem;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

/**
 * Created by Pratik on 01-Oct-17.
 */

public class SharedPrefManagerUser
{
    private static SharedPrefManagerUser mInstance;
    private static Context mCtx;
    private static final String SHARED_PREF_NAME="mysharedpref12";
    private static final String KEY_SOCIETYNAME="societyName";
    private static final String KEY_FNAME="firstName";
    private static final String KEY_LASTNAME="lastName";
    private static final String KEY_EMAIL="email";
    private static final String KEY_AADHAR="aadharNo";
    private static final String KEY_MOBILE="mobileNo";
    private static final String KEY_STATUS="not accepted";

    private SharedPrefManagerUser(Context context) {
        mCtx = context;

    }

    public static synchronized SharedPrefManagerUser getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManagerUser(context);
        }
        return mInstance;
    }

    public boolean userLogin(String societyName,String firstName,String lastName,String email,String aadharNo,String mobileNo,String status)
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_SOCIETYNAME,societyName);
        editor.putString(KEY_FNAME,firstName);
        editor.putString(KEY_LASTNAME,lastName);
        editor.putString(KEY_EMAIL,email);
        editor.putString(KEY_AADHAR,aadharNo);
        editor.putString(KEY_MOBILE,mobileNo);
        editor.putString(KEY_STATUS,status);
        editor.apply();

        return true;
    }

    public static String getFirstName()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_FNAME,null);
    }

    public static String getLastName()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_LASTNAME,null);
    }
    public static String getSocietyName()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_SOCIETYNAME,null);
    }
    public static String getKeyStatus() {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_STATUS,null);
    }

    public static String getKeyEmail() {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMAIL,null);
    }
    public static boolean isLoggedIn()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_EMAIL,null)!=null)
        {
          return true;
        }
        return false;
    }
    public boolean logout()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }
}
