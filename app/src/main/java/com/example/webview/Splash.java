package com.example.webview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class Splash extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView = ( ImageView )findViewById(R.id.img);


        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent in = new Intent(Splash.this, MainActivity.class);
                startActivity(in);
                finish();
            }
        }, 2700);

    }

}
