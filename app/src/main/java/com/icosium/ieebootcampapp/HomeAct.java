package com.icosium.ieebootcampapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class HomeAct extends AppCompatActivity {
    private TextView questionDisplay,scoreDisplay;
    private Button trueBtn,falseBtn;
    private ProgressBar myProgress;
    private Question [] questionsList ;
    private int score = 0;
    private int qstNb = 0, progress = 1;
    private TextView counter ;
    private long totalTime = 30000;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        initializeItems();


        questionsList = new Question[]
                {new Question("Constantine is the capitale of Algeria","False"),
                        new Question(" HTML means Hyper Transport Markup Language ","False"),
                        new Question("IOT means Internet of Things","True"),
                        new Question(" The Highest Montain is Evrest","True"),
                      
                };
        questionDisplay.setText(questionsList[0].getQuestion());
        trueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(trueBtn);
                toTheNextQuestion();

            }
        });
        falseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(falseBtn);
                toTheNextQuestion();
            }
        });
        setQstCounter();





    }
    private void setQstCounter(){
        countDownTimer.start();
    }
    private void updateProgress() {
        progress = (int) ( ((double) (qstNb+1) / (double) questionsList.length)*100);
        myProgress.setProgress(progress);
    }
    private void checkAnswer(Button button){
        if (questionsList[qstNb].getReponse().equals(button.getText().toString())){ // The user is right

            updateScore();
        }else {

        }

    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private  void toTheNextQuestion(){

        if (qstNb<questionsList.length-1){
            totalTime=30000;
            qstNb++;
            questionDisplay.setText(questionsList[qstNb].getQuestion());
            updateProgress();
            countDownTimer.cancel();
            countDownTimer.start();

        }else {

            AlertDialog.Builder mbuilder = new AlertDialog.Builder(HomeAct.this);
            mbuilder.setTitle("Game over");
            ImageView imgView = new ImageView(HomeAct.this);
            imgView.setMaxWidth(300);
            imgView.setMaxHeight(300);
            if (score >= questionsList.length/2){
                imgView.setImageDrawable(getResources().getDrawable(R.drawable.youwin));
            }else {
                imgView.setImageDrawable(getResources().getDrawable(R.drawable.youlose));
            }
            mbuilder.setView(imgView);
            mbuilder.setPositiveButton("Play again", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    qstNb =0;
                    progress = 0;
                    score = 0;
                    questionDisplay.setText(questionsList[qstNb].getQuestion());
                    updateProgress();
                    scoreDisplay.setText("Your score is : "+score);

                }
            });
            mbuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            mbuilder.show();
            countDownTimer.cancel();
        }


    }

    private void updateScore(){

        if (qstNb<questionsList.length-1){
            score++;

        }else {
            //score= 0;
        }
        scoreDisplay.setText("Your score is : "+score);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder mbuilder = new AlertDialog.Builder(HomeAct.this);
        mbuilder.setTitle("Alert Message ");
        mbuilder.setMessage(getString(R.string.exit_msg));
        mbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                HomeAct.super.onBackPressed();
            }
        });
        mbuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        mbuilder.show();


    }


    private void initializeItems(){
        questionDisplay=findViewById(R.id.question);
        scoreDisplay=findViewById(R.id.score);
        trueBtn=findViewById(R.id.trueBtn);
        falseBtn=findViewById(R.id.falseBtn);
        myProgress=findViewById(R.id.progressBar);
        counter = findViewById(R.id.counter);
        countDownTimer =new CountDownTimer(totalTime,1000){
            @Override
            public void onTick(long l) {
                // update for our text counter display
                counter.setText("You need to answer before : 00:"+l/1000);
            }

            @Override
            public void onFinish() {
                toTheNextQuestion();
                Toast.makeText(HomeAct.this, "You passed the timeout !", Toast.LENGTH_SHORT).show();
            }
        };
    }

}