package com.udacity.android.bakingapp.data.network;

import android.content.Context;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.model.Step;

class StepListProcessor extends BaseRecipeListProcessor<Step> {


    StepListProcessor(Context context) {
        super(context);
    }

    @Override
    Step buildRecipeListItem(JsonObject recipeListItem) {
        int stepId = recipeListItem.get(getStringResource(R.string.json_step_stepId)).getAsInt();
        String shortDescription = recipeListItem.get(getStringResource(R.string.json_step_shortDescription)).getAsString();
        String description = recipeListItem.get(getStringResource(R.string.json_step_description)).getAsString();

        JsonPrimitive jsonVideoUrlPrimitive = recipeListItem.getAsJsonPrimitive(getStringResource(R.string.json_step_video_url));
        String videoUrl = checkEmptyString(jsonVideoUrlPrimitive);

        JsonPrimitive jsonThumbnailUrlPrimitive = recipeListItem.getAsJsonPrimitive(getStringResource(R.string.json_step_thumbnail_url));
        String thumbnailUrl = checkEmptyString(jsonThumbnailUrlPrimitive);

        return new Step(stepId, shortDescription, description, videoUrl, thumbnailUrl);
    }
}