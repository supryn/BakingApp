package com.udacity.android.bakingapp.ui.adapter;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.ui.fragment.BaseDetailListFragment;

public class DetailViewPager {

    private ViewPagerAdapter mAdapter;

    public DetailViewPager(FragmentActivity activity) {
        mAdapter = new ViewPagerAdapter(activity.getSupportFragmentManager());
    }

    public void addFragment(BakingClickListener clickListener, int resId, int recipeId, String tabTitle) {
     Fragment fragment = BaseDetailListFragment.getInstance(clickListener, resId, recipeId);
     mAdapter.addFrag(fragment, tabTitle);

    }

    public ViewPagerAdapter getAdapter() {
        return mAdapter;
    }


}
