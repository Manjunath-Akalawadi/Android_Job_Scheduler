package com.demo.manju.job_scheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void StartJob(View view) {

        ComponentName componentName = new ComponentName(this,MyJobService.class);
        JobInfo jobInfo = new JobInfo.Builder(123,componentName)
                .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(15 * 60 * 1000)
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int rCode =  scheduler.schedule(jobInfo);
        if (rCode ==  JobScheduler.RESULT_SUCCESS){

            Log.d(TAG,"Job Schedule");
        }else {

            Log.d(TAG,"Job Schedule Failed");
        }
    }

    public void StopJob(View view) {

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(123);

        Log.d(TAG,"Job Schedule Cancelled");

    }
}
