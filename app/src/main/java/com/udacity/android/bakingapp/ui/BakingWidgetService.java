package com.udacity.android.bakingapp.ui;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.data.BakingRepository;
import com.udacity.android.bakingapp.model.Ingredient;
import com.udacity.android.bakingapp.utility.ObjectProviderUtil;

import java.util.List;

import static com.udacity.android.bakingapp.ui.detail.DetailActivity.RECIPE_ID_KEY;

public class BakingWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new BakingRemoteViewsFactory(this.getApplicationContext());
    }

    class BakingRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

        private static final int VIEW_TYPE_COUNT = 1;

        private Context mContext;
        private List<Ingredient> mIngredients;
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
            return mIngredients != null ? mIngredients.size() : 0;
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.widget_list_item);
            remoteViews.setTextViewText(R.id.recipe_name, mIngredients.get(position).item);

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
            mIngredients = null;
            mRepository = null;
        }

        @Override
        public void onDataSetChanged() {
            int recipeId = PreferenceManager.getDefaultSharedPreferences(mContext).getInt(RECIPE_ID_KEY, 1);
            mRepository.getRecipeById(recipeId).observeForever(recipe -> mIngredients = recipe.ingredients);
        }
    }
}
