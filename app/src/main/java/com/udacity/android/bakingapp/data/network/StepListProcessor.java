package com.udacity.android.bakingapp.data.network;

import android.content.Context;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.model.Step;

class StepListProcessor extends BaseRecipeListProcessor<Step> {

    private static final int STEP_NUMBER_SET_OFF = 1;
    private static final int DESCRIPTION_BEGIN_INDEX = 3;

    StepListProcessor(Context context) {
        super(context);
    }

    @Override
    Step buildRecipeListItem(int recipeId, JsonObject recipeListItem) {
        int stepId = recipeListItem.get(getStringResource(R.string.json_step_stepId)).getAsInt();
        String shortDescription = recipeListItem.get(getStringResource(R.string.json_step_shortDescription)).getAsString();
        String description = recipeListItem.get(getStringResource(R.string.json_step_description)).getAsString();

        JsonPrimitive jsonVideoUrlPrimitive = recipeListItem.getAsJsonPrimitive(getStringResource(R.string.json_step_video_url));
        String videoUrl = checkEmptyString(jsonVideoUrlPrimitive);

        JsonPrimitive jsonThumbnailUrlPrimitive = recipeListItem.getAsJsonPrimitive(getStringResource(R.string.json_step_thumbnail_url));
        String thumbnailUrl = checkEmptyString(jsonThumbnailUrlPrimitive);

        return new Step(recipeId, stepId + STEP_NUMBER_SET_OFF, shortDescription, parseDescription(stepId, description), videoUrl, thumbnailUrl);
    }

    // used to remove the step number in the description field from every step other than the first one
    private String parseDescription(int stepId, String description) {
        return stepId != 0 ? description.substring(DESCRIPTION_BEGIN_INDEX) : description;
    }
}
