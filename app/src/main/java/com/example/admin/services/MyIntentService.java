package com.example.admin.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;


public class MyIntentService extends IntentService {
    private static final String TAG = "MyIntetnService";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: " );
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){e.printStackTrace();}
        switch (intent.getAction()){
            case "repo":
                Log.d(TAG, "onHandleIntent: " + intent.getStringExtra("data") + Thread.currentThread());
                break;
            case "fol":
                Log.d(TAG, "onHandleIntent: " + intent.getStringExtra("data") + Thread.currentThread());
                break;

        }
        //Log.d(TAG, "onHandleIntent: " + Thread.currentThread());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
