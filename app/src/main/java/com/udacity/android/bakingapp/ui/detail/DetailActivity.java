package com.udacity.android.bakingapp.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.model.RecipeUmbrella;
import com.udacity.android.bakingapp.ui.adapter.BakingClickListener;
import com.udacity.android.bakingapp.ui.adapter.DetailViewPager;
import com.udacity.android.bakingapp.ui.adapter.ViewPagerAdapter;
import com.udacity.android.bakingapp.ui.fragment.BaseDetailListFragment;
import com.udacity.android.bakingapp.ui.fragment.StepDetailFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity displaying more detailed information about a particular Recipe.
 *
 */
public class DetailActivity extends AppCompatActivity implements BakingClickListener {

    public static final String RECIPE_ID_KEY = "recipe_id";
    public static final String STEP_LIST_SIZE_KEY = "step_list_size_id";
    private static final String FRAGMENT_ID_KEY = "fragment_id";

    private static ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewPager = findViewById(R.id.tab_viewpager);
        if (mViewPager != null) {
            configureViewPager();
            configureTabLayout();
        }


//        addStepListFragmentContainer(getIntentExtra(FRAGMENT_ID_KEY, R.string.app_step_fragment));
//
//        // its a Tablet in landscape mode
//        if (getResource(R.bool.isTablet) && getResource(R.bool.isTabletLandscape)) {
//            addStepDetailFragmentContainer(getIntentExtra(StepDetailActivity.STEP_ID_KEY, 1));
//            registerButtons();
//        }
//        // its a Tablet in portrait mode
//        else if (getResource(R.bool.isTablet)) {
//            addStepDetailFragmentContainer(getIntentExtra(StepDetailActivity.STEP_ID_KEY, 1));
//            addIngredientListFragmentContainer();
//        }
//        // its a Phone in either portrait or landscape mode
//        else {
//            registerButtons();
//        }
    }

    private void configureTabLayout() {
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }

    private void configureViewPager() {
            DetailViewPager detailViewPager = new DetailViewPager(this);
            // add Steps tab
            detailViewPager.addFragment(this,
                    getIntentExtra(FRAGMENT_ID_KEY, R.string.app_step_fragment),
                    getIntentExtra(RECIPE_ID_KEY, -1),
                    getString(R.string.ui_steps_button_label));
            // add Ingredients tab
            detailViewPager.addFragment(this,
                    R.string.app_ingredient_fragment,
                    getIntentExtra(RECIPE_ID_KEY, -1),
                    getString(R.string.ui_ingredients_button_label));
            mViewPager.setAdapter(detailViewPager.getAdapter());
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

    private void addStepDetailFragmentContainer(int stepId) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.step_detail_fragment_container,
                        StepDetailFragment.getInstance(
                                getIntentExtra(RECIPE_ID_KEY, -1),
                                stepId))
                .commit();
    }


    private boolean getResource(int boolRes) {
        return getResources().getBoolean(boolRes);
    }

    private int getIntentExtra(String key, int defaultValue) {
        return getIntent().getIntExtra(key, defaultValue);
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

}
