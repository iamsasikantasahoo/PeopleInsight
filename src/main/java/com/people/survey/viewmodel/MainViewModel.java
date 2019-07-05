package com.people.survey.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.people.survey.data.AppRepository;
import com.people.survey.data.local.entity.MarvelEntity;
import com.people.survey.workmanager.MarvelWorker;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private LiveData<List<WorkInfo>> mSavedWorkInfo;
    // public final VoterRecyclerViewAdapter myRecyclerViewAdapter;
    private LiveData<List<MarvelEntity>> voterListLiveData;
    private WorkManager mWorkManager;

    public MainViewModel(@NonNull Application application) {
        super(application);
        voterListLiveData = AppRepository.getInstance(getApplication()).getVoters();
        mWorkManager = WorkManager.getInstance();
        startWorkManager(1);

    }

    public LiveData<List<MarvelEntity>> getVoterListLiveData() {
        if (voterListLiveData != null)
            return voterListLiveData;
        else {
            voterListLiveData = AppRepository.getInstance(getApplication()).getVoters();
            return voterListLiveData;
        }
    }

    public void onMyClick(MarvelEntity voters) {
        Log.e("CLICK", "CLICK:DATABINDING:" + voters.getName());

    }
   public void startWorkManager(int blurLevel) {
      /* */
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(MarvelWorker.class)
                .addTag("TAG_OUTPUT") // This adds the tag
                .build();
        mWorkManager
               .beginUniqueWork("WORK_NAME_HERE",
                       ExistingWorkPolicy.REPLACE,
                       workRequest).enqueue();

        mSavedWorkInfo = mWorkManager.getWorkInfosByTagLiveData("TAG_OUTPUT");


       // mWorkManager.enqueue(OneTimeWorkRequest.from(MarvelWorker.class));
    }
    public LiveData<List<WorkInfo>> getOutputWorkInfo() { return mSavedWorkInfo; }
    void cancelWork() {
        mWorkManager.cancelUniqueWork("TAG_OUTPUT");
    }
}
