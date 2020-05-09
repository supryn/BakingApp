package com.udacity.android.bakingapp.data.network;

import android.content.Context;

import com.google.gson.JsonObject;
import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.model.Ingredient;

class IngredientListProcessor extends BaseRecipeListProcessor<Ingredient> {

    IngredientListProcessor(Context context) {
        super(context);
    }

    @Override
    Ingredient buildRecipeListItem(JsonObject recipeListItem) {
        int quantity = recipeListItem.get(getStringResource(R.string.json_ingredient_quantity)).getAsInt();
        String measure = recipeListItem.get(getStringResource(R.string.json_ingredient_measure)).getAsString();
        String ingredient = recipeListItem.get(getStringResource(R.string.json_ingredient_name)).getAsString();

        return new Ingredient(quantity, measure, ingredient);
    }
}
