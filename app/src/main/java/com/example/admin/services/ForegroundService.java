package com.example.admin.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

public class ForegroundService extends Service {
    private static final String TAG = "ForegroundService";

    public ForegroundService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        switch (intent.getAction()) {
            case "startForeground":
                Intent stopIntent = new Intent(this, ForegroundService.class);
                stopIntent.setAction("stopForeground");
                PendingIntent pendingIntent = PendingIntent.getService(this, 0, stopIntent, 0);
                Notification notification = new NotificationCompat.Builder(this).setContentTitle("Music Player")
                        .setContentText("Music playing in foreground").setSmallIcon(R.drawable.mp).setOngoing(true)
                        .addAction(R.drawable.ic_stop, "STOP MUSIC", pendingIntent).build();
                startForeground(1, notification);
                break;
            case "stopForeground":
                stopForeground(true);
                stopService(new Intent(this, MyNormalService.class));
                break;
        }
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
        // throw new UnsupportedOperationException("Not yet implemented");
    }
}
