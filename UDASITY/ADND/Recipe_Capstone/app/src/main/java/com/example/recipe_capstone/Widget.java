package com.example.recipe_capstone;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import static com.example.recipe_capstone.RecipeDetailActivity.ingredients;


public class Widget extends AppWidgetProvider {

    private static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                        int appWidgetId) {
        CharSequence defaultWidgetText = context.getString(R.string.appwidget_text);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
        if (ingredients == null || ingredients.equalsIgnoreCase(""))
            views.setTextViewText(R.id.appwidget_text, defaultWidgetText);
        else
            views.setTextViewText(R.id.appwidget_text, ingredients);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    public static void updateWidgets(Context context, AppWidgetManager appWidgetManager, int[] ints) {
        for (int i : ints) {
            updateAppWidget(context, appWidgetManager, i);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, Widget.class));
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.add_widget);
        Widget.updateWidgets(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onDisabled(Context context) {
    }
}

