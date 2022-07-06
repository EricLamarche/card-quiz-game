package com.example.CARD_QUIZ_Lamarchee1_GAME;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.CARD_QUIZ_Lamarchee1_GAME.questions.frag_MultipleChoice;
import com.example.CARD_QUIZ_Lamarchee1_GAME.questions.frag_MultipleSelection;

import java.util.ArrayList;

public class Quiz extends AppCompatActivity {

    private Button btnSubmit;
    //an array list is a list of items that CAN CHANGE it's size throughout the program
    private ArrayList<String> answers;
    private ArrayList<String> userAnswers;
    private ArrayList<Fragment> fragQuestions;
    private int indexOfCurrentQuestion = 0;
    private int NUMBER_OF_QUESTIONS = 5;

    //multiple selection the answer could be ABD

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //Make the first questions
        frag_MultipleChoice mc_q1 = frag_MultipleChoice.newInstance("What is the Capital of the United States?", "Washington DC", "Boston", "New York", "Los Angeles");
        frag_MultipleChoice mc_q2 = frag_MultipleChoice.newInstance("What is the Capital of Canada?", "Montreal", "Ottawa", "Winnipeg", "Quebec");
        frag_MultipleSelection ms_q3 = frag_MultipleSelection.newInstance("Which of the Following are Programming Languages?", "Java", "Lava", "Python", "Magma");
        frag_MultipleChoice mc_q4 = frag_MultipleChoice.newInstance("Who was the first American President?", "Lincoln", "Adams", "Obama", "Washington");
        frag_MultipleChoice mc_q5 = frag_MultipleChoice.newInstance("On Which Continent is Morocco Located?", "Africa", "Europe", "Asia", "North America");

        fragQuestions = new ArrayList<>();
        fragQuestions.add(mc_q1); //0
        fragQuestions.add(mc_q2); //1
        fragQuestions.add(ms_q3); //2
        fragQuestions.add(mc_q4); //3
        fragQuestions.add(mc_q5); //4

        //setup the actual answers to the questions
        answers = new ArrayList<>();
        answers.add("A");
        answers.add("B");
        answers.add("AC");
        answers.add("D");
        answers.add("A");

        //store the answers for each question
        userAnswers = new ArrayList<>();

        getSupportFragmentManager().beginTransaction().add(R.id.fragMainContainer, fragQuestions.get(indexOfCurrentQuestion)).addToBackStack("start").commit();

        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Quiz.this);
                // Set Alert Title
                //  builder.setTitle("Alert !");
                // Set the message show for the Alert time
                builder.setMessage("Are you sure you want to submit this answer?");
                // Set Cancelable false
                // for when the user clicks on the outside
                // the Dialog Box then it will remain show
                builder.setCancelable(false); //user MUST press a button to close the dialog

                // Set the positive button with yes name
                // OnClickListener method is use of
                // DialogInterface interface.
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        submitAnswer();
                        dialog.dismiss();
                    }
                });

                // Set the Negative button with No name
                // OnClickListener method is use
                // of DialogInterface interface.
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {                        // If user click no
                        // then dialog box is canceled.
                        dialog.cancel();
                    }
                });

                // Create the Alert dialog
                AlertDialog alertDialog = builder.create();

                // Show the Alert Dialog box
                alertDialog.show();
            }
        });

    }

    private void submitAnswer() {
        //get the answer from the fragment

        Fragment curFragment = fragQuestions.get(indexOfCurrentQuestion);
        String answer;
        if (curFragment instanceof frag_MultipleChoice) {
            //get the fragment from the list, but we have to cast it as multiple choice
            //because Fragment by itself does not have getAnswer Implemented
            answer = ((frag_MultipleChoice) fragQuestions.get(indexOfCurrentQuestion)).getAnswer();
        } else {
            answer = ((frag_MultipleSelection) fragQuestions.get(indexOfCurrentQuestion)).getAnswer();
        }

        //NONE
        //if the user did not select something show a toast and dont move on to the next question
        if (answer.equals("NONE")) {
            Toast.makeText(Quiz.this, "Please choose an answer before continuing", Toast.LENGTH_SHORT).show();
        } else {
            //store the answer
            userAnswers.add(answer);

            //see if we should go to the next question or if the quiz is over and we show the end
            indexOfCurrentQuestion++;
            //4 --> 5
            if (indexOfCurrentQuestion == NUMBER_OF_QUESTIONS) {
                int stop = 0;
                //go to the finish screen to show the results
                finish();
                //show the results activity

                Intent results = new Intent(Quiz.this, Results.class);
                //add information into the intent so I can access it on the results page
                //send the userAnswers, and the answers to the results activity

                //for example, lets store the number 5 as the value with the key "num_questions"
                //results.putExtra("num_questions", 5);
                results.putStringArrayListExtra("userAnswers", userAnswers);
                results.putStringArrayListExtra("answers", answers);
                startActivity(results);
            } else {
                //screen
                Fragment nextFragment = fragQuestions.get(indexOfCurrentQuestion);
                //go to the next question
                getSupportFragmentManager().beginTransaction().add(R.id.fragMainContainer, nextFragment).addToBackStack(null).commit();
            }
        }
    }
}