package com.udacity.android.bakingapp.data.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.udacity.android.bakingapp.model.Ingredient;
import com.udacity.android.bakingapp.model.Recipe;
import com.udacity.android.bakingapp.model.Step;

/**
 * Database for storing recipe information.
 *
 */
@Database(entities = {Recipe.class, Ingredient.class, Step.class}, version = 3, exportSchema = false)
@TypeConverters({BakingTypeConverter.class})
public abstract class BakingDatabase extends RoomDatabase {

    private static BakingDatabase sInstance;
    private static final String LOG_TAG = BakingDatabase.class.getSimpleName();

    private static final String DB_NAME = "Baking_App";

    public static BakingDatabase getInstance(Context context) {
        Log.d(LOG_TAG, "Retrieving Database instance.");
        if (sInstance == null) {
            synchronized (BakingDatabase.class) {
                sInstance = Room.databaseBuilder(
                        context, BakingDatabase.class, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
                Log.d(LOG_TAG, "Database instance created.");
            }
        }

        return sInstance;
    }

    public abstract BakingDao bakingDao();

}
