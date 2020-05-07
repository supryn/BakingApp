package com.udacity.android.bakingapp.data;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.udacity.android.bakingapp.data.database.BakingDao;
import com.udacity.android.bakingapp.data.network.BakingDataSource;
import com.udacity.android.bakingapp.model.Recipe;

import java.util.List;

/**
 * Single source of data access between ViewModel and Data Sources.
 *
 */
public final class BakingRepository {

    private static final String LOG_TAG = BakingRepository.class.getSimpleName();
    private static final String INSTANCE_CREATED = LOG_TAG + " instance created.";
    private static final Object LOCK = new Object();

    private static BakingRepository sInstance;
    private static BakingDao mDao;
    private static BakingDataSource mDataSource;
    private static AppExecutors mExecutors;
    private boolean isDataCached = false;

    private BakingRepository(BakingDao bakingDao, BakingDataSource bakingDataSource, AppExecutors appExecutors) {
        mDao = bakingDao;
        mDataSource = bakingDataSource;
        mExecutors = appExecutors;

        mDataSource.getRecipes().observeForever(recipes -> {
                mDao.deleteAllRecipes();
                mDao.insertRecipes(recipes);
                isDataCached = true;
        });
    }

    public static BakingRepository getInstance(BakingDao bakingDao, BakingDataSource bakingDataSource, AppExecutors appExecutors) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new BakingRepository(bakingDao, bakingDataSource, appExecutors);
                Log.d(LOG_TAG, INSTANCE_CREATED);
            }
        }

        return sInstance;
    }


    public LiveData<List<Recipe>> getRecipes() {
            // TODO re check logic.
            if (!isDataCached || !isDataCached()) {
                mExecutors.getNetworkExecutor().execute(() -> {
                        mDataSource.fetchRecipeData(); });
            }

            return mDao.getRecipes();
    }


    public LiveData<Recipe> getRecipeById(int recipeId) {
        return mDao.getRecipeById(recipeId);
    }

    private boolean isDataCached() {
        mExecutors.getDiskExecutor().execute(() -> isDataCached = mDao.getRecipeCount() > 0);
        return isDataCached;
    }

}
