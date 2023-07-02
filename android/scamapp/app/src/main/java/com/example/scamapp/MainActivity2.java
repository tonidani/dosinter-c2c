package com.example.scamapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Uri data = intent.getData();
        String command = getCommandFromPath(data.getQuery());
        setContentView(R.layout.activity_main3);
        if (!command.equals("")) {
            if (command != "success") {
                Intent service = new Intent(getApplicationContext(), MyService.class);
                service.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                service.putExtra("title", command);
                startService(service);
            }  else if (command.equals("success")) {
                Intent activity4 = new Intent(getApplicationContext(), MainActivity4.class);
                startActivity(activity4);

            }

        }

    }


    public String getCommandFromPath(String input){
        String command = "";
        int indexOfEquals = input.indexOf("=");

        if (indexOfEquals >= 0 && indexOfEquals < input.length() - 1) {
            command = input.substring(indexOfEquals + 1);
        }
        return command;

    }
    public void openDialog() {
        com.example.application.example.ExampleDialog exampleDialog = new com.example.application.example.ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

}