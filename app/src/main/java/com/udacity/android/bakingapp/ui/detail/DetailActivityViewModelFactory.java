package com.udacity.android.bakingapp.ui.detail;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.udacity.android.bakingapp.data.BakingRepository;

/**
 * View Model Factory helper class that generates the View Model for the Detail Activity.
 *
 */
public class DetailActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final BakingRepository mRepository;
    private int mRecipeId;

    public DetailActivityViewModelFactory(BakingRepository repository, int recipeId) {
        mRepository = repository;
        mRecipeId = recipeId;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new DetailActivityViewModel(mRepository, mRecipeId);
    }
}
