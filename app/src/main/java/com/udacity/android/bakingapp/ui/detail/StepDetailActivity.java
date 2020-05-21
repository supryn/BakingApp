package com.udacity.android.bakingapp.ui.detail;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.ui.fragment.StepDetailFragment;

/**
 * Activity displaying a detailed Step.
 *
 */
public class StepDetailActivity extends AppCompatActivity {

    public static final String STEP_ID_KEY = "step_id_key";
    private int mStepId;
    private int mRecipeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_detail_activity);
        mStepId = getIntent().getIntExtra(STEP_ID_KEY, -1);
        mRecipeId = getIntent().getIntExtra(DetailActivity.RECIPE_ID_KEY, -1);

        if (!isLandscape()) {
            findViewById(R.id.previous_step_button).setOnClickListener(new PreviousStepClickListener(getSupportFragmentManager(), getIntent()));
            findViewById(R.id.next_step_button).setOnClickListener(new NextStepClickListener(getSupportFragmentManager(), getIntent()));
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, StepDetailFragment.getInstance(mRecipeId, mStepId))
                .commit();
    }

    private boolean isLandscape() {
        return getResources().getBoolean(R.bool.isLandscape);
    }
}
