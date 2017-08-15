package com.example.admin.services;

import android.app.Application;
import android.util.Log;

/**
 * Created by Admin on 8/14/2017.
 */

public class MyApplication extends Application {
    private static final String TAG = "myApp";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: " );
    }
}
