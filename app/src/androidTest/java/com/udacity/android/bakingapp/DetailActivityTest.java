package com.udacity.android.bakingapp;

import android.content.Intent;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import com.udacity.android.bakingapp.ui.detail.DetailActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4ClassRunner.class)
public class DetailActivityTest {

    private static final int RECIPE_ID = 1;
    private static final int STEP_LIST_SIZE = 7;

    @Rule
    public ActivityTestRule<DetailActivity> mTestRule = new ActivityTestRule<>(DetailActivity.class, false, false);

    @Before
    public void setupActivity() {
        Intent intent = new Intent();
        intent.putExtra(DetailActivity.RECIPE_ID_KEY, RECIPE_ID);
        intent.putExtra(DetailActivity.STEP_LIST_SIZE_KEY, STEP_LIST_SIZE);
        mTestRule.launchActivity(intent);
    }

    @Test
    public void test_ClickStepsButton_DisplaysStepListFragment() {
        onView(withId(R.id.steps_button)).check(
                matches(withText(getStringResource(R.string.ui_steps_button_label))));
        onView(withId(R.id.recipes_list)).check(
                matches(
                        hasDescendant(
                                withText(getStringResource(R.string.ui_step_button_label))
                        )
                )
        );
    }

    @Test
    public void test_ClickIngredientsButton_DisplaysIngredientListFragment() {
        onView(withId(R.id.ingredients_button)).check(
                matches(withText(getStringResource(R.string.ui_ingredients_button_label))));
        onView(withId(R.id.ingredients_button)).perform(click());
        onView(withId(R.id.recipes_list)).check(
                matches(
                        hasDescendant(
                                withText(getStringResource(R.string.ui_ingredient_measure_label))
                        )
                )
        );
    }

    private String getStringResource(int resId) {
        return mTestRule.getActivity().getResources().getString(resId);
    }
}
