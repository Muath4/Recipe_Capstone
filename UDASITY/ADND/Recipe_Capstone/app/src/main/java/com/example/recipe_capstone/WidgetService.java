package com.example.recipe_capstone;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;

public class WidgetService extends IntentService {

    private static final String UPDATE_WIDGETS = "updateTheWidget";

    public WidgetService() {
        super("WidgetService");
    }

    public static void updateWidget(Context context) {
        Intent intent = new Intent(context, WidgetService.class);
        intent.setAction(UPDATE_WIDGETS);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        setUpdate();
    }

    private void setUpdate() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, Widget.class));
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.add_widget);
        Widget.updateWidgets(this, appWidgetManager, appWidgetIds);
    }
}
