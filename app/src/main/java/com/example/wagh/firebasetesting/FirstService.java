package com.example.wagh.firebasetesting;

import android.app.Notification;
import android.app.NotificationManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by wagh on 24/7/16.
 */
public class FirstService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String rec=remoteMessage.toString();


        Notification notification=new Notification.Builder(this)
                        .setContentTitle("Firebase Message")
                        .setContentText(rec)
                        .build();

        NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(0,notification);

    }
}
