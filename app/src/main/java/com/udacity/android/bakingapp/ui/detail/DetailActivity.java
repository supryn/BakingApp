package com.udacity.android.bakingapp.ui.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.model.RecipeUmbrella;
import com.udacity.android.bakingapp.ui.adapter.BakingClickListener;
import com.udacity.android.bakingapp.ui.fragment.BaseDetailListFragment;

public class DetailActivity extends AppCompatActivity implements BakingClickListener {

    public static final String RECIPE_ID_KEY = "recipe_id";
    private int mRecipeId;

    private Button mIngredientsButton;
    private Button mStepsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        mIngredientsButton = findViewById(R.id.ingredients_button);
        mStepsButton = findViewById(R.id.steps_button);
        mRecipeId = getIntent().getIntExtra(RECIPE_ID_KEY, -1);

        registerButton(mIngredientsButton, R.string.app_ingredient_fragment);
        registerButton(mStepsButton, R.string.app_step_fragment);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, BaseDetailListFragment.getInstance(this,R.string.app_step_fragment, mRecipeId))
                .commit();
    }


    private void registerButton(Button button, int fragmentId) {
        button.setOnClickListener(new ButtonClickListener(fragmentId));
    }


    private void switchFragment(int fragmentId) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, BaseDetailListFragment.getInstance(this, fragmentId, mRecipeId))
                .commit();
    }

    @Override
    public void onClick(RecipeUmbrella recipeType) {
        boolean isTablet = getResources().getBoolean(R.bool.isTablet);

        if (isTablet) {

        } else {
            Intent intent = new Intent(getApplicationContext(), StepDetailActivity.class);
            intent.putExtra(StepDetailActivity.STEP_ID_KEY, recipeType.getId());
            intent.putExtra(DetailActivity.RECIPE_ID_KEY, mRecipeId);
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
