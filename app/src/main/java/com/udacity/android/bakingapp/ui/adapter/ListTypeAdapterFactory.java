package com.udacity.android.bakingapp.ui.adapter;


import com.udacity.android.bakingapp.R;

/**
 * Factory generating concrete implementations of ListTypeAdapters.
 *
 */
public class ListTypeAdapterFactory {


    public static BaseListTypeAdapter create(int layoutResId, BakingClickListener clickListener) {
        switch (layoutResId) {
            case R.string.app_adapter_recipes_phone:
                return new PhoneListTypeAdapter(clickListener);
            case R.string.app_adapter_recipes_tablet:
                return new TabletListTypeAdapter(clickListener);
            default:
                return null;

        }
    }

}
