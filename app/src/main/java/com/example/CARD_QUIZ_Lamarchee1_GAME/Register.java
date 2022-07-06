package com.example.CARD_QUIZ_Lamarchee1_GAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    public static final String SHARED_PREF_EMAIL = "email";
    public static final String SHARED_PREF_PASSWORD = "password";
    public static final String SHARED_PREF_FILENAME = "sharedPref";

    EditText edtFirstName, edtLastName, edtBirthday, edtPassword, etdEmail;
    Button btnCancel, btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnCancel = findViewById(R.id.btnRegisterCancel);
        btnSubmit = findViewById(R.id.btnRegisterSubmit);
        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);
        edtBirthday = findViewById(R.id.edtBirthday);
        edtPassword = findViewById(R.id.edtPassword);
        etdEmail = findViewById(R.id.edtEmail);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send back page was cancelled
                Intent result = new Intent();
                setResult(RESULT_CANCELED, result);
                finish();

            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname = edtFirstName.getText().toString();
                String lastname = edtLastName.getText().toString();
                String birthday = edtBirthday.getText().toString();
                String password = edtPassword.getText().toString();
                String email = etdEmail.getText().toString();

                //check to see if any info fields are blank
                if (firstname.length() == 0 || lastname.length() == 0 || email.length() == 0 || password.length() == 0 || birthday.length() == 0) {
                    Toast.makeText(Register.this, "Error: Please fill in all fields", Toast.LENGTH_LONG).show();

                } else { //checks name length more than 2 but less than 31 characters, checks email has @ and .edu or .com and doesnt have typo like ..com
                    if (firstname.length() < 3 || firstname.length() > 30) {
                        Toast.makeText(Register.this, "Error: first name must be 3-30 characters", Toast.LENGTH_LONG).show();
                    } else if (email.contains("@") == false || (email.contains(".edu") == false && email.contains(".com") == false) || email.contains("..") == true) {
                        Toast.makeText(Register.this, "Error: email must contain @ and .com or .edu", Toast.LENGTH_SHORT).show();

                    } else {

                        //save the current user info to the shared preferences
                        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREF_FILENAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString(SHARED_PREF_EMAIL, email);
                        editor.putString(SHARED_PREF_PASSWORD, password);
                        editor.apply();


                        //send back register successful
                        Intent result = new Intent();
                        setResult(RESULT_OK, result);
                        finish();


                    }
                }
            }
        });
    }
}