package com.udacity.android.bakingapp.ui.detail;

import android.content.Intent;
import android.view.View;

import androidx.fragment.app.FragmentManager;

import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.ui.fragment.StepDetailFragment;

/**
 * Base Step Navigation click listener navigating either a step back or forward.
 *
 */
abstract class BaseStepNavigationClickListener implements View.OnClickListener {

    static final int ONE_CONSTANT = 1;
    static final int FIRST_STEP = 1;

    private static int mRecipeId;
    private static int mMaxStepsSize;
    private static int mCurrentStep;
    private FragmentManager mFragmentManager;
    private Intent mIntent;

    public BaseStepNavigationClickListener(FragmentManager fragmentManager, Intent intent) {
        mFragmentManager = fragmentManager;
        mIntent = intent;
        mCurrentStep = intent.getIntExtra(StepDetailActivity.STEP_ID_KEY, -1);
        mMaxStepsSize = intent.getIntExtra(DetailActivity.STEP_LIST_SIZE_KEY, -1);
        mRecipeId = intent.getIntExtra(DetailActivity.RECIPE_ID_KEY, -1);
    }

    @Override
    public void onClick(View v) {
        determineStep();
        switchFragment(getCurrentStep());
        mIntent.putExtra(StepDetailActivity.STEP_ID_KEY, getCurrentStep());
    }

    abstract void determineStep();

    int getCurrentStep() {
        return mCurrentStep;
    }

    void setCurrentStep(int currentStep) {
        mCurrentStep = currentStep;
    }

    int getMaxStepsSize() {
        return mMaxStepsSize;
    }

    private void switchFragment(int stepId) {
        mFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, StepDetailFragment.getInstance(mRecipeId, stepId))
                .commit();
    }
}
