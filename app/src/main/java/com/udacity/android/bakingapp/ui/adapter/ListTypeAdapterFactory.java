package com.udacity.android.bakingapp.ui.adapter;


import com.udacity.android.bakingapp.R;

/**
 * Factory generating concrete adapters to accommodate lists of recipes, steps, and their ingredients.
 *
 */
public class ListTypeAdapterFactory {

    private static final String LIST_TYPE_ADAPTER_MSG = "The ListTypeAdapter with resourceId: ";
    private static final String NOT_FOUND = " is not found.";

    public static BaseListTypeAdapter create(int layoutResId, BakingClickListener clickListener) {
        switch (layoutResId) {
            case R.string.app_adapter_recipes:
                return new RecipeListTypeAdapter(clickListener);
            default:
                throw new RuntimeException(LIST_TYPE_ADAPTER_MSG + layoutResId + NOT_FOUND);
        }
    }

}
