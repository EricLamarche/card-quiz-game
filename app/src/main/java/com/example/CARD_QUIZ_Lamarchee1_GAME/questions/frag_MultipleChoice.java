package com.example.CARD_QUIZ_Lamarchee1_GAME.questions;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.CARD_QUIZ_Lamarchee1_GAME.R;


public class frag_MultipleChoice extends Fragment {


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "question";
    private static final String ARG_PARAM2 = "ans1";
    private static final String ARG_PARAM3 = "ans2";
    private static final String ARG_PARAM4 = "ans3";
    private static final String ARG_PARAM5 = "ans4";


    private String question;
    private String ans1;
    private String ans2;
    private String ans3;
    private String ans4;

    //GUI XMl elements
    private TextView txtQuestion;
    private RadioGroup radGrpAnswers;
    private RadioButton radAns1, radAns2, radAns3, radAns4;

    public frag_MultipleChoice() {
        // Required empty public constructor
    }


    public static frag_MultipleChoice newInstance(String question, String ans1, String ans2, String ans3, String ans4) {
        frag_MultipleChoice fragment = new frag_MultipleChoice();
        Bundle args = new Bundle();
        //items are stored in a bundle in key-value pairs
        //key-value pair - some type of key that represents a value

        args.putString(ARG_PARAM1, question); //we mapped the word "question" to the actual question string
        args.putString(ARG_PARAM2, ans1);
        args.putString(ARG_PARAM3, ans2);
        args.putString(ARG_PARAM4, ans3);
        args.putString(ARG_PARAM5, ans4);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //we passed in information into this fragment so we need to pull that information out
        //when the fragment is actually shown on the screen
        if (getArguments() != null) {
            question = getArguments().getString(ARG_PARAM1);
            ans1 = getArguments().getString(ARG_PARAM2);
            ans2 = getArguments().getString(ARG_PARAM3);
            ans3 = getArguments().getString(ARG_PARAM4);
            ans4 = getArguments().getString(ARG_PARAM5);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //this creates and inflates the view but we need to access some of the xml elements
        //on this fragment so we need to store this view inflation in a view variable
        View layout = inflater.inflate(R.layout.fragment_multiple_choice, container, false);

        //grab the xml elements and assign them to their java variables
        txtQuestion = layout.findViewById(R.id.txtMC_Question);
        txtQuestion.setText(question);

        radAns1 = layout.findViewById(R.id.radMC_Answer1);
        radAns1.setText(ans1);
        radAns2 = layout.findViewById(R.id.radMC_Answer2);
        radAns2.setText(ans2);
        radAns3 = layout.findViewById(R.id.radMC_Answer3);
        radAns3.setText(ans3);
        radAns4 = layout.findViewById(R.id.radMC_Answer4);
        radAns4.setText(ans4);


        radGrpAnswers = layout.findViewById(R.id.radGrpAnswers);
        //the radio group will listen for a change in selection

        return layout;
    }

    public String getAnswer() {

        int idOfCheckedRadioButton = radGrpAnswers.getCheckedRadioButtonId();
        if (idOfCheckedRadioButton == R.id.radMC_Answer1) {
            //this means rad 1 is checked
            return "A";
        } else if (idOfCheckedRadioButton == R.id.radMC_Answer2) {
            return "B";
        } else if (idOfCheckedRadioButton == R.id.radMC_Answer3) {
            return "C";
        } else if (idOfCheckedRadioButton == R.id.radMC_Answer4) {
            return "D";
        } else {
            return "NONE";
        }

    }
}