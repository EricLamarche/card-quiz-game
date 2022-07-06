package com.example.CARD_QUIZ_Lamarchee1_GAME;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button btnLogin, btnRegister;
    TextView lblDirections;

    int ACTIVITY_REGISTER_ID = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //START SPLASH SCREEN and closes itself

        Intent splash = new Intent(MainActivity.this, SplashScreen.class);
        startActivity(splash);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        lblDirections = findViewById(R.id.lblDirections);

        //check to see if we have previous log in data
        SharedPreferences sharedPref = getSharedPreferences(Register.SHARED_PREF_FILENAME, Context.MODE_PRIVATE);
        //the last parameter is the value to set email as if there are no shared preferences yet
        String email = sharedPref.getString(Register.SHARED_PREF_EMAIL, "none");


        if (email.equals("none")) {
            //if a user was loaded from shared prefs - show the email on the screen

            btnLogin.setEnabled(false);
        } else {
            String password = sharedPref.getString(Register.SHARED_PREF_PASSWORD, "none");
            String pastScores = sharedPref.getString(email, "");
            btnLogin.setEnabled(true);
            btnLogin.setText("Login to " + email);
            lblDirections.setText("Scores: " + pastScores);
        }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivityForResult(intent, ACTIVITY_REGISTER_ID);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(MainActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Quiz.class);
                startActivity(intent);
            }
        });
    }

    //send back a result
    @Override
    protected void onActivityResult(int activityID, int resultCode, @Nullable Intent data) {
        super.onActivityResult(activityID, resultCode, data);
        //which activity sent info back
        if (activityID == ACTIVITY_REGISTER_ID) {
            //also check result code for OK
            if (resultCode == RESULT_OK) {
                btnLogin.setEnabled(true);
            } else {
                //display message Registration cancelled if cancel button clicked
                Toast.makeText(MainActivity.this, "Registration was cancelled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void testWriteData() {

    }

    private void testReadData() {

    }
}