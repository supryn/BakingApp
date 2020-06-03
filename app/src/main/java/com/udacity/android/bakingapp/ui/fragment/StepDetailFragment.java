package com.udacity.android.bakingapp.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;
import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.databinding.StepDetailFragmentBinding;
import com.udacity.android.bakingapp.model.Recipe;
import com.udacity.android.bakingapp.model.Step;
import com.udacity.android.bakingapp.ui.detail.DetailActivity;
import com.udacity.android.bakingapp.ui.detail.DetailActivityViewModel;
import com.udacity.android.bakingapp.ui.detail.DetailActivityViewModelFactory;
import com.udacity.android.bakingapp.ui.detail.StepDetailActivity;
import com.udacity.android.bakingapp.utility.ObjectProviderUtil;

/**
 * Fragment displaying detailed Step information.
 *
 */
public class StepDetailFragment extends Fragment {


    private static final String PLAYBACK_POSITION_KEY = "playback_position";

    private int mStepId;
    private int mRecipeId;
    private boolean mPlayWhenReady = true;
    private long mCurrentPlaybackPosition ;
    private static SimpleExoPlayer mExoPlayer;


    public static StepDetailFragment getInstance(int recipeId, int stepId) {
        StepDetailFragment fragment = new StepDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(StepDetailActivity.STEP_ID_KEY, stepId);
        bundle.putInt(DetailActivity.RECIPE_ID_KEY, recipeId);
        fragment.setArguments(bundle);

        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mStepId = getArguments().getInt(StepDetailActivity.STEP_ID_KEY);
        mRecipeId = getArguments().getInt(DetailActivity.RECIPE_ID_KEY);

        if (savedInstanceState != null) {
            mCurrentPlaybackPosition = savedInstanceState.getLong(PLAYBACK_POSITION_KEY, mCurrentPlaybackPosition);
        }

        StepDetailFragmentBinding binding = StepDetailFragmentBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        DetailActivityViewModelFactory factory = ObjectProviderUtil.provideDetailActivityViewModelFactory(binding.getRoot().getContext(), mRecipeId);
        DetailActivityViewModel viewModel = new ViewModelProvider(this, factory).get(DetailActivityViewModel.class);
        viewModel.getRecipe().observe(getViewLifecycleOwner(), recipe -> {
            Step step = getSpecificStep(recipe);
            binding.setStep(step);
            parseMedia(binding, step);
        });

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT >= 24) {
            prepareMediaSource();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if ((Util.SDK_INT < 24 || mExoPlayer == null)) {
            prepareMediaSource();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        releaseExoPlayer();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mExoPlayer != null) {
            releaseExoPlayer();
        }
    }



    private void parseMedia(StepDetailFragmentBinding binding, Step step) {
        if (!isEmpty(step.videoUrl)) {
            prepareMediaSource(binding, Uri.parse(step.videoUrl));
        } else if (!isEmpty(step.thumbnailUrl)) {
            prepareMediaSource(binding, Uri.parse(step.thumbnailUrl));
        } else {
            // neither video nor thumbnail urls available. load default image.
            ImageView defaultImage = binding.getRoot().findViewById(R.id.default_media_image);
            Picasso.get()
                    .load(R.drawable.default_food_image)
                    .into(defaultImage);
            defaultImage.setVisibility(View.VISIBLE);
        }
    }

    private void prepareMediaSource(StepDetailFragmentBinding binding, Uri videoUri) {
        PlayerView playerView = binding.getRoot().findViewById(R.id.player_view);
        playerView.setPlayer(mExoPlayer);
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                Util.getUserAgent(getContext(), getContext().getString(R.string.app_name)));
        MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(videoUri);
        mExoPlayer.seekTo(mCurrentPlaybackPosition);
        mExoPlayer.prepare(videoSource, false, false);
    }

    private void prepareMediaSource() {
        mExoPlayer = new SimpleExoPlayer.Builder(getContext()).build();
        mExoPlayer.setPlayWhenReady(mPlayWhenReady);
    }

    private void releaseExoPlayer() {
        mPlayWhenReady = mExoPlayer.getPlayWhenReady();
        mCurrentPlaybackPosition = mExoPlayer.getCurrentPosition();
        mExoPlayer.release();
        mExoPlayer = null;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(PLAYBACK_POSITION_KEY, mCurrentPlaybackPosition);
    }

    private Step getSpecificStep(Recipe recipe) {
        for (Step step : recipe.steps) {
            if (mStepId == step.stepId)
                return step;
        }
        return null;
    }

    private boolean isEmpty(String url) {
        return url == null;
    }
}
