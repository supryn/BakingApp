package com.udacity.android.bakingapp.ui.adapter;

import androidx.databinding.ViewDataBinding;

import com.udacity.android.bakingapp.databinding.RecipeListItemPhoneBinding;
import com.udacity.android.bakingapp.model.Recipe;

/**
 * A list type adapter binding and creating view holders with recipe data on phone configurations.
 *
 */
class PhoneListTypeAdapter extends BaseListTypeAdapter<Recipe> {


    PhoneListTypeAdapter(BakingClickListener clickListener) {
        super(clickListener);
    }

    @Override
    BaseListTypeViewHolder createListViewHolder(ViewDataBinding binding) {
        return new PhoneListTypeViewHolder((RecipeListItemPhoneBinding) binding);
    }

    class PhoneListTypeViewHolder extends BaseListTypeViewHolder {

        RecipeListItemPhoneBinding mBinding;

        public PhoneListTypeViewHolder(RecipeListItemPhoneBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        @Override
        void bind(Recipe recipe) {
            mBinding.setRecipe(recipe);
        }

    }
}
