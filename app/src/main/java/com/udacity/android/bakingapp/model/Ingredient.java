package com.udacity.android.bakingapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ingredient_table")
public class Ingredient {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "quantity")
    public int quantity;

    @ColumnInfo(name = "measure")
    public String measure;

    @ColumnInfo(name = "item")
    public String item;

    public Ingredient(int id, int quantity, String measure, String item) {
        this.id = id;
        this.quantity = quantity;
        this.measure = measure;
        this.item = item;
    }
}
