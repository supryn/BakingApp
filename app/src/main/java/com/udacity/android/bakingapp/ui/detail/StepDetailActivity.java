package com.udacity.android.bakingapp.ui.detail;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.ui.fragment.StepDetailFragment;

/**
 * Activity displaying a detailed Step.
 *
 */
public class StepDetailActivity extends AppCompatActivity implements BaseStepNavigationClickListener.NavigationClickListener {

    public static final String STEP_ID_KEY = "step_id_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_detail_activity);
//        setSupportActionBar(findViewById(R.id.toolbar));
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // register previous/next nav buttons only if in portrait mode
        if (!isLandscape()) {
            setCurrentStep(getIntentExtra(STEP_ID_KEY, -1));
            registerNavigationButton(R.id.previous_step_button);
            registerNavigationButton(R.id.next_step_button);
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,
                        StepDetailFragment.getInstance(
                                getIntentExtra(DetailActivity.RECIPE_ID_KEY, -1),
                                getIntentExtra(STEP_ID_KEY, -1)))
                .commit();
    }

    @Override
    public void onClick(int currentStep) {
        setCurrentStep(currentStep);
    }

    private void registerNavigationButton(int buttonResId) {
        findViewById(buttonResId).setOnClickListener(BaseStepNavigationClickListener
                .getInstance(buttonResId, getSupportFragmentManager(), getIntent(), this));
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

    private boolean isLandscape() {
        return getResources().getBoolean(R.bool.isLandscape);
    }

    private void setCurrentStep(int currentStep) {
        ((TextView) findViewById(R.id.current_step)).setText(Integer.toString(currentStep));
    }

    private int getIntentExtra(String key, int defaultValue) {
        return getIntent().getIntExtra(key, defaultValue);
    }
}
