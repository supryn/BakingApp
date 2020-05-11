package com.udacity.android.bakingapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.model.Recipe;

public class TabletRecipeListTypeAdapter extends BaseRecipeListTypeAdapter<Recipe> {

    TabletRecipeListTypeAdapter(BakingClickListener clickListener) {
        super(clickListener);
    }

//    @NonNull
//    @Override
//    public BaseRecipeListTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View view = inflater.inflate(R.layout.recipe_list_item_tablet, parent, false);
//
//        return new TabletRecipeListTypeViewHolder(view);
//    }

    @Override
    BaseRecipeListTypeViewHolder createListViewHolder(ViewDataBinding binding) {
        return null;
    }


    class TabletRecipeListTypeViewHolder extends BaseRecipeListTypeViewHolder {

        public TabletRecipeListTypeViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        void bind(Recipe recipe) {

        }

        @Override
        public void onClick(View v) {

        }
    }
}
