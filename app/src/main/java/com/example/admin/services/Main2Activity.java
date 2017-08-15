package com.example.admin.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "secondActivity";
    private Button btnPlay, btnPause;
    private ImageView imageView;
    //private MediaPlayer mediaPlayer;
    int val;
    boolean isBound = false;
    String action = "";
    TextView tvSecond;
    MyBindService myBindService;
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
        setContentView(R.layout.activity_main2);
        tvSecond = (TextView) findViewById(R.id.tvSecond);
        readSum = (TextView) findViewById(R.id.readSum);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnPause = (Button) findViewById(R.id.btnPause);
        imageView = (ImageView) findViewById(R.id.imageView);
        intent = getIntent();
        val = Integer.parseInt(intent.getStringExtra("number"));
        Intent boundIntent = new Intent(this, MyBindService.class);
        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        readSum = (TextView) findViewById(R.id.readSum);
        boundIntent.putExtra("text",intent.getStringExtra("number"));
        bindService(boundIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        rvRandomStrings = (RecyclerView) findViewById(R.id.rvRandomStrings);
        layoutManager = new LinearLayoutManager(this);
        itemAnimator = new DefaultItemAnimator();
        rvRandomStrings.setLayoutManager(layoutManager);
        rvRandomStrings.setItemAnimator(itemAnimator);
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected: ");
            MyBindService.MyBinder myBinder = (MyBindService.MyBinder) iBinder;
            myBindService = myBinder.getService();
            int value = Integer.parseInt(intent.getStringExtra("number")) + myBindService.fiveNumber();
            tvSecond.setText(String.valueOf(value));
            isBound = true;
            randomStringList =  myBindService.getRandomData();
            mAdapter = new ArrayAdapter(randomStringList);
            rvRandomStrings.setAdapter(mAdapter);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected: ");
            isBound = false;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //stopService(new Intent(this,MyNormalService.class));
    }

    @Override
    public void onClick(View view) {
        if(view == btnPlay)
            startService(new Intent(this,MyNormalService.class));
        else if(view == btnPause)
            stopService(new Intent(this,MyNormalService.class));
    }
}
