package com.udacity.android.bakingapp.ui.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.ui.fragment.BaseDetailListFragment;

public class DetailActivity extends AppCompatActivity {

    public static final String RECIPE_ID_KEY = "recipe_id";
    private int mRecipeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mRecipeId = getIntent().getIntExtra(RECIPE_ID_KEY, -1);


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, BaseDetailListFragment.getInstance(R.string.app_ingredient_fragment, mRecipeId))
                .commit();
    }
}
