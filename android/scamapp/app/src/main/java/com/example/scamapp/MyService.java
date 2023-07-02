package com.example.scamapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.google.android.material.snackbar.Snackbar;

public class MyService extends Service {

    public static final String NOTIFICATION_CHANNEL_ID = "10010" ;
    String description = "Your data has been compromised! Please reset your password.";


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent != null) {
            // Retrieve extras from the intent
            String title = intent.getStringExtra("title");

            showNotification(title, description, "nothing");
        }
        return START_STICKY;
    }

    private void showNotification(String title, String data, String pwaUrl) {


        Intent whatToDoWhenClick = new Intent(getApplicationContext(), MainActivity3.class);
        whatToDoWhenClick.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        whatToDoWhenClick.putExtra("command", title);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, whatToDoWhenClick, PendingIntent.FLAG_MUTABLE);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    "My Channel", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("My Channel Description");
            notificationManager.createNotificationChannel(channel);
        }

        // Create a notification builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(data)
                .setSmallIcon(R.drawable.security_alert)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        /*
        switch (title) {
            case "Facebook":
                builder.setSmallIcon(R.drawable.security_alert);
            case "LinkedIn":
                builder.setSmallIcon(R.drawable.security_alert);
            case "Bank":
                builder.setSmallIcon(R.drawable.security_alert);
        }
        */
        // Show the notification
        notificationManager.notify(1, builder.build());
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}