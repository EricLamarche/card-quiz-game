package com.example.CARD_QUIZ_Lamarchee1_GAME.questions;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.CARD_QUIZ_Lamarchee1_GAME.R;

public class frag_MultipleSelection extends Fragment {

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
    private CheckBox chkAns1, chkAns2, chkAns3, chkAns4;

    public frag_MultipleSelection() {
        // Required empty public constructor
    }

    public static frag_MultipleSelection newInstance(String question, String ans1, String ans2, String ans3, String ans4) {
        frag_MultipleSelection fragment = new frag_MultipleSelection();
        Bundle args = new Bundle();
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
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.frag__multiple_selection, container, false);


        //grab the xml elements and assign them to their java variables
        txtQuestion = layout.findViewById(R.id.txtMS_Question);
        txtQuestion.setText(question);

        chkAns1 = layout.findViewById(R.id.radMS_Answer1);
        chkAns1.setText(ans1);
        chkAns2 = layout.findViewById(R.id.radMS_Answer2);
        chkAns2.setText(ans2);
        chkAns3 = layout.findViewById(R.id.radMS_Answer3);
        chkAns3.setText(ans3);
        chkAns4 = layout.findViewById(R.id.radMS_Answer4);
        chkAns4.setText(ans4);


        return layout;
    }

    public String getAnswer() {

        //populate a string of the answers for example
        //if A and D are selected the answer should be AD
        String ans = "";
        if (chkAns1.isChecked()) {
            ans += "A";
        }
        if (chkAns2.isChecked()) {
            ans += "B";
        }
        if (chkAns3.isChecked()) {
            ans += "C";
        }
        if (chkAns4.isChecked()) {
            ans += "D";
        }

        if (ans.length() > 0) {
            return ans;
        } else {
            return "NONE";
        }

    }
}