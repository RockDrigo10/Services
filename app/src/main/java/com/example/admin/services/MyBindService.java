package com.example.admin.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class MyBindService extends Service {
    private static final String TAG = "BindService";
    IBinder iBinder =  new MyBinder();
    String passed;
    public MyBindService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: " );
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        passed = intent.getStringExtra("text");
        Log.d(TAG, "onBind: " + passed);
        return iBinder;
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    public class MyBinder extends Binder {
        public MyBindService getService(){
            return MyBindService.this;
        }
    }
    public int randomData(){
        Random randomGen = new Random();
        return randomGen.nextInt(100);
    }
    public int fiveNumber(){
        return 5;
    }
    public ArrayList<String> getRandomData(){
        Random random = new Random();
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < Integer.parseInt(passed) ; i++){
            list.add(new BigInteger(130, random).toString(32));
        }
        return list;
    }
}
