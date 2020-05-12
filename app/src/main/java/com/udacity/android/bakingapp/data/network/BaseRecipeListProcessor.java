package com.udacity.android.bakingapp.data.network;

import android.content.Context;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.model.RecipeUmbrella;

import java.util.ArrayList;
import java.util.List;

/**
 * Base Recipe List Processor class that parses the json data of both the list of ingredients and steps, and returns a list with a type corresponding to its appropriate model object.
 *
 * @param <T>
 */
abstract class BaseRecipeListProcessor<T extends RecipeUmbrella> {

    private static final String NO_LIST_PROCESSOR_FOUND = "No concrete list processor found with resourceId: ";

    private Context mContext;

    BaseRecipeListProcessor(Context context) {
        mContext = context;
    }

    static BaseRecipeListProcessor getInstance(Context context, int resourceId) {

        switch (resourceId) {
            case R.string.json_recipe_ingredients:
                return new IngredientListProcessor(context);
            case R.string.json_recipe_steps:
                return new StepListProcessor(context);
            default:
                throw new RuntimeException(NO_LIST_PROCESSOR_FOUND + resourceId);
        }

    }

    List<T> processRecipeListItems(JsonArray jsonRecipeListItems) {
        List<T> mRecipeListItems = new ArrayList<>();
        for (JsonElement jsonElement : jsonRecipeListItems) {
            JsonObject recipeListItem = jsonElement.getAsJsonObject();
            mRecipeListItems.add(buildRecipeListItem(recipeListItem));
        }

        return mRecipeListItems;
    }

    abstract T buildRecipeListItem(JsonObject recipeListItem);

    String getStringResource(int resId) {
        return mContext.getString(resId);
    }

    static String checkEmptyString(JsonPrimitive jsonPrimitive) {
        return !jsonPrimitive.getAsString().isEmpty() ? jsonPrimitive.getAsString() : null;
    }
}
