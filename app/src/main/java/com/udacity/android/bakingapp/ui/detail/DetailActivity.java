package com.udacity.android.bakingapp.ui.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

    public static final String LOG_TAG = DetailActivity.class.getSimpleName();

    public static final String RECIPE_ID_KEY = "recipe_id";
    public static final String STEP_LIST_SIZE_KEY = "step_list_size_id";
    private static final String FRAGMENT_ID_KEY = "fragment_id";
    private int mRecipeId;
    private int mRecipeStepsSize;
    private boolean mIsTablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        mRecipeId = getIntent().getIntExtra(RECIPE_ID_KEY, -1);
        mRecipeStepsSize = getIntent().getIntExtra(STEP_LIST_SIZE_KEY, -1);
        mIsTablet = getResources().getBoolean(R.bool.isTablet);
        int fragmentId = getIntent().getIntExtra(FRAGMENT_ID_KEY, R.string.app_step_fragment);
        int stepId = getIntent().getIntExtra(StepDetailActivity.STEP_ID_KEY, 1);

        registerButton(R.id.ingredients_button, R.string.app_ingredient_fragment);
        registerButton(R.id.steps_button, R.string.app_step_fragment);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, BaseDetailListFragment.getInstance(this, fragmentId, mRecipeId))
                .commit();

        if (mIsTablet) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.step_detail_fragment_container, StepDetailFragment.getInstance(mRecipeId, stepId))
                    .commit();
        }
    }


    private void registerButton(int resId, int fragmentId) {
        Button button = findViewById(resId);
        button.setOnClickListener(new ButtonClickListener(fragmentId));
    }


    private void switchFragment(int fragmentId) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, BaseDetailListFragment.getInstance(this, fragmentId, mRecipeId))
                .commit();
        getIntent().putExtra(FRAGMENT_ID_KEY, fragmentId);
    }

    @Override
    public void onClick(RecipeUmbrella recipeType) {
        if (mIsTablet) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.step_detail_fragment_container, StepDetailFragment.getInstance(mRecipeId, recipeType.getId()))
                    .commit();
            getIntent().putExtra(StepDetailActivity.STEP_ID_KEY, recipeType.getId());
        } else {
            Intent intent = new Intent(getApplicationContext(), StepDetailActivity.class);
            intent.putExtra(StepDetailActivity.STEP_ID_KEY, recipeType.getId());
            intent.putExtra(DetailActivity.RECIPE_ID_KEY, mRecipeId);
            intent.putExtra(DetailActivity.STEP_LIST_SIZE_KEY, mRecipeStepsSize);
            startActivity(intent);
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
