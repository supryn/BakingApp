package com.udacity.android.bakingapp.data.network;

import android.content.Context;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
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

    public RecipeDeserializer(Context context) {
        mContext = context;
    }


    @Override
    public List<Recipe> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<Recipe> recipes = new ArrayList<>();



        return recipes;
    }


    private static String getStringResource(Context context, int resId) {
        return context.getString(resId);
    }
}
