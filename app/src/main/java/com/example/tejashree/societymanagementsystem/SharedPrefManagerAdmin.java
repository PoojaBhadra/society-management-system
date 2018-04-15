package com.example.tejashree.societymanagementsystem;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tejashree on 9/10/17.
 */

public class SharedPrefManagerAdmin
{
    private static SharedPrefManagerAdmin mInstance;
    private static Context mCtx;
    private static final String SHARED_PREF_NAME="mysharedpref12";
    private static final String KEY_SOCIETYNAME="societyName";
    private static final String KEY_ADMINNAME="adminname";
    private static final String KEY_SOCIETYADDRESS="lastName";
    private static final String KEY_EMAIL="email";

    private SharedPrefManagerAdmin(Context context) {
        mCtx = context;

    }

    public static synchronized SharedPrefManagerAdmin getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManagerAdmin(context);
        }
        return mInstance;
    }

    public static String getKeyEmail() {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMAIL,null);
    }
    public static String getKeySocietyname() {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_SOCIETYNAME,null);
    }
    public static String getAdminName() {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ADMINNAME,null);
    }

    public boolean userLogin(String societyName, String adminName, String societyAddress, String email)
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_SOCIETYNAME,societyName);
        editor.putString(KEY_ADMINNAME,adminName);
        editor.putString(KEY_SOCIETYADDRESS,societyAddress);
        editor.putString(KEY_EMAIL,email);

        editor.apply();

        return true;
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
    public static boolean logout()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }
}
