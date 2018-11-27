package com.temka.alarm;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 1500000;
    private TextView countdownText;
    private Button countdownButton;
    private Button countResetButton;

    private CountDownTimer countDownTimer;
    private long timeLeftMillseconds = START_TIME_IN_MILLIS;
    private boolean timerRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countdownText = findViewById(R.id.countdown_text);
        countdownButton = findViewById(R.id.btn_start);

        countResetButton = findViewById(R.id.btn_reset);

        countdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();

            }
        });
        countResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }



    public void reset(){
        timeLeftMillseconds =START_TIME_IN_MILLIS;
        updateTimer();
        countResetButton.setVisibility(View.INVISIBLE);
        countdownButton.setVisibility(View.VISIBLE);


    }

    public void startStop(){
        if(timerRunning){
            stopTimer();
        }else {
            startTimer();
        }
    }
    public void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftMillseconds,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftMillseconds = millisUntilFinished;
                updateTimer();

            }

            @Override
            public void onFinish() {
                timerRunning =false;
                stopTimer();
                countdownButton.setText("START");
                countResetButton.setVisibility(View.VISIBLE);
                countdownButton.setVisibility(View.INVISIBLE);



            }
        }.start();



            countdownButton.setText("PAUSE");

            timerRunning = true;
        }


    public void stopTimer()
        {
        countDownTimer.cancel();
        timerRunning = false;
        countdownButton.setText("START");
        countResetButton.setVisibility(View.VISIBLE);


    }

    public void updateTimer(){
        int minutes = (int) (timeLeftMillseconds/1000)/60;
        int seconds = (int) (timeLeftMillseconds/1000)%60;

        String timeLeftText;

        timeLeftText = "" + minutes;
        timeLeftText +=":";
        if(seconds < 10)timeLeftText += "0";
        timeLeftText+=seconds;

        countdownText.setText(timeLeftText);
    }

}



