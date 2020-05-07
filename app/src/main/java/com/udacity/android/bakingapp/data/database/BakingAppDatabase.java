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

@Database(entities = {Recipe.class, Ingredient.class, Step.class}, version = 1, exportSchema = false)
@TypeConverters({BakingTypeConverter.class})
public abstract class BakingAppDatabase extends RoomDatabase {

    private static BakingAppDatabase sInstance;
    private static final String LOG_TAG = BakingAppDatabase.class.getSimpleName();

    private static final String DB_NAME = "Popular_Movies";


    public static BakingAppDatabase getInstance(Context context) {
        Log.d(LOG_TAG, "Retrieving Database instance.");
        if (sInstance == null) {
            synchronized (BakingAppDatabase.class) {
                sInstance = Room.databaseBuilder(
                        context, BakingAppDatabase.class, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
                Log.d(LOG_TAG, "Database instance created.");
            }
        }

        return sInstance;
    }




}
