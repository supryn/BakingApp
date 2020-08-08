package com.udacity.android.bakingapp.ui.adapter;

import android.view.View;

import androidx.databinding.ViewDataBinding;

import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.databinding.IngredientListItemBinding;
import com.udacity.android.bakingapp.model.Ingredient;

public class IngredientListTypeAdapter extends BaseListTypeAdapter<Ingredient> {

    IngredientListTypeAdapter(BakingClickListener clickListener) {
        super(clickListener);
    }

    @Override
    int getLayoutResId() {
        return R.layout.ingredient_list_item;
    }

    @Override
    BaseListTypeViewHolder createListViewHolder(ViewDataBinding binding) {
        return new IngredientListTypeViewHolder(binding);
    }

    class IngredientListTypeViewHolder extends BaseListTypeViewHolder {

        private IngredientListItemBinding mBinding;

        IngredientListTypeViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            // since ingredients don't need a click, leaving click event unregistered.
            mBinding = (IngredientListItemBinding) binding;
        }



        @Override
        void bind(Ingredient ingredient) {
            mBinding.setIngredient(ingredient);
        }

        // empty implementation. No click action needed for Ingredients.
        @Override
        public void onClick(View v) { }
    }
}
