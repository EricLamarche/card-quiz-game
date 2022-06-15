package com.example.register_lamarchee1_login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button btnLogin, btnRegister;
    //startActivityForResult()
    int ACTIVITY_REGISTER_ID = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //START SPLASH SCREEN and closes itself after 5 seconds
        Intent intent = new Intent(MainActivity.this, SplashScreen.class);
        startActivity(intent);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin.setEnabled(false);

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
                Toast.makeText(MainActivity.this, "Login success - logging you in...", Toast.LENGTH_SHORT).show();
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
                //display message Regist cancelled if cancel button clicked
                Toast.makeText(MainActivity.this, "Registration was cancelled", Toast.LENGTH_SHORT).show();
            }
        }
    }
}