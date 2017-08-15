package com.example.admin.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MyNormalService extends Service {
    private static final String TAG = "MyServiceTAG";
    private MediaPlayer mediaPlayer;
    public MyNormalService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer = MediaPlayer.create(this, R.raw.despacito);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
//        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        super.onDestroy();
    }
}
