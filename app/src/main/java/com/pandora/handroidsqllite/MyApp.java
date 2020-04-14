package com.pandora.handroidsqllite;

import android.app.Application;
import android.content.Context;
import android.util.Log;


public class MyApp extends Application {

    private String TAG = this.getClass().getSimpleName();

    private static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate ");
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }
}
