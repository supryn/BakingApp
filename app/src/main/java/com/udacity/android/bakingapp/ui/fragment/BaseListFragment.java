package com.udacity.android.bakingapp.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.ui.adapter.BakingClickListener;
import com.udacity.android.bakingapp.ui.adapter.BaseListTypeAdapter;
import com.udacity.android.bakingapp.ui.adapter.ListTypeAdapterFactory;



/**
 * Base Master List fragment serves to create Views specifically for lists.
 *
 */
public abstract class BaseListFragment extends Fragment {

    private static final String LOG_TAG = BaseListFragment.class.getSimpleName();

    private int mRecipeId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle savedInstanceState) {
        Log.d(LOG_TAG, "entered onCreateView");
        View view = inflater.inflate(getLayoutId(), viewGroup, false);
        BaseListTypeAdapter adapter = ListTypeAdapterFactory.create(getAdapterResId(), getClickListener());
        setupRecyclerView(view, adapter);
        observeData(view, adapter, getRecipeId(getArguments()));

        return view;
    }

    void setupRecyclerView(View view, RecyclerView.Adapter adapter) {
        RecyclerView mRecyclerView = view.findViewById(R.id.recipes_list);
        mRecyclerView.setLayoutManager(getLayoutManager(view));
        mRecyclerView.setAdapter(adapter);
    }

    void hideProgressBar(View view) {
        view.findViewById(R.id.baking_progress_bar).setVisibility(View.INVISIBLE);
    }

    private int getRecipeId(Bundle bundle) {
        if (bundle != null) {
            mRecipeId = bundle.getInt(BaseDetailListFragment.RECIPE_ID_KEY);
        }
        return mRecipeId;
    }

    abstract RecyclerView.LayoutManager getLayoutManager(View view);

    abstract void observeData(View view, BaseListTypeAdapter adapter, int recipeId);

    abstract int getAdapterResId();

    abstract BakingClickListener getClickListener();

    abstract int getLayoutId();
}
