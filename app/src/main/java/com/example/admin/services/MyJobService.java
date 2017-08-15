package com.example.admin.services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

/**
 * Created by Admin on 8/15/2017.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService {


    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d("a", "onStartJob: ");
        Intent intent = new Intent(getApplicationContext(),MyScheduleServices.class);
        getApplicationContext().startService(intent);
        return false;

    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
        //if you return true it will restart the job
    }
}
