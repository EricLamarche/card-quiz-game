package com.example.CARD_QUIZ_Lamarchee1_GAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Results extends AppCompatActivity {

    private TextView txtScore;
    private TextView txtCorrect1, txtCorrect2, txtCorrect3, txtCorrect4, txtCorrect5;
    private TextView txtUserAns1, txtUserAns2, txtUserAns3, txtUserAns4, txtUserAns5;
    private Button btnFinish;
    private ArrayList<String> userAnswers;
    private ArrayList<String> answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        txtScore = findViewById(R.id.txtScore);
        txtCorrect1 = findViewById(R.id.txtCorrect1);
        txtCorrect2 = findViewById(R.id.txtCorrect2);
        txtCorrect3 = findViewById(R.id.txtCorrect3);
        txtCorrect4 = findViewById(R.id.txtCorrect4);
        txtCorrect5 = findViewById(R.id.txtCorrect5);
        txtUserAns1 = findViewById(R.id.txtUserAns1);
        txtUserAns2 = findViewById(R.id.txtUserAns2);
        txtUserAns3 = findViewById(R.id.txtUserAns3);
        txtUserAns4 = findViewById(R.id.txtUserAns4);
        txtUserAns5 = findViewById(R.id.txtUserAns5);
        btnFinish = findViewById(R.id.btnFinish);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //we have data being passed into the intent for this activity so we need to get that
        //out of the intent
        Bundle extras = getIntent().getExtras();
        //access the original intent that started this activity

        if (extras != null) {
            userAnswers = extras.getStringArrayList("userAnswers");
            answers = extras.getStringArrayList("answers");

            //Answers --> { 'A', 'B', 'C', 'D','A'}
            //indexes        0    1    2    3   4
            //to access an element in an array list use .get( *index of the element to get*)
            //dont forget array and arraylist indexes always start at 0
            txtCorrect1.setText(answers.get(0));
            txtCorrect2.setText(answers.get(1));
            txtCorrect3.setText(answers.get(2));
            txtCorrect4.setText(answers.get(3));
            txtCorrect5.setText(answers.get(4));

            txtUserAns1.setText(userAnswers.get(0));
            txtUserAns2.setText(userAnswers.get(1));
            txtUserAns3.setText(userAnswers.get(2));
            txtUserAns4.setText(userAnswers.get(3));
            txtUserAns5.setText(userAnswers.get(4));

            //calculate the score by seeing which answers are correct

            //check to see if the current answers are correct

            int score = 0;
            //repeat a certain number of times

            for (int i = 0; i < answers.size(); i++) { //this makes i go from 0 to 4
                if (userAnswers.get(i).equals(answers.get(i))) {
                    score++;
                }
            }

            double scorePercentage = score / 5.0; //why i put the ".0" - to avoid integer math
            //setText wants a string but we have a double so we need to convert
            txtScore.setText(String.valueOf(scorePercentage * 100));

            //save the score to the device
            SharedPreferences sharedPref = getSharedPreferences(Register.SHARED_PREF_FILENAME, Context.MODE_PRIVATE);
            //the last parameter is the value to set email as if there are no shared preferences yet
            String email = sharedPref.getString(Register.SHARED_PREF_EMAIL, "none");
            //save this persons scores under their email as the key
            //if there is no score there yet start the pastScores variable as blank
            String pastScores = sharedPref.getString(email, "");

            //add the current score to the past scores
            //75,85,
            pastScores += String.valueOf(scorePercentage * 100) + ",";

            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(email, pastScores);
            editor.apply();

        } else {
            Toast.makeText(this, "Error: did not receive results", Toast.LENGTH_LONG).show();
        }
    }
}