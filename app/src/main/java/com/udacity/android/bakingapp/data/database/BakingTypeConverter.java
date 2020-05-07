package com.udacity.android.bakingapp.data.database;

import com.google.gson.Gson;
import androidx.room.TypeConverter;

import com.google.gson.reflect.TypeToken;
import com.udacity.android.bakingapp.model.Ingredient;
import com.udacity.android.bakingapp.model.Step;

import java.lang.reflect.Type;
import java.util.List;

public class BakingTypeConverter {

    /**
     * Converts a list of ingredients to Json representation.
     *
     * @param ingredients
     * @return string of ingredients.
     */
    @TypeConverter
    public String toIngredientsJson(List<Ingredient> ingredients) {
        return new Gson().toJson(ingredients);
    }

    /**
     * Converts a String representation of ingredients into a list.
     *
     * @param ingredients
     * @return list of ingredients.
     */
    @TypeConverter
    public List<Ingredient> toIngredientsList(String ingredients) {
        Type type = new TypeToken<List<Ingredient>>() {}.getType();
        return new Gson().fromJson(ingredients, type);
    }


    /**
     * Converts a list of ingredients to Json representation.
     *
     * @param steps
     * @return string of steps.
     */
    @TypeConverter
    public String toStepsJson(List<Step> steps) {
        return new Gson().toJson(steps);
    }

    /**
     * Converts a String representation of steps into a list.
     *
     * @param steps
     * @return list of steps.
     */
    @TypeConverter
    public List<Step> toStepsList(String steps) {
        Type type = new TypeToken<List<Step>>() {}.getType();
        return new Gson().fromJson(steps, type);
    }
}
