package com.udacity.android.bakingapp.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;

import com.udacity.android.bakingapp.model.Recipe;

import java.util.List;


/**
 * Baking DAO to access database storage.
 *
 */
@Dao
public interface BakingDao {

    /**
     * Gets the list of recipes.
     *
     * @return recipes
     */
    LiveData<List<Recipe>> getRecipes();



    



}
