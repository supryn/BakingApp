package com.udacity.android.bakingapp.ui.fragment;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.udacity.android.bakingapp.R;

/**
 * A Master list Fragment handling tablet based configurations.
 */
public class TabletListFragment extends BaseMainListFragment {

    @Override
    RecyclerView.LayoutManager getLayoutManager(View view) {
        // TODO Configure Grid Layout Manager properly
        return new GridLayoutManager(view.getContext(), 2);
    }

    @Override
    int getAdapterResId() {
        return R.string.app_adapter_recipes;
    }

}
