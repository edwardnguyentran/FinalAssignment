package com.example.edwardnguyentran.assignmented;


import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class Quiz extends Fragment {
    private static final long COUNTDOWN_MILLIS = 30000;


    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCountDown;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonConfirmNext;

    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;

    private CountDownTimer countDownTimer;
    private long timeLeftMillis;

    private List <Question> questionList;

    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;


    public Quiz() {
        // Required empty public constructor
    }

    //Creatse view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quiz, container, false);

        textViewQuestion = v.findViewById(R.id.text_view_question);
        textViewScore = v.findViewById(R.id.text_view_score);
        textViewCountDown = v.findViewById(R.id.text_view_countdown);
        textViewQuestionCount = v.findViewById(R.id.text_view_question_count);
        rbGroup = v.findViewById(R.id.radio_group);
        rb1 = v.findViewById(R.id.radio_button1);
        rb2 = v.findViewById(R.id.radio_button2);
        rb3 = v.findViewById(R.id.radio_button3);
        rb4 = v.findViewById(R.id.radio_button4);
        buttonConfirmNext= v.findViewById((R.id.button_confirm));

        //Changes colour for buttons and timer
        textColorDefaultRb = rb1.getTextColors();
        textColorDefaultCd = textViewCountDown.getTextColors();

        //Accessing database to retrieve question
        QuizDbHelper dbHelper = new QuizDbHelper(getActivity());
        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();

        //Check answer
        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {

                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()){
                        checkAnswer();
                    }else {
                        Toast.makeText(getActivity(), "Please select something", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    showNextQuestion();
                }

            }
        });

        return v;

    }

    //Go to next question
    private void showNextQuestion(){
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rb4.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();

        if (questionCounter < questionCountTotal){

            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());

//score at the top of the app

            questionCounter ++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            buttonConfirmNext.setText("Confirm");

            timeLeftMillis = COUNTDOWN_MILLIS;
            startCountDown();

        }else {
            finishQuiz();
        }


    }

    //Method for timer countdown
    private void startCountDown(){
        countDownTimer = new CountDownTimer(timeLeftMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftMillis = millisUntilFinished;
                updateCountDownText();

            }

            @Override
            public void onFinish() {

                timeLeftMillis =0;
                updateCountDownText();
                checkAnswer();

            }
        }.start();
    }

    //Updates the timer as time passes
    private void updateCountDownText(){
        int minutes = (int)(timeLeftMillis/1000)/60;
        int seconds = (int) (timeLeftMillis/1000)%60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        textViewCountDown.setText(timeFormatted);

        if (timeLeftMillis <10000){
            textViewCountDown.setTextColor(Color.RED);
        }else {
            textViewCountDown.setTextColor(textColorDefaultCd);
        }

    }

    //Method for checking answer and updates score
    private void checkAnswer(){

        answered =true;

        countDownTimer.cancel();

        RadioButton rbSelected = getView().findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;

        if (answerNr == currentQuestion.getAnswerNr()){
            score++;
           textViewScore.setText("Score: "+ score);
        }

        showSolution();

    }

    //Shows solution on confirm
    private void showSolution(){
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNr()){
            case 1:
                rb1.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer A is correct");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer B is correct");
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer C is correct");
                break;
            case 4:
                rb4.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer D is correct");
                break;
        }

        if (questionCounter < questionCountTotal){
            buttonConfirmNext.setText("Next");
        }else {

            buttonConfirmNext.setText("Complete");
        }
    }

    //This part makes it go to home screen after quiz is finish
    private void finishQuiz(){
        FragmentTransaction fr=getFragmentManager().beginTransaction();
        fr.replace(R.id.fragment,new QuizFeedback());
        getActivity().setTitle("Quiz Feedback");
        fr.commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (countDownTimer!= null){
            countDownTimer.cancel();
        }
    }

    //

}
