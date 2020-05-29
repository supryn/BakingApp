package com.udacity.android.bakingapp.ui;

import android.content.Context;
import android.content.Intent;
import androidx.preference.PreferenceManager;

import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.data.BakingRepository;
import com.udacity.android.bakingapp.model.Ingredient;
import com.udacity.android.bakingapp.model.Step;
import com.udacity.android.bakingapp.ui.detail.DetailActivity;
import com.udacity.android.bakingapp.ui.detail.StepDetailActivity;
import com.udacity.android.bakingapp.utility.ObjectProviderUtil;

import java.util.List;


public class BakingWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new BakingRemoteViewsFactory(this.getApplicationContext());
    }

    class BakingRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

        private static final int VIEW_TYPE_COUNT = 1;

        private Context mContext;
        private List<Step> mSteps;
        private BakingRepository mRepository;

        BakingRemoteViewsFactory(Context context) {
            mContext = context;
        }

        @Override
        public void onCreate() {
            mRepository = ObjectProviderUtil.provideRepository(mContext);
        }

        @Override
        public int getCount() {
            return mSteps != null ? mSteps.size() : 0;
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.widget_list_item);
            int stepId = mSteps.get(position).stepId;
            remoteViews.setTextViewText(R.id.widget_step_number, Integer.toString(stepId));

            Bundle bundle = new Bundle();
            bundle.putInt(StepDetailActivity.STEP_ID_KEY, stepId);
            bundle.putInt(DetailActivity.RECIPE_ID_KEY, mSteps.get(position).recipeId);
            Intent fillInIntent = new Intent();
            fillInIntent.putExtras(bundle);
            remoteViews.setOnClickFillInIntent(R.id.widget_step_button, fillInIntent);

            return remoteViews;
        }

        @Override
        public int getViewTypeCount() {
            return VIEW_TYPE_COUNT;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public void onDestroy() {
            mSteps = null;
            mRepository = null;
        }

        @Override
        public void onDataSetChanged() {
            int recipeId = PreferenceManager.getDefaultSharedPreferences(mContext).getInt(BakingWidgetProvider.RECIPE_ID_KEY, 1);
            mSteps = mRepository.getRecipeByIdSynchronously(recipeId).steps;
        }
    }
}
