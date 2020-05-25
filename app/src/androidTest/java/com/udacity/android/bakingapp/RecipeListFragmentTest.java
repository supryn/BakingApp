package com.udacity.android.bakingapp;

import android.app.Activity;
import android.app.Instrumentation;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.udacity.android.bakingapp.ui.detail.DetailActivity;
import com.udacity.android.bakingapp.ui.fragment.RecipeListFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.fragment.app.testing.FragmentScenario.launchInContainer;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4ClassRunner.class)
public class RecipeListFragmentTest {


    @Before
    public void setup() {
        launchInContainer(RecipeListFragment.class);
    }

    @Test
    public void test_RecipeListItemIsDisplayed() {
        onView(withId(R.id.recipes_list)).check(matches(isDisplayed()));
    }

    @Test
    public void test_DetailActivityPressBackButton() {
        onView(withId(R.id.recipes_list)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        onView(withId(R.id.steps_button)).check(matches(withText("Steps")));
        pressBack();
        onView(withId(R.id.recipes_list)).check(matches(isDisplayed()));
    }
}
