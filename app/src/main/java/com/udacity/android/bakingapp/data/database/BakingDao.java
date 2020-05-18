package com.udacity.android.bakingapp.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

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
    @Query("SELECT * FROM recipe_table")
    LiveData<List<Recipe>> getRecipes();


    /**
     * Gets a specific recipe by its id.
     * @param recipeId recipeId.
     * @return list of recipes.
     */
    @Query("SELECT * FROM recipe_table WHERE recipe_id = :recipeId")
    LiveData<Recipe> getRecipeById(int recipeId);



    /**
     * Inserts a bulk of recipes.
     * @param recipes recipe objects
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipes(List<Recipe> recipes);



    /**
     * Deletes all entries in recipe_table.
     *
     */
    @Query("DELETE FROM recipe_table")
    void deleteAllRecipes();


    /**
     * Returns a count of recipes. Used to check if a data fetch from network is needed.
     *
     * @return count of recipes in database
     */
    @Query("SELECT COUNT(recipe_id) FROM recipe_table")
    int getRecipeCount();


}
