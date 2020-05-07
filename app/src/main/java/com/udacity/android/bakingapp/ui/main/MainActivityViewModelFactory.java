package com.udacity.android.bakingapp.ui.main;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.udacity.android.bakingapp.data.BakingRepository;

/**
 * View Model Factory helper class that generates the View Model for the Main Activity.
 *
 */
public class MainActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final BakingRepository mBakingRepository;

    public MainActivityViewModelFactory(BakingRepository repository) {
        mBakingRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new MainActivityViewModel(mBakingRepository);
    }
}
