package com.playappstore.playappstore.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by herui on 8/4/2017.
 */

public class BaseApplication extends Application {
    public static Context sAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sAppContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return sAppContext;
    }



}
