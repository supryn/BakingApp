package com.udacity.android.bakingapp.ui.fragment;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.udacity.android.bakingapp.R;

/**
 * A Master list Fragment handling phone based configurations.
 */
public class RecipeListFragment extends BaseMainListFragment {


    @Override
    RecyclerView.LayoutManager getLayoutManager(View view) {
        RecyclerView.LayoutManager layoutManager;

        if (getResources().getBoolean(R.bool.isLandscape)) {
            layoutManager = new GridLayoutManager(view.getContext(), 2);
        } else if (getResources().getBoolean(R.bool.isTablet)) {
            layoutManager = new GridLayoutManager(view.getContext(), 2);
        } else {
            layoutManager = new LinearLayoutManager(view.getContext());
        }

        return layoutManager;
    }

    @Override
    int getAdapterResId() {
        return R.string.app_adapter_recipes;
    }

    @Override
    BaseMainListFragment getBaseMainListFragment() {
        return this;
    }
}
