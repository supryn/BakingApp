package com.udacity.android.bakingapp.ui.detail;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.ui.fragment.StepDetailFragment;

/**
 * Activity displaying a detailed Step.
 *
 */
public class StepDetailActivity extends AppCompatActivity implements BaseStepNavigationClickListener.NavigationClickListener {

    public static final String STEP_ID_KEY = "step_id_key";
    private int mStepId;
    private int mRecipeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_detail_activity);
        mStepId = getIntent().getIntExtra(STEP_ID_KEY, -1);
        mRecipeId = getIntent().getIntExtra(DetailActivity.RECIPE_ID_KEY, -1);


        // register previous/next nav buttons only if in portrait mode
        if (!isLandscape()) {
            setCurrentStep(mStepId);
            registerNavigationButton(R.id.previous_step_button);
            registerNavigationButton(R.id.next_step_button);
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, StepDetailFragment.getInstance(mRecipeId, mStepId))
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(int currentStep) {
        setCurrentStep(currentStep);
    }

    private void registerNavigationButton(int buttonResId) {
        findViewById(buttonResId).setOnClickListener(BaseStepNavigationClickListener
                .getInstance(buttonResId, getSupportFragmentManager(), getIntent(), this));
    }

    private boolean isLandscape() {
        return getResources().getBoolean(R.bool.isLandscape);
    }

    private void setCurrentStep(int currentStep) {
        ((TextView) findViewById(R.id.current_step)).setText(Integer.toString(currentStep));
    }
}
