package com.udacity.android.bakingapp.ui.fragment;

import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.model.Ingredient;
import com.udacity.android.bakingapp.model.Recipe;
import com.udacity.android.bakingapp.ui.adapter.BakingClickListener;

import java.util.List;

/**
 * Concrete configuration items for an Ingredient List Fragment.
 *
 */
public class IngredientListFragment extends BaseDetailListFragment<Ingredient> {


    @Override
    List<Ingredient> getItemList(Recipe recipe) {
        return recipe.ingredients;
    }

    @Override
    int getAdapterResId() {
        return R.string.app_adapter_ingredients;
    }

    // empty implementation. no click listener registered with Ingredient List Item Fragment
    @Override
    BakingClickListener getClickListener() {
        return null;
    }

}
