package databit.com.br.datamobile.gcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;

import com.google.android.gms.gcm.GcmListenerService;

import databit.com.br.datamobile.activity.LoginActivity;
import databit.com.br.datamobile.R;

public class GcmIntentService extends GcmListenerService {
    @Override
    public void onMessageReceived(String from, Bundle data) {
        try {
            String title = data.getString("title");
            String message = data.getString("message");
            Intent intent = new Intent("PUSH_RECEIVED");
            intent.putExtra("title", title);
            intent.putExtra("message", message);
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
            sendNotification(title, message);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private void sendNotification(String title, String message) {
        try {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            intent.putExtra("title", title);
            intent.putExtra("message", message);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

            Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_databit_novo);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_dataservice)
                    .setLargeIcon(largeIcon)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            Notification note = notificationBuilder.build();
            note.defaults |= Notification.DEFAULT_VIBRATE;
            note.defaults |= Notification.DEFAULT_SOUND;
            note.flags |= Notification.FLAG_SHOW_LIGHTS;
            note.ledARGB = 0xff00ff00;
            note.ledOnMS = 400;
            note.ledOffMS = 10000;
            notificationManager.notify(0, note);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
