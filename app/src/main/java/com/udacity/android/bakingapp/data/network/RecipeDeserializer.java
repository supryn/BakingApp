package com.udacity.android.bakingapp.data.network;

import android.content.Context;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.model.Recipe;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Deserializes the raw json response data into their respective Recipe model objects.
 *
 */
public class RecipeDeserializer implements JsonDeserializer<List<Recipe>> {

    private Context mContext;

    RecipeDeserializer(Context context) {
        mContext = context;
    }

    @Override
    public List<Recipe> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        BaseRecipeListProcessor ingredientListProcessor = BaseRecipeListProcessor
                .getInstance(mContext, R.string.json_recipe_ingredients);
        BaseRecipeListProcessor stepListProcessor = BaseRecipeListProcessor
                .getInstance(mContext, R.string.json_recipe_steps);

        List<Recipe> recipes = new ArrayList<>();
        for (JsonElement jsonElement : json.getAsJsonArray()) {
            JsonObject jsonRecipe = jsonElement.getAsJsonObject();
            int recipeId = jsonRecipe.get(getStringResource(R.string.json_recipe_id)).getAsInt();
            String recipeName = jsonRecipe.get(getStringResource(R.string.json_recipe_name)).getAsString();
            int recipeServings = jsonRecipe.get(getStringResource(R.string.json_recipe_servings)).getAsInt();

            JsonPrimitive jsonRecipeImagePrimitive = jsonRecipe.getAsJsonPrimitive(getStringResource(R.string.json_recipe_image));
            String jsonRecipeImage = BaseRecipeListProcessor.checkEmptyString(jsonRecipeImagePrimitive);

            JsonArray jsonIngredients = jsonRecipe.getAsJsonArray(getStringResource(R.string.json_recipe_ingredients));
            List ingredients = ingredientListProcessor.processRecipeListItems(jsonIngredients);

            JsonArray jsonSteps = jsonRecipe.getAsJsonArray(getStringResource(R.string.json_recipe_steps));
            List steps = stepListProcessor.processRecipeListItems(jsonSteps);

            recipes.add(new Recipe(recipeId, recipeName, recipeServings, jsonRecipeImage, ingredients, steps));
        }

        return recipes;
    }

    private String getStringResource(int resId) {
        return mContext.getString(resId);
    }
}
