package com.udacity.android.bakingapp.ui.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.core.view.ViewCompat;
import androidx.databinding.ViewDataBinding;

import com.squareup.picasso.Picasso;
import com.udacity.android.bakingapp.R;

import com.udacity.android.bakingapp.databinding.RecipeListItemBinding;
import com.udacity.android.bakingapp.model.Recipe;

import java.util.Arrays;
import java.util.List;

/**
 * A list type adapter binding and creating view holders with recipe data.
 *
 */
class RecipeListTypeAdapter extends BaseListTypeAdapter<Recipe> {

    private List<Integer> recipeImages = Arrays.asList(R.drawable.nutella_9_6, R.drawable.brownies_9_6, R.drawable.yellowcake_9_6, R.drawable.cheesecake_9_6);

    RecipeListTypeAdapter(BakingClickListener clickListener) {
        super(clickListener);
    }

    @Override
    int getLayoutResId() {
        return R.layout.recipe_list_item;
    }

    @Override
    BaseListTypeViewHolder createListViewHolder(ViewDataBinding binding) {
        return new RecipeListTypeViewHolder(binding);
    }

    class RecipeListTypeViewHolder extends BaseListTypeViewHolder {

        private RecipeListItemBinding mBinding;
        private ImageView mRecipeImage;


        RecipeListTypeViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            binding.getRoot().setOnClickListener(this);
            mBinding = (RecipeListItemBinding) binding;
            mRecipeImage = binding.getRoot().findViewById(R.id.recipe_image);
        }

        @Override
        void bind(Recipe recipe) {
            mBinding.setRecipe(recipe);
            loadImage();
            ViewCompat.setTransitionName(mRecipeImage, recipe.name);
        }

        @Override
        public void onClick(View v) {
            getClickListener().onClick(
                    getDataList().get(getAdapterPosition()),
                    recipeImages.get(getAdapterPosition()),
                    mRecipeImage);
        }

        private void loadImage() {
            Picasso.get()
                    .load(recipeImages.get(getAdapterPosition()))
                    .placeholder(R.drawable.default_food_image)
                    .error(R.drawable.default_food_image)
                    .into(mRecipeImage);
        }
    }
}
