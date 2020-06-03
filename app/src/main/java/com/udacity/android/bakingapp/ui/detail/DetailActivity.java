package com.udacity.android.bakingapp.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.model.RecipeUmbrella;
import com.udacity.android.bakingapp.ui.adapter.BakingClickListener;
import com.udacity.android.bakingapp.ui.fragment.BaseDetailListFragment;
import com.udacity.android.bakingapp.ui.fragment.StepDetailFragment;

/**
 * Activity displaying more detailed information about a particular Recipe.
 *
 */
public class DetailActivity extends AppCompatActivity implements BakingClickListener {

    public static final String RECIPE_ID_KEY = "recipe_id";
    public static final String STEP_LIST_SIZE_KEY = "step_list_size_id";
    private static final String FRAGMENT_ID_KEY = "fragment_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addStepListFragmentContainer(getIntentExtra(FRAGMENT_ID_KEY, R.string.app_step_fragment));

        // its a Tablet in landscape mode
        if (getResource(R.bool.isTablet) && getResource(R.bool.isTabletLandscape)) {
            addStepDetailFragmentContainer(getIntentExtra(StepDetailActivity.STEP_ID_KEY, 1));
            registerButtons();
        }
        // its a Tablet in portrait mode
        else if (getResource(R.bool.isTablet)) {
            addStepDetailFragmentContainer(getIntentExtra(StepDetailActivity.STEP_ID_KEY, 1));
            addIngredientListFragmentContainer();
        }
        // its a Phone in either portrait or landscape mode
        else {
            registerButtons();
        }
    }

    @Override
    public void onClick(RecipeUmbrella recipeType) {
        if (getResource(R.bool.isTablet)) {
            addStepDetailFragmentContainer(recipeType.getId());
            getIntent().putExtra(StepDetailActivity.STEP_ID_KEY, recipeType.getId());
        } else {
            Intent intent = new Intent(getApplicationContext(), StepDetailActivity.class);
            intent.putExtra(StepDetailActivity.STEP_ID_KEY, recipeType.getId());
            intent.putExtra(DetailActivity.RECIPE_ID_KEY, getIntentExtra(RECIPE_ID_KEY, -1));
            intent.putExtra(DetailActivity.STEP_LIST_SIZE_KEY, getIntentExtra(STEP_LIST_SIZE_KEY, -1));
            startActivity(intent);
        }
    }

    private void addStepListFragmentContainer(int resId) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,
                        BaseDetailListFragment.getInstance(
                                this,
                                resId,
                                getIntentExtra(RECIPE_ID_KEY, -1)))
                .commit();
    }

    private void addIngredientListFragmentContainer() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.ingredients_fragment_container,
                        BaseDetailListFragment.getInstance(
                                this,
                                R.string.app_ingredient_fragment,
                                getIntentExtra(RECIPE_ID_KEY, -1)))
                .commit();
    }

    private void addStepDetailFragmentContainer(int stepId) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.step_detail_fragment_container,
                        StepDetailFragment.getInstance(
                                getIntentExtra(RECIPE_ID_KEY, -1),
                                stepId))
                .commit();
    }

    private void registerButtons() {
        registerButton(R.id.ingredients_button, R.string.app_ingredient_fragment);
        registerButton(R.id.steps_button, R.string.app_step_fragment);
    }

    private void registerButton(int resId, int fragmentId) {
        Button button = findViewById(resId);
        button.setOnClickListener(new ButtonClickListener(fragmentId));
    }

    private boolean getResource(int boolRes) {
        return getResources().getBoolean(boolRes);
    }

    private int getIntentExtra(String key, int defaultValue) {
        return getIntent().getIntExtra(key, defaultValue);
    }

    private void switchFragment(int fragmentId) {
        addStepListFragmentContainer(fragmentId);
        getIntent().putExtra(FRAGMENT_ID_KEY, fragmentId);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    class ButtonClickListener implements View.OnClickListener {

        private int mFragmentId;

        public ButtonClickListener(int fragmentId) {
            mFragmentId = fragmentId;
        }

        @Override
        public void onClick(View v) {
            switchFragment(mFragmentId);
        }
    }
}
