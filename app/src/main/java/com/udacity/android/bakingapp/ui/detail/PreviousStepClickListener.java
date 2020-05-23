package com.udacity.android.bakingapp.ui.detail;

import android.content.Intent;

import androidx.fragment.app.FragmentManager;

/**
 * Click listener handling the Previous Step button action, taking the user one Step back.
 *
 */
public class PreviousStepClickListener extends BaseStepNavigationClickListener {


    PreviousStepClickListener(FragmentManager fragmentManager, Intent intent) {
        super(fragmentManager, intent);
    }

    @Override
    void determineStep() {
        if (getCurrentStep() != FIRST_STEP) {
            setCurrentStep(getCurrentStep() - ONE_CONSTANT);
        } else {
            setCurrentStep(getMaxStepsSize());
        }
    }
}

