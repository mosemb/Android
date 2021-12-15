package com.hfad.workout;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;


public class StopwatchFragment extends Fragment implements View.OnClickListener {

    // Number of seconds displayed on the stop watch
    private int seconds = 0;

    // Is the stop watch running
    private boolean running;
    // was the stop watch running
    private boolean wasRunning;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        if(savedInstance!=null){
            seconds = savedInstance.getInt("seconds");
            running = savedInstance.getBoolean("running");
            wasRunning = savedInstance.getBoolean("wasRunning");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        View layout = inflater.inflate(R.layout.fragment_stopwatch, container,false);
        runTimer(layout);
        Button startButton = (Button) layout.findViewById(R.id.start_button);
        startButton.setOnClickListener(this);

        Button stopButton = (Button) layout.findViewById(R.id.stop_button);
        stopButton.setOnClickListener(this);

        Button resetButton = (Button) layout.findViewById(R.id.reset);
        resetButton.setOnClickListener(this);


        return layout;

    }

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

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running",running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }


    public void onClickReset(){
        running = false;
        seconds = 0;
    }

    private void runTimer(View view){
        final TextView timeview = (TextView) view.findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;

                String time = String.format(Locale.getDefault(),"%d:%02d:%02d",hours,minutes,secs);
                timeview.setText(time);

                if(running){
                    seconds++;
                }

                handler.postDelayed(this,1000);
            }
        });

    }

    private void onClickStart(){
        running = true;
    }

    private void onClickStop(){
        running = false;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_button:
                onClickStart();
                break;
            case R.id.stop_button:
                onClickStop();
                break;

            case R.id.reset:
                onClickReset();
                break;

        }

    }
}