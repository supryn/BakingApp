package com.udacity.android.bakingapp.utility;

import android.content.Context;

import com.udacity.android.bakingapp.data.AppExecutors;
import com.udacity.android.bakingapp.data.BakingRepository;
import com.udacity.android.bakingapp.data.database.BakingDatabase;
import com.udacity.android.bakingapp.data.network.BakingDataSource;
import com.udacity.android.bakingapp.ui.detail.DetailActivityViewModelFactory;
import com.udacity.android.bakingapp.ui.main.MainActivityViewModelFactory;

/**
 *  Utility class providing view models to their respective activities/fragments.
 *
 */
public final class ObjectProviderUtil {

    public static DetailActivityViewModelFactory provideDetailActivityViewModelFactory(Context context, int recipeId) {
        return new DetailActivityViewModelFactory(provideRepository(context), recipeId);
    }

    public static MainActivityViewModelFactory provideMainActivityViewModelFactory(Context context) {
        return new MainActivityViewModelFactory(provideRepository(context));
    }

    // this would be used when needing to instantiate the appropriate objects for running a deferred task with something like WorkManager.
    public static BakingDataSource provideBakingDataSource(Context context) {
        provideRepository(context);
        return BakingDataSource.getInstance(context);
    }

    public static BakingRepository provideRepository(Context context) {
        return BakingRepository.getInstance(
                BakingDatabase.getInstance(context).bakingDao(),
                BakingDataSource.getInstance(context),
                AppExecutors.getInstance());
    }
}
