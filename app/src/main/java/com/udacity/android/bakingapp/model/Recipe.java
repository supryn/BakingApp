package com.udacity.android.bakingapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.udacity.android.bakingapp.data.database.BakingTypeConverter;

import java.util.List;

@Entity(tableName = "recipe_table")
public class Recipe implements RecipeUmbrella {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "recipe_id")
    public int recipeId;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "servings")
    public int servings;

    @ColumnInfo(name = "image")
    public String image;

    @ColumnInfo(name = "ingredients")
    @TypeConverters(BakingTypeConverter.class)
    public List<Ingredient> ingredients;

    @ColumnInfo(name = "steps")
    @TypeConverters(BakingTypeConverter.class)
    public List<Step> steps;

    public Recipe(int id, int recipeId, String name, int servings, String image, List<Ingredient> ingredients, List<Step> steps) {
        this.id = id;
        this.recipeId = recipeId;
        this.name = name;
        this.servings = servings;
        this.image = image;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    @Ignore
    public Recipe(int recipeId, String name, int servings, String image, List<Ingredient> ingredients, List<Step> steps) {
        this.recipeId = recipeId;
        this.name = name;
        this.servings = servings;
        this.image = image;
        this.ingredients = ingredients;
        this.steps = steps;
    }
}
