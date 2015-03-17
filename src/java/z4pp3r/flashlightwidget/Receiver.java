package z4pp3r.flashlightwidget;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;

public class Receiver extends BroadcastReceiver {
    static boolean isLightOn = false;
    private static Camera camera;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (isLightOn) {
            if (camera != null) {
                camera.stopPreview();
                camera.release();
                camera = null;
                isLightOn = false;
            }
        } else {
            camera = Camera.open(); //Open the default rear facing camera

            if(camera != null) {
                Camera.Parameters param = camera.getParameters();
                param.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                try {
                    camera.setParameters(param);
                    camera.startPreview();
                    isLightOn = true;
                } catch (Exception e) {}
            }
        }

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        appWidgetManager.updateAppWidget(new ComponentName(context, Main.class), Main.createWidgetView(context));
    }
}