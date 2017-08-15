package com.example.admin.services;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Button btnStartNormalService, btnStopNormalService, btnBindService, btnUnbindService, btnIntentService, btnJobScheduler;
    MyBindService myBindService;
    boolean isBound = false;
    Intent normalIntent;
    Intent intIntent;
    Intent boundIntent;
    Intent intent;
    TextView readSum;

    ArrayList<String> randomStringList;
    RecyclerView rvRandomStrings;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemAnimator itemAnimator;
    RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void startService(View view) {
        normalIntent = new Intent(this, MyNormalService.class);
        intIntent = new Intent(this, MyIntentService.class);
        boundIntent = new Intent(this, MyBindService.class);
        intent = new Intent(this, Main2Activity.class);
        switch (view.getId()) {
            case R.id.btnStartNormalService:
                normalIntent.putExtra("data", "This is a normal service");
                startService(normalIntent);
                break;
            case R.id.btnStopNormalService:
                stopService(normalIntent);
                break;
            case R.id.btnIntentService:
                intIntent.putExtra("data", "This an Intent Service repo");
                intIntent.setAction("repo");
                startService(intIntent);
                break;
            case R.id.btnBindService:
                bindService(boundIntent, serviceConnection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnUnbindService:
                if (isBound) {
                    unbindService(serviceConnection);
                }
                break;
            case R.id.btnPassInt:
                Random randomGen = new Random();
                intent.putExtra("number", String.valueOf(randomGen.nextInt(100)));
                startActivity(intent);
                break;
            case R.id.btnJobScheduler:
                Log.d(TAG, "onClick: ");
                ComponentName serviceComponent = new ComponentName(this, MyJobService.class);
                JobInfo.Builder jobInfo = new JobInfo.Builder(0, serviceComponent);

                jobInfo.setMinimumLatency(1000);
                jobInfo.setOverrideDeadline(3 * 1000); // maximum delay
                Log.d(TAG, "this is my version "+ Build.VERSION.SDK_INT+"  This is the other   "+ android.os.Build.VERSION_CODES.M);
                JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
                jobScheduler.schedule(jobInfo.build());
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected: ");
            MyBindService.MyBinder myBinder = (MyBindService.MyBinder) iBinder;
            myBindService = myBinder.getService();
            Log.d(TAG, "onServiceConnected: " + myBindService.randomData());
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected: ");
            isBound = false;
        }
    };
}
