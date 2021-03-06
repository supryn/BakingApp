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
    private static final String RECIPES_INSERTED = "Recipes inserted into database.";
    private static final Object LOCK = new Object();

    private static BakingRepository sInstance;
    private static BakingDao mDao;
    private static BakingDataSource mDataSource;
    private static AppExecutors mExecutors;

    private BakingRepository(BakingDao bakingDao, BakingDataSource bakingDataSource, AppExecutors appExecutors) {
        mDao = bakingDao;
        mDataSource = bakingDataSource;
        mExecutors = appExecutors;

        mDataSource.getRecipes().observeForever(recipes -> mExecutors.getDiskExecutor().execute(() -> {
            mDao.deleteAllRecipes();
            mDao.insertRecipes(recipes);
            Log.d(LOG_TAG, RECIPES_INSERTED);
        }));
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

    /**
     * Returns all the recipes saved in cache.
     * @return List of Recipes.
     */
    public LiveData<List<Recipe>> getRecipes() {
            checkCache();
            return mDao.getRecipes();
    }

    /**
     * Returns a specific Recipe based on its id.
     * @param recipeId A Recipe id.
     * @return A specific Recipe.
     */
    public LiveData<Recipe> getRecipeById(int recipeId) {
        return mDao.getRecipeById(recipeId);
    }

    public Recipe getRecipeByIdSynchronously(int recipeId) {
        return mDao.getRecipeByIdSync(recipeId);
    }

    private void checkCache() {
        mExecutors.getDiskExecutor().execute(() -> {
            if (mDao.getRecipeCount() == 0) {
                mDataSource.fetchRecipeData();
            }
        });

        mDataSource.fetchRecipeData();
    }
}
