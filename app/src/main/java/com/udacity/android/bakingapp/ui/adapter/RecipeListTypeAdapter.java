package com.udacity.android.bakingapp.ui.adapter;

import androidx.databinding.ViewDataBinding;

import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.databinding.RecipeListItemPhoneBinding;
import com.udacity.android.bakingapp.model.Recipe;

/**
 * A list type adapter binding and creating view holders with recipe data.
 *
 */
class RecipeListTypeAdapter extends BaseListTypeAdapter<Recipe> {


    RecipeListTypeAdapter(BakingClickListener clickListener) {
        super(clickListener);
    }

    @Override
    int getLayoutResId() {
        return R.layout.recipe_list_item_phone;
    }

    @Override
    BaseListTypeViewHolder createListViewHolder(ViewDataBinding binding) {
        return new RecipeListTypeViewHolder(binding);
    }

    class RecipeListTypeViewHolder extends BaseListTypeViewHolder {

        RecipeListItemPhoneBinding mBinding;

        RecipeListTypeViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            binding.getRoot().setOnClickListener(this);
            mBinding = (RecipeListItemPhoneBinding) binding;
        }

        @Override
        void bind(Recipe recipe) {
            mBinding.setRecipe(recipe);
        }

    }
}
