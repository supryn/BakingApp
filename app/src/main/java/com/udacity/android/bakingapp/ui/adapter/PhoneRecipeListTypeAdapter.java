package com.udacity.android.bakingapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import com.udacity.android.bakingapp.databinding.RecipeListItemPhoneBinding;
import com.udacity.android.bakingapp.model.Recipe;

public class PhoneRecipeListTypeAdapter extends BaseRecipeListTypeAdapter<Recipe> {


    PhoneRecipeListTypeAdapter(BakingClickListener clickListener) {
        super(clickListener);
    }

    @Override
    BaseRecipeListTypeViewHolder createListViewHolder(ViewDataBinding binding) {
        return new PhoneRecipeListTypeViewHolder((RecipeListItemPhoneBinding) binding);
    }

    class PhoneRecipeListTypeViewHolder extends BaseRecipeListTypeViewHolder {

        RecipeListItemPhoneBinding mBinding;

        public PhoneRecipeListTypeViewHolder(RecipeListItemPhoneBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        @Override
        void bind(Recipe recipe) {
            mBinding.setRecipe(recipe);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(mBinding.getRoot().getContext(), "click twerked", Toast.LENGTH_SHORT).show();
        }
    }
}
