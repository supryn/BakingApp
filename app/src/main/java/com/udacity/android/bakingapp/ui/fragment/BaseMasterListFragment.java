package com.udacity.android.bakingapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.model.RecipeUmbrella;
import com.udacity.android.bakingapp.ui.adapter.BakingClickListener;
import com.udacity.android.bakingapp.ui.adapter.BaseListTypeAdapter;
import com.udacity.android.bakingapp.ui.adapter.ListTypeAdapterFactory;
import com.udacity.android.bakingapp.ui.main.MainActivityViewModel;
import com.udacity.android.bakingapp.ui.main.MainActivityViewModelFactory;
import com.udacity.android.bakingapp.utility.ViewModelInjectUtil;

/**
 * Base Master List Fragment to display list of recipes on phones or tablets.
 *
 */
public abstract class BaseMasterListFragment extends Fragment implements BakingClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.master_list_fragment, viewGroup, false);
        BaseListTypeAdapter adapter = ListTypeAdapterFactory.create(getAdapterResId(), this);
        setupRecyclerView(view, adapter);

        MainActivityViewModelFactory factory = ViewModelInjectUtil
                .provideMainActivityViewModelFactory(view.getContext());
        MainActivityViewModel mViewModel = new ViewModelProvider(this, factory).get(MainActivityViewModel.class);
        mViewModel.getRecipes().observe(this, adapter::swapData);

        return view;
    }

    @Override
    public void onClick(RecipeUmbrella recipeType) {

    }

    private void setupRecyclerView(View view, RecyclerView.Adapter adapter) {
        RecyclerView mRecyclerView = view.findViewById(R.id.recipes_list);
        mRecyclerView.setLayoutManager(getLayoutManager(view));
        mRecyclerView.setAdapter(adapter);
    }

    abstract RecyclerView.LayoutManager getLayoutManager(View view);

    abstract int getAdapterResId();

}
