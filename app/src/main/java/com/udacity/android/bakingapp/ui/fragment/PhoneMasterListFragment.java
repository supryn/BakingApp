package com.udacity.android.bakingapp.ui.fragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.model.RecipeUmbrella;

/**
 * A Master list Fragment handling phone based configurations.
 */
public class PhoneMasterListFragment extends BaseMasterListFragment {


    @Override
    RecyclerView.LayoutManager getLayoutManager(View view) {
        return new LinearLayoutManager(view.getContext());
    }

    @Override
    int getAdapterResId() {
        return R.string.app_adapter_recipes_phone;
    }

}
