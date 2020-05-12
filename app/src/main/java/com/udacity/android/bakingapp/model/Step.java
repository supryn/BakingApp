package com.udacity.android.bakingapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "step_table")
public class Step implements RecipeUmbrella {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "step_id")
    public int stepId;

    @ColumnInfo(name = "short_description")
    public String shortDescription;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "video_url")
    public String videoUrl;

    @ColumnInfo(name = "thumbnail_url")
    public String thumbnailUrl;


    public Step(int id, int stepId, String shortDescription, String description, String videoUrl, String thumbnailUrl) {
        this.id = id;
        this.stepId = stepId;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
    }

    @Ignore
    public Step(int stepId, String shortDescription, String description, String videoUrl, String thumbnailUrl) {
        this.stepId = stepId;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public int getId() {
        return stepId;
    }
}
