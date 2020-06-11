package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfcorrectAnswer;
    int correctAns =0 ;
    int questions =0 ;
    TextView resultView;
    TextView ScoreCard;
    TextView sumTextView;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button playAgain;
    TextView timerTextView;

    public void playAgain(View view){

        correctAns =0;
        questions =0;
        timerTextView.setText("30s");
        ScoreCard.setText("0/0");
        resultView.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        new CountDownTimer(30000,1000){


            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0s");
                playAgain.setVisibility(View.VISIBLE);
                answers.clear();
                resultView.setText(Integer.toString(correctAns) + "/"+Integer.toString(questions));
            }
        }.start();


    }


    public void generateQuestion(){
        timerTextView.setVisibility(View.VISIBLE);
        sumTextView.setVisibility(View.VISIBLE);
        ScoreCard.setVisibility(View.VISIBLE);
        Random rand = new Random();

        int num_A = rand.nextInt(21);
        int num_B = rand.nextInt(21);

        sumTextView.setText(Integer.toString(num_A) + " +" + Integer.toString(num_B));

        locationOfcorrectAnswer = rand.nextInt(4);
        int incorrectAnswer;
        for(int i=0;i<4;i++){

            if(i == locationOfcorrectAnswer){
                answers.add(num_A+num_B);
            }
            else{
                incorrectAnswer = rand.nextInt(41);
                while(incorrectAnswer == num_A+num_B){
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }
        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));

    }

    public void start(){

        startButton.setVisibility(View.INVISIBLE);
        generateQuestion();

    }
    public void chooseAnswer(View view){

        if(view.getTag().toString().equals(Integer.toString(locationOfcorrectAnswer+1))){
            correctAns++;
            resultView.setText("Correct!");
            resultView.setVisibility(View.VISIBLE);

        }
        else{
            resultView.setText("Incorrect!");



        }
        answers.clear();
        questions++;
        generateQuestion();
        ScoreCard.setText(Integer.toString(correctAns) + "/"+Integer.toString(questions));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        sumTextView = (TextView) findViewById(R.id.sumTextView);

        button1 = (Button) findViewById(R.id.guessButton1);
        button2 = (Button) findViewById(R.id.guessButton2);
        button3 = (Button) findViewById(R.id.guessButton3);
        button4 = (Button) findViewById(R.id.guessButton4);
        playAgain = (Button) findViewById(R.id.playAgainButton);

        resultView = (TextView) findViewById(R.id.resultTextView);
        ScoreCard = (TextView) findViewById(R.id.pointsTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        start();
        playAgain(findViewById(R.id.playAgainButton));
        //generateQuestion();

    }
}