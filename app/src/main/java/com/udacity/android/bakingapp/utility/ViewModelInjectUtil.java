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
public final class ViewModelInjectUtil {

    public static DetailActivityViewModelFactory provideDetailActivityViewModelFactory(Context context, int recipeId) {
        return new DetailActivityViewModelFactory(getRepository(context), recipeId);
    }

    public static MainActivityViewModelFactory provideMainActivityViewModelFactory(Context context) {
        return new MainActivityViewModelFactory(getRepository(context));
    }

    public static BakingDataSource provideBakingDataSource(Context context) {
        getRepository(context);
        return BakingDataSource.getInstance(context);
    }

    private static BakingRepository getRepository(Context context) {
        return BakingRepository.getInstance(
                BakingDatabase.getInstance(context).bakingDao(),
                BakingDataSource.getInstance(context),
                AppExecutors.getInstance());
    }
}
