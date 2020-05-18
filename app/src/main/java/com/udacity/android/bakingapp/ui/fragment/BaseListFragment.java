package com.udacity.android.bakingapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.ui.adapter.BakingClickListener;
import com.udacity.android.bakingapp.ui.adapter.BaseListTypeAdapter;
import com.udacity.android.bakingapp.ui.adapter.ListTypeAdapterFactory;



/**
 * Base Master List fragment serves to create Views specifically for lists.
 *
 */
public abstract class BaseListFragment extends Fragment {

    private int mRecipeId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, viewGroup, false);
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

    abstract RecyclerView.LayoutManager getLayoutManager(View view);

    abstract void observeData(View view, BaseListTypeAdapter adapter, int recipeId);

    abstract int getAdapterResId();

    // the reason for this abstraction is because every fragment requires to register a click listener when creating an adapter for recycler for in the BaseListFragment,
    // but the only one that doesn't is IngredientListFragment, so pushing definition down to its concrete classes.
    abstract BakingClickListener getClickListener();


    private int getRecipeId(Bundle bundle) {
        if (bundle != null) {
            mRecipeId = bundle.getInt(BaseDetailListFragment.RECIPE_ID_KEY);
        }
        return mRecipeId;
    }

}
