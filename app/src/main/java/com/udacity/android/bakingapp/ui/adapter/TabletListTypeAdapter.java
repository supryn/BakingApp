package com.udacity.android.bakingapp.ui.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import com.udacity.android.bakingapp.databinding.RecipeListItemPhoneBinding;
import com.udacity.android.bakingapp.model.Recipe;

/**
 * A list type adapter binding and creating view holders with recipe data on tablet configurations.
 *
 */
class TabletListTypeAdapter extends BaseListTypeAdapter<Recipe> {

    TabletListTypeAdapter(BakingClickListener clickListener) {
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
    BaseListTypeViewHolder createListViewHolder(ViewDataBinding binding) {
        return new TabletListTypeViewHolder((RecipeListItemPhoneBinding) binding);
    }


    class TabletListTypeViewHolder extends BaseListTypeViewHolder {

        RecipeListItemPhoneBinding mBinding;

        public TabletListTypeViewHolder(RecipeListItemPhoneBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        @Override
        void bind(Recipe recipe) {
            mBinding.setRecipe(recipe);
        }

    }
}
