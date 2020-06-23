package com.udacity.android.bakingapp.ui;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
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
        getPreferenceEditor(context).putInt(RECIPE_ID_KEY, recipeId).commit();
        getPreferenceEditor(context).putString(RECIPE_NAME_KEY, recipeName).commit();
        Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent.setComponent(new ComponentName(context, BakingWidgetProvider.class));
        context.sendBroadcast(intent);
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        if (intent.getAction().equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {
            AppWidgetManager mgr = AppWidgetManager.getInstance(context);
            RemoteViews remoteViews = buildDefaultWidget(context);
            mgr.updateAppWidget(getBakingWidgetProvider(context), remoteViews);
            mgr.notifyAppWidgetViewDataChanged(
                    mgr.getAppWidgetIds(getBakingWidgetProvider(context)),
                    R.id.widget_list_view);
        }
        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews remoteViews = buildDefaultWidget(context);
        Intent intent = new Intent(context, BakingWidgetService.class);
        remoteViews.setRemoteAdapter(R.id.widget_list_view, intent);

        Intent activityIntent = new Intent(context, StepDetailActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, activityIntent, 0);
        remoteViews.setPendingIntentTemplate(R.id.widget_list_view, pendingIntent);

        appWidgetManager.updateAppWidget(getBakingWidgetProvider(context), remoteViews);
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    private static ComponentName getBakingWidgetProvider(Context context) {
        return new ComponentName(context, BakingWidgetProvider.class);
    }

    static SharedPreferences.Editor getPreferenceEditor(Context context) {
        return  PreferenceManager.getDefaultSharedPreferences(context).edit();
    }

    private static RemoteViews buildDefaultWidget(Context context) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.baking_widget);
        String recipeName = PreferenceManager.getDefaultSharedPreferences(context).getString(RECIPE_NAME_KEY, null);
        remoteViews.setTextViewText(R.id.widget_recipe_name, recipeName);
        remoteViews.setEmptyView(R.id.widget_list_view, R.id.empty_view);

        return remoteViews;
    }
}

