package com.udacity.android.bakingapp.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;
import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.model.RecipeUmbrella;
import com.udacity.android.bakingapp.ui.adapter.BakingClickListener;
import com.udacity.android.bakingapp.ui.adapter.DetailViewPager;
import com.udacity.android.bakingapp.ui.fragment.StepDetailFragment;

/**
 * Activity displaying more detailed information about a particular Recipe.
 *
 */
public class DetailActivity extends AppCompatActivity implements BakingClickListener {

    public static final String RECIPE_ID_KEY = "recipe_id";
    public static final String STEP_LIST_SIZE_KEY = "step_list_size_id";
    public static final String EXTRA_RECIPE_IMAGE_TRANSITION_NAME = "recipe_image_transition_name";
    public static final String EXTRA_RECIPE_IMAGE = "EXTRA_RECIPE_IMAGE";
    public static final String EXTRA_RECIPE_NAME = "EXTRA_RECIPE_NAME";
    private static final String FRAGMENT_ID_KEY = "fragment_id";


    private static ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        configureAppBar();
        mViewPager = findViewById(R.id.tab_viewpager);
        if (mViewPager != null) {
            configureViewPager();
            configureTabLayout();
        }

    }

    private void configureAppBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getIntent().getExtras().getString(EXTRA_RECIPE_NAME));
        toolbar.setTitleTextColor(getColor(R.color.colorWhite));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ImageView imageView = findViewById(R.id.app_bar_image);
        String imageTransitionName = getIntent().getExtras().getString(EXTRA_RECIPE_IMAGE_TRANSITION_NAME);
        imageView.setTransitionName(imageTransitionName);
        int imageResId = getIntent().getExtras().getInt(DetailActivity.EXTRA_RECIPE_IMAGE);
        Picasso.get()
                .load(imageResId)
                .noFade()
                .into(imageView);
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
    public void onClick(RecipeUmbrella recipeType, int position, ImageView image) {
        if (getResource(R.bool.isTablet)) {
            addStepDetailFragmentContainer(recipeType.getId());
            getIntent().putExtra(StepDetailActivity.STEP_ID_KEY, recipeType.getId());
        } else {
            Intent intent = new Intent(getApplicationContext(), StepDetailActivity.class);
            intent.putExtra(StepDetailActivity.STEP_ID_KEY, recipeType.getId());
            intent.putExtra(DetailActivity.RECIPE_ID_KEY, getIntentExtra(RECIPE_ID_KEY, -1));
            intent.putExtra(DetailActivity.STEP_LIST_SIZE_KEY, getIntentExtra(STEP_LIST_SIZE_KEY, -1));
            intent.putExtra(EXTRA_RECIPE_NAME, getIntent().getExtras().getString(EXTRA_RECIPE_NAME));
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
