package com.udacity.android.bakingapp.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.udacity.android.bakingapp.data.BakingRepository;
import com.udacity.android.bakingapp.model.Recipe;

/**
 * ViewModel for Detail Activity.
 *
 */
public class DetailActivityViewModel extends ViewModel {


    private LiveData<Recipe> mRecipe;

    public DetailActivityViewModel(BakingRepository repository, int recipeId) {
        mRecipe = repository.getRecipeById(recipeId);
    }

    public LiveData<Recipe> getRecipe() {
        return mRecipe;
    }
}
