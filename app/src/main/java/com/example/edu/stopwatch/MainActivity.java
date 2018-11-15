package com.example.edu.stopwatch;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView textViewTime;
    Button buttonStart, buttonPause, buttonReset;

    long startTime, pauseTime;
    long millisecondTime=0, updateTime=0, timeBuff=0;
    Handler handler = new Handler();
    SimpleDateFormat formatter = new SimpleDateFormat("mm:ss:SSS");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTime = findViewById(R.id.textViewTime);
        buttonStart = findViewById(R.id.buttonStart);
        buttonPause = findViewById(R.id.buttonPause);
        buttonReset = findViewById(R.id.buttonReset);

        buttonStart.setOnClickListener(this);
        buttonPause.setOnClickListener(this);
        buttonReset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.buttonStart:
                buttonStart();
                break;
            case R.id.buttonPause:
                buttonPause();
                break;
            case R.id.buttonReset:
                buttonReset();
                break;

        }
    }

    public void buttonStart(){
        startTime = SystemClock.uptimeMillis();
        handler.postDelayed(runnable, 0);
        //handler.post();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int seconds, minutes, milliSeconds;
//                millisecondTime = SystemClock.uptimeMillis() - startTime;
//                updateTime = timeBuff + millisecondTime;
//                seconds = (int) (updateTime / 1000);
//                minutes = seconds / 60; seconds = seconds % 60;
//                milliSeconds = (int) (updateTime % 1000);
//                textViewTime.setText(minutes + ":" + String.format("%02d", seconds) + ":"
//                        + String.format("%03d", milliSeconds));
//            }
//
//        }).start();
    }
    public void buttonPause(){
        timeBuff += millisecondTime;
        handler.removeCallbacks(runnable);

    }
    public void buttonReset(){
        startTime = 0;
        textViewTime.setText("00:00:00");
    }

    public Runnable runnable = new Runnable() {
        public void run() {
//            int seconds, minutes, milliSeconds;
            millisecondTime = SystemClock.uptimeMillis() - startTime;
            updateTime = timeBuff + millisecondTime;
//            seconds = (int) (updateTime / 1000);
//            minutes = seconds / 60; seconds = seconds % 60;
//            milliSeconds = (int) (updateTime % 1000);
//            textViewTime.setText(minutes + ":" + String.format("%02d", seconds) + ":"
//                    + String.format("%03d", milliSeconds));

            textViewTime.setText(formatter.format(updateTime));

            handler.postDelayed(this, 0);
        }
    };


}
