package com.udacity.android.bakingapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "step_table")
public class Step {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "short_description")
    public String shortDescription;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "video_url")
    public String videoUrl;

    @ColumnInfo(name = "thumbnail_url")
    public String thumbnailUrl;

    public Step(int id, String shortDescription, String description, String videoUrl, String thumbnailUrl) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
    }


}
