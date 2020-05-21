package com.udacity.android.bakingapp.ui.fragment;

import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.model.Recipe;
import com.udacity.android.bakingapp.model.RecipeUmbrella;
import com.udacity.android.bakingapp.model.Step;
import com.udacity.android.bakingapp.ui.adapter.BakingClickListener;

import java.util.List;

public class StepListFragment extends BaseDetailListFragment<Step> implements BakingClickListener {

    private BakingClickListener mStepClickListener;

    public StepListFragment() { }

    public StepListFragment(BakingClickListener clickListener) {
        mStepClickListener = clickListener;
    }

    @Override
    int getAdapterResId() {
        return R.string.app_adapter_steps;
    }

    @Override
    BakingClickListener getClickListener() {
        return this;
    }

    @Override
    List<Step> getItemList(Recipe recipe) {
        return recipe.steps;
    }

    @Override
    public void onClick(RecipeUmbrella recipeType) {
        mStepClickListener.onClick(recipeType);
    }
}
