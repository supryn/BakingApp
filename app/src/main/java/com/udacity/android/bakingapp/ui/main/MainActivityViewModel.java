package com.udacity.android.bakingapp.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.udacity.android.bakingapp.data.BakingRepository;
import com.udacity.android.bakingapp.model.Recipe;

import java.util.List;

/**
 * View Model class for Main Activity.
 *
 */
public class MainActivityViewModel extends ViewModel {

    private LiveData<List<Recipe>> mRecipes;

    public MainActivityViewModel(BakingRepository repository) {
        mRecipes = repository.getRecipes();
    }

    LiveData<List<Recipe>> getRecipes() {
        return mRecipes;
    }
}
