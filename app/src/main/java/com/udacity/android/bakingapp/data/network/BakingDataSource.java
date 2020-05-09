package com.udacity.android.bakingapp.data.network;


import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.udacity.android.bakingapp.model.Recipe;

import java.util.List;

import io.reactivex.schedulers.Schedulers;

/**
 * Data source that accesses network to fetch baking data.
 *
 */
public final class BakingDataSource {

    static final String BAKING_APP_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";
    private static final String JSON_FILE_NAME = "baking.json";

    private static final String LOG_TAG = BakingDataSource.class.getSimpleName();
    private static final String INSTANCE_CREATED = LOG_TAG + " instance created.";

    private static BakingDataSource sInstance;
    private static final Object LOCK = new Object();
    private static RetrofitClient mRetrofitClient;
    private MutableLiveData<List<Recipe>> mRecipes;

    private BakingDataSource(Context context) {
        mRetrofitClient = RetrofitClient.getInstance(context);
        mRecipes = new MutableLiveData<>();
    }

    public static BakingDataSource getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new BakingDataSource(context);
                Log.d(LOG_TAG, INSTANCE_CREATED);
            }
        }

        return sInstance;
    }

    public void fetchRecipeData() {
        mRetrofitClient
                .getNetworkService()
                .fetchRecipes(JSON_FILE_NAME)
                .subscribeOn(Schedulers.io())
                .subscribe(recipes -> mRecipes.postValue(recipes));
    }


    public LiveData<List<Recipe>> getRecipes() {
        return mRecipes;
    }
}
