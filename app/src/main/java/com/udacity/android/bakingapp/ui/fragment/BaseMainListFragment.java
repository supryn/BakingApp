package com.udacity.android.bakingapp.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.model.Recipe;
import com.udacity.android.bakingapp.model.RecipeUmbrella;
import com.udacity.android.bakingapp.ui.BakingWidgetProvider;
import com.udacity.android.bakingapp.ui.adapter.BakingClickListener;
import com.udacity.android.bakingapp.ui.adapter.BaseListTypeAdapter;
import com.udacity.android.bakingapp.ui.detail.DetailActivity;
import com.udacity.android.bakingapp.ui.main.MainActivityViewModel;
import com.udacity.android.bakingapp.ui.main.MainActivityViewModelFactory;
import com.udacity.android.bakingapp.utility.ObjectProviderUtil;

import java.util.List;

import static com.udacity.android.bakingapp.ui.detail.DetailActivity.RECIPE_ID_KEY;
import static com.udacity.android.bakingapp.ui.detail.DetailActivity.STEP_LIST_SIZE_KEY;

/**
 *  Base Main List Fragment provides data from the Main Activity View Model to its concrete implementation classes.
 *
 */
public abstract class BaseMainListFragment extends BaseListFragment implements BakingClickListener  {

    @Override
    void observeData(View view, BaseListTypeAdapter adapter, int recipeId) {
        MainActivityViewModelFactory factory = ObjectProviderUtil
                .provideMainActivityViewModelFactory(view.getContext());
        MainActivityViewModel mViewModel = new ViewModelProvider(this, factory).get(MainActivityViewModel.class);
        mViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                if (recipes != null && recipes.size() != 0) {
                    hideProgressBar(view);
                    adapter.swapData(recipes);
                }
            }
        });
    }

    @Override
    public void onClick(RecipeUmbrella recipeType, int imageDrawable, ImageView sharedImageView) {
        String imageTransitionName = ViewCompat.getTransitionName(sharedImageView);
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(RECIPE_ID_KEY, recipeType.getId());
        intent.putExtra(STEP_LIST_SIZE_KEY, ((Recipe) recipeType).steps.size());
        intent.putExtra(DetailActivity.EXTRA_RECIPE_IMAGE_TRANSITION_NAME, imageTransitionName);
        intent.putExtra(DetailActivity.EXTRA_RECIPE_IMAGE, imageDrawable);

        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(
                        getActivity(),
                        sharedImageView,
                        imageTransitionName);
        startActivity(intent, options.toBundle());
        BakingWidgetProvider.sendRefreshBroadcast(
                getContext(),
                recipeType.getId(),
                ((Recipe) recipeType).name);
    }

    @Override
    BakingClickListener getClickListener() {
        return this;
    }

    @Override
    int getLayoutId() {
        return R.layout.list_fragment;
    }


    abstract BaseMainListFragment getBaseMainListFragment();
}
