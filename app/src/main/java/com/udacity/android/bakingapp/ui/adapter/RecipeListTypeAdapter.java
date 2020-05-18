package com.udacity.android.bakingapp.ui.adapter;

import android.widget.ImageView;

import androidx.databinding.ViewDataBinding;

import com.squareup.picasso.Picasso;
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

        private RecipeListItemPhoneBinding mBinding;
        private ImageView mRecipeImage;


        RecipeListTypeViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            binding.getRoot().setOnClickListener(this);
            mBinding = (RecipeListItemPhoneBinding) binding;
            mRecipeImage = binding.getRoot().findViewById(R.id.recipe_image);
        }

        @Override
        void bind(Recipe recipe) {
            mBinding.setRecipe(recipe);
            loadImage(recipe);
        }

        private void loadImage(Recipe recipe) {
            Picasso.get()
                    .load(recipe.image)
                    .placeholder(R.drawable.default_food_image)
                    .error(R.drawable.default_food_image)
                    .into(mRecipeImage);
        }
    }
}
