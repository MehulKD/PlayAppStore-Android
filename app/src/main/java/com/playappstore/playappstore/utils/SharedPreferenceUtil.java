package com.playappstore.playappstore.utils;

import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;

import com.playappstore.playappstore.base.BaseApplication;

/**
 * Created by herui on 8/4/2017.
 */

public class SharedPreferenceUtil {
    public static final String  HOST = "setting_host"; // host address
    

    public static int ONE_HOUR = 1000 * 60 * 60;

    private SharedPreferences mPrefs;

    public static SharedPreferenceUtil getInstance() {
        return SPHolder.sInstance;
    }

    private static class SPHolder {
        private static final SharedPreferenceUtil sInstance = new SharedPreferenceUtil();
    }

    private SharedPreferenceUtil() {
        mPrefs = BaseApplication.getAppContext().getSharedPreferences("setting", Context.MODE_PRIVATE);
    }

    public SharedPreferenceUtil putInt(String key, int value) {
        mPrefs.edit().putInt(key, value).apply();
        return this;
    }

    public int getInt(String key, int defValue) {
        return mPrefs.getInt(key, defValue);
    }

    public SharedPreferenceUtil putString(String key, String value) {
        mPrefs.edit().putString(key, value).apply();
        return this;
    }

    public String getString(String key, String defValue) {
        return mPrefs.getString(key, defValue);
    }

    public SharedPreferenceUtil putBoolean(String key, boolean value) {
        mPrefs.edit().putBoolean(key, value).apply();
        return this;
    }

    public boolean getBoolean(String key, boolean defValue) {
        return mPrefs.getBoolean(key, defValue);
    }

    public void setHost(String host) {
        mPrefs.edit().putString(HOST, host);
    }
    public String getHost() {
        return mPrefs.getString(HOST, "");
    }
}
