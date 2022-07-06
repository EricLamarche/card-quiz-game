package com.example.CARD_QUIZ_Lamarchee1_GAME;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    TextView txtAppName;
    int SHOW_TIME = 3000; //time to show splash screen in ms

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        txtAppName = findViewById(R.id.txtAppName);

        //call finish 3 seconds from now
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, SHOW_TIME);

    }
}