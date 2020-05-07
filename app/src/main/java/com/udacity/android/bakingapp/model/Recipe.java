package com.udacity.android.bakingapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.udacity.android.bakingapp.data.database.BakingTypeConverter;

import java.util.List;

@Entity(tableName = "recipe_table")
public class Recipe {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "servings")
    public int servings;

    @ColumnInfo(name = "image")
    private String image;

    @ColumnInfo(name = "ingredients")
    @TypeConverters(BakingTypeConverter.class)
    private List<Ingredient> ingredients;

    @ColumnInfo(name = "steps")
    @TypeConverters(BakingTypeConverter.class)
    private List<Step> steps;

    public Recipe(int id, String name, int servings, String image, List<Ingredient> ingredients, List<Step> steps) {
        this.id = id;
        this.name = name;
        this.servings = servings;
        this.image = image;
        this.ingredients = ingredients;
        this.steps = steps;
    }
}
