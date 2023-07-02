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

public class MainActivity4 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

                setContentView(R.layout.activity_main_background);
                openDialog();
            }

    public void openDialog() {
        com.example.application.example.ExampleDialog exampleDialog = new com.example.application.example.ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(), "Error");
    }
}