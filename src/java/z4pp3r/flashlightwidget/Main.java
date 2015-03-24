package z4pp3r.flashlightwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class Main extends AppWidgetProvider {

    static RemoteViews createWidgetView(Context context) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.main);

        if(Receiver.isLightOn) {
            views.setTextViewText(R.id.button, "☼ on");
        } else {
            views.setTextViewText(R.id.button, "☼ off");
        }

        Intent receiver = new Intent(context, Receiver.class);
        receiver.setAction("z4pp3r.flashlightwidget.FLASHLIGHT");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, receiver, 0);
        views.setOnClickPendingIntent(R.id.button, pendingIntent);

        return views;
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews views = createWidgetView(context);
        appWidgetManager.updateAppWidget(appWidgetIds, views);
    }

}