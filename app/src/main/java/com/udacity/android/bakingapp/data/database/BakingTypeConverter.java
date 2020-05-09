package com.udacity.android.bakingapp.data.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.udacity.android.bakingapp.model.Ingredient;
import com.udacity.android.bakingapp.model.Step;

import java.lang.reflect.Type;
import java.util.List;

public class BakingTypeConverter {

    /**
     * Converts a list of ingredients to Json representation.
     *
     * @param ingredients list of ingredient in object form.
     * @return string of ingredients.
     */
    @TypeConverter
    public String toIngredientsJson(List<Ingredient> ingredients) {
        return new Gson().toJson(ingredients);
    }

    /**
     * Converts a String representation of ingredients into a list.
     *
     * @param ingredients list of ingredients in String form.
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
     * @param steps list of steps in object form.
     * @return string of steps.
     */
    @TypeConverter
    public String toStepsJson(List<Step> steps) {
        return new Gson().toJson(steps);
    }

    /**
     * Converts a String representation of steps into a list.
     *
     * @param steps list of steps in String form.
     * @return list of steps.
     */
    @TypeConverter
    public List<Step> toStepsList(String steps) {
        Type type = new TypeToken<List<Step>>() {}.getType();
        return new Gson().fromJson(steps, type);
    }
}
