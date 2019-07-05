package com.people.survey.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.people.survey.data.local.AppDatabase;
import com.people.survey.data.local.dao.MarvelDao;
import com.people.survey.data.local.entity.MarvelEntity;
import com.people.survey.data.remote.APIRequest;
import com.people.survey.data.remote.ApiConstants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppRepository {
    private APIRequest apiRequest;
    private MarvelDao marvelDao;
    private static final Object LOCK = new Object();
    private static AppRepository sInstance;
    private boolean mInitialized = false;
private LiveData<List<MarvelEntity>> allMarvel;
    public synchronized static AppRepository getInstance(Context ctx) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new AppRepository(ctx);
            }
        }
        return sInstance;
    }

    private AppRepository(Context ctx) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiRequest = retrofit.create(APIRequest.class);
        marvelDao = AppDatabase.getInstance(ctx).marvelDao();



        allMarvel = marvelDao.getAll();

    }

    public synchronized void initializeData() {

        // Only perform initialization once per app lifetime. If initialization has already been
        // performed, we have nothing to do in this method.
        if (mInitialized) return;
        mInitialized = true;

        // TODO Finish this method when instructed
    }

    public  LiveData<List<MarvelEntity>> getVoters() {
        final MutableLiveData<List<MarvelEntity>> data = new MutableLiveData<>();

       // return allMarvel;

       // if(!mInitialized) {
            apiRequest.getVoters()
                    .enqueue(new Callback<List<MarvelEntity>>() {
                        @Override
                        public void onResponse(@NonNull Call<List<MarvelEntity>> call, @NonNull Response<List<MarvelEntity>> response) {
                            if (response.isSuccessful()) {
                                initializeData();
                                data.setValue(response.body());

                                new insertAsyncTask(marvelDao).execute(response.body());

                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<List<MarvelEntity>> call, @NonNull Throwable t) {
                            data.setValue(null);
                        }
                    });
            return data;
       /* }else{
            Log.e("marvelDao","marvelDaoDB:"+allMarvel.getValue().size());
             //data.setValue(allMarvel);
            return allMarvel;
        }*/

    }

    private static class insertAsyncTask extends AsyncTask<List<MarvelEntity>, Void, Void> {

        private MarvelDao mAsyncTaskDao;

        insertAsyncTask(MarvelDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final List<MarvelEntity>... params) {

            mAsyncTaskDao.insertAll(params[0]);
            return null;
        }
    }
}
