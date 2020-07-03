package com.udacity.android.bakingapp.ui.adapter;

import android.widget.ImageView;

import com.udacity.android.bakingapp.model.RecipeUmbrella;

public interface BakingClickListener {

    void onClick(RecipeUmbrella recipeType, int position, ImageView recipeImage);
}
