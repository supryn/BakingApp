package com.udacity.android.bakingapp.ui;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import androidx.preference.PreferenceManager;
import android.widget.RemoteViews;

import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.ui.detail.StepDetailActivity;

/**
 * Implementation of App Widget functionality.
 */
public class BakingWidgetProvider extends AppWidgetProvider {

    static final String RECIPE_ID_KEY = "RECIPE_ID_KEY";
    static final String RECIPE_NAME_KEY = "RECIPE_NAME_KEY";


    @SuppressLint("ApplySharedPref")
    public static void sendRefreshBroadcast(Context context, int recipeId, String recipeName) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit().putInt(RECIPE_ID_KEY, recipeId).commit();
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit().putString(RECIPE_NAME_KEY, recipeName).commit();
        Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent.setComponent(new ComponentName(context, BakingWidgetProvider.class));
        context.sendBroadcast(intent);
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        if (intent.getAction().equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {
            AppWidgetManager mgr = AppWidgetManager.getInstance(context);
            ComponentName cn = new ComponentName(context, BakingWidgetProvider.class);
            mgr.notifyAppWidgetViewDataChanged(mgr.getAppWidgetIds(cn), R.id.widget_list_view);
        }
        super.onReceive(context, intent);
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        String recipeName = PreferenceManager.getDefaultSharedPreferences(context).getString(RECIPE_NAME_KEY, null);
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, recipeName);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId, String recipeName) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.baking_widget);
        String moddedRecipeName = context.getString(R.string.ui_widget_recipe_label) + recipeName;
        remoteViews.setTextViewText(R.id.widget_recipe_name, moddedRecipeName);
        remoteViews.setEmptyView(R.id.widget_list_view, R.id.empty_view);
        
        Intent intent = new Intent(context, BakingWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        remoteViews.setRemoteAdapter(R.id.widget_list_view, intent);

        Intent activityIntent = new Intent(context, StepDetailActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, activityIntent, 0);
        remoteViews.setPendingIntentTemplate(R.id.widget_list_view, pendingIntent);
        
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }
}

