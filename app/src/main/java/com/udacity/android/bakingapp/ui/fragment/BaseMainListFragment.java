package com.udacity.android.bakingapp.ui.fragment;

import android.content.Intent;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;

import com.udacity.android.bakingapp.model.Recipe;
import com.udacity.android.bakingapp.model.RecipeUmbrella;
import com.udacity.android.bakingapp.ui.BakingWidgetProvider;
import com.udacity.android.bakingapp.ui.adapter.BakingClickListener;
import com.udacity.android.bakingapp.ui.adapter.BaseListTypeAdapter;
import com.udacity.android.bakingapp.ui.detail.DetailActivity;
import com.udacity.android.bakingapp.ui.main.MainActivityViewModel;
import com.udacity.android.bakingapp.ui.main.MainActivityViewModelFactory;
import com.udacity.android.bakingapp.utility.ObjectProviderUtil;

import static com.udacity.android.bakingapp.ui.detail.DetailActivity.RECIPE_ID_KEY;
import static com.udacity.android.bakingapp.ui.detail.DetailActivity.STEP_LIST_SIZE_KEY;

/**
 *  Base Main List Fragment provides data from the Main Activity View Model to its concrete implementation classes.
 *
 */
public abstract class BaseMainListFragment extends BaseListFragment implements BakingClickListener  {

    @Override
    void observeData(View view, BaseListTypeAdapter adapter, int recipeId) {
        MainActivityViewModelFactory factory = ObjectProviderUtil
                .provideMainActivityViewModelFactory(view.getContext());
        MainActivityViewModel mViewModel = new ViewModelProvider(this, factory).get(MainActivityViewModel.class);
        mViewModel.getRecipes().observe(this, adapter::swapData);
    }

    @Override
    public void onClick(RecipeUmbrella recipeType) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(RECIPE_ID_KEY, recipeType.getId());
        intent.putExtra(STEP_LIST_SIZE_KEY, ((Recipe) recipeType).steps.size());
        startActivity(intent);
        BakingWidgetProvider.sendRefreshBroadcast(
                getContext(),
                recipeType.getId(),
                ((Recipe) recipeType).name);
    }

    @Override
    BakingClickListener getClickListener() {
        return this;
    }
}
