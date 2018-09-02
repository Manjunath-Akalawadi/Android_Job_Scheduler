package com.demo.manju.job_scheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class MyJobService extends JobService {
    private static final String TAG = "MyJobService";
    private boolean jobCancelled = false;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.d(TAG, "onStartJob: ");
        backgroundWork(jobParameters);
        return true;
    }

    private void backgroundWork(final JobParameters jobParameters) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i=0; i<20; i++){

                    Log.d(TAG,"counting"+i);
                        if (jobCancelled){
                            return;
                        }
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Log.d(TAG,"Job Finished");
                jobFinished(jobParameters,false);

            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {

        Log.d(TAG,"Job Cancelled");
        jobCancelled=true;
        return true;
    }
}
