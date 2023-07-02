package com.example.scamapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //Do Notification
        Intent serviceIntent = new Intent(context, MyService.class);
        context.startService(serviceIntent);
/*
        String count = intent.getStringExtra("count");
        String tempData = intent.getStringExtra("data");
        saveToCache(context.getApplicationContext(),
                tempData.replaceAll("\n", "")
        );
        //String srcData = "data:image/jpeg;base64," + data;
        makeToast(context, "SCAMPP GETTING DATA");
        if(Integer.valueOf(count) == 1){
            //NOTIFICATION
            //Intent service = new Intent(context.getApplicationContext(), MyService.class);
            //context.startService(service);

            Intent chromeintent = new Intent(context.getApplicationContext(), MainActivity2.class);
            //chromeintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            //chromeintent.putExtra("data", srcData);
            //context.startActivity(chromeintent);
        }

*/
    }

    public void saveToCache(Context context, String Base64data){
        String fileName = "photo_cached.txt";
        File cacheDir = context.getCacheDir();
        Log.d("Cache dir", String.valueOf(cacheDir));


        try {
            File file = new File(cacheDir, fileName);

            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);

            outputStreamWriter.write(Base64data);
            outputStreamWriter.flush();
            outputStreamWriter.close();

            Log.d("Cache Example", "Dane zostały zapisane w pamięci podręcznej.");
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public void makeToast(Context context, String message) {
        Handler handler = new Handler(Looper.getMainLooper());

        handler.post(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        });
    }

}
