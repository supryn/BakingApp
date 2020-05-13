package com.udacity.android.bakingapp.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.model.Recipe;
import com.udacity.android.bakingapp.model.RecipeUmbrella;
import com.udacity.android.bakingapp.ui.adapter.BaseListTypeAdapter;
import com.udacity.android.bakingapp.ui.detail.DetailActivityViewModel;
import com.udacity.android.bakingapp.ui.detail.DetailActivityViewModelFactory;
import com.udacity.android.bakingapp.utility.ViewModelInjectUtil;

import java.util.List;

public abstract class BaseDetailListFragment<T extends RecipeUmbrella> extends BaseListFragment {


    static final String RECIPE_ID_KEY = "recipe_id_key";

    public static BaseDetailListFragment getInstance(int resId, int recipeId) {
        BaseDetailListFragment fragment;
        switch (resId) {
            case R.string.app_ingredient_fragment:
                fragment = new IngredientListFragment();
                break;
            case R.string.app_step_fragment:
                fragment = new StepListFragment();
                break;
            default:
                throw new IllegalStateException("Unexpected fragment: " + resId);
        }

        Bundle bundle = new Bundle();
        bundle.putInt(RECIPE_ID_KEY, recipeId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    void observeData(View view, BaseListTypeAdapter adapter, int recipeId) {
        DetailActivityViewModelFactory factory = ViewModelInjectUtil
                .provideDetailActivityViewModelFactory(view.getContext(), recipeId);
        DetailActivityViewModel viewModel = new ViewModelProvider(this, factory)
                .get(DetailActivityViewModel.class);
        viewModel.getRecipe().observe(this, recipe -> {
            adapter.swapData(getItemList(recipe));
        });
    }


    RecyclerView.LayoutManager getLayoutManager(View view) {
        return new LinearLayoutManager(view.getContext());
    }

    abstract List<T> getItemList(Recipe recipe);
}
