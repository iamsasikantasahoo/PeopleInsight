package com.people.survey.workmanager;


import android.content.Context;
import android.util.Log;


import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MarvelWorker extends Worker {
    public MarvelWorker(
            @NonNull Context appContext,
            @NonNull WorkerParameters workerParams) {
        super(appContext, workerParams);
    }

    private static final String TAG = MarvelWorker.class.getSimpleName();

    @NonNull
    @Override
    public Result doWork() {

        Context applicationContext = getApplicationContext();

        Data outputData = new Data.Builder()
                .putString("KEY_IMAGE_URI", "xxx")

                .build();
        try {
            return Result.success(outputData);

            // If there were no errors, return SUCCESS
            // return Result.success();
        } catch (Throwable throwable) {

            // Technically WorkManager will return Result.failure()
            // but it's best to be explicit about it.
            // Thus if there were errors, we're return FAILURE
            Log.e(TAG, "Error applying blur", throwable);
            return Result.failure();
        }
    }
}
