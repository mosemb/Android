package com.hfad.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int seconds;
    private boolean running;
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null){  // Saves the state to the device even if its rotated. It continues to run
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");

        }
        runTimer();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {  // Saves the state of the device even if its rotated.
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning",wasRunning);
    }


    /*
    @Override
    public void onStop() {
        super.onStop();
        wasRunning = running;
        running = false;
    }


    @Override
    public void onStart() {

        super.onStart();
        if(wasRunning){
            running = true;
        }
       // wasRunning = running;
    }

     */
    @Override
    public void onPause() {

        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    public void onResume() {

        super.onResume();
        if(wasRunning){

            running = true;
        }
    }




    public void onClickStart(View view){
        running = true;
    }

    public void onClickStop(View view){
        running = false;
    }

    public void onClickReset(View view){
        running = false;
        seconds = 0;
    }


    public void runTimer(){
        /*
        Method to update timer
        * */
        final TextView textView = (TextView) findViewById(R.id.text_view); // Get the text view
        final Handler handler = new Handler(); // We use the handler to run code that is supposed to run in the future

        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;

                String times = String.format(Locale.getDefault(),"%d:%02d:%02d",hours,minutes,secs);
                textView.setText(times);

                if(running==true){
                    seconds = seconds+1;
                }
                handler.postDelayed(this,1000);
            }
        });

    }
}