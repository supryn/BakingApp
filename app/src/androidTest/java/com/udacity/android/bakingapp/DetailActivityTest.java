package com.udacity.android.bakingapp;

import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import com.udacity.android.bakingapp.ui.detail.DetailActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4ClassRunner.class)
public class DetailActivityTest {

    @Rule
    public ActivityTestRule<DetailActivity> mActivityTestRule = new ActivityTestRule<>(DetailActivity.class, false, false);

    @Before
    public void setupActivity() {
        // setup intent for first recipe
        Intent intent = new Intent();
        intent.putExtra(DetailActivity.RECIPE_ID_KEY, 1);
        intent.putExtra(DetailActivity.STEP_LIST_SIZE_KEY, 7);
        mActivityTestRule.launchActivity(intent);
//        mActivityTestRule.getActivity().getResources().getString(R.)
    }

    @Test
    public void test_ClickStepsButton_DisplaysStepListFragment() {
        // check detail activity launched
        onView(withId(R.id.steps_button)).check(matches(withText("Steps")));
        // check step list fragment is displayed
        onView(withId(R.id.recipes_list)).check(matches(hasDescendant(withText("GO"))));
    }

    @Test
    public void test_ClickIngredientsButton_DisplaysIngredientListFragment() {

    }

}
