package com.example.heartratemonitor;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Math.abs;

public class MainActivity extends FragmentActivity implements SensorEventListener {
    MediaPlayer alarm;
    AudioManager audio;
    private Sensor heartRate;
    Button start_record, save_record;

    private SensorManager mSensorManager;
    private  TextView Heart;

    private TextView Status;
    private TextView hrold1,hrold2;
    private boolean permission_to_record = false;
    String Stat ="";
    String heartValue ="";
    int mHeartRate, HRold1,HRold2;
    float percent;

    int seventyVolume,currentVolume,maxVolume;

    Vibrator vibrator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        heartRate = mSensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
        // Enables Always-on
//        setAmbientEnabled();

        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        Heart = (TextView) findViewById(R.id.heart);
        start_record = findViewById(R.id.Record);
        save_record = findViewById(R.id.Save);
        Status = (TextView) findViewById(R.id.status);
        hrold1 = (TextView) findViewById(R.id.hrold1);
        hrold2 = (TextView) findViewById(R.id.hrold2);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        currentVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        percent = 0.7f;
        seventyVolume = (int) (maxVolume*percent);
        startMeasure();
        HRold1 =70;
        HRold2 =70;
        start_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_record.setBackgroundColor(Color.RED);
                save_record.setBackgroundColor(Color.DKGRAY);
                startMeasure();
                checkCondition();
                Toast.makeText(MainActivity.this,"Start Recording...", Toast.LENGTH_SHORT).show();
            }
        });

        save_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_record.setBackgroundColor(Color.DKGRAY);
                save_record.setBackgroundColor(Color.GREEN);
                permission_to_record = false;
                Toast.makeText(MainActivity.this, "File Created & Saved", Toast.LENGTH_SHORT).show();
                stopMeasure();
                vibrator.cancel();
                stopAlarm();
            }
        });
    }
    public void onSensorChanged(SensorEvent event) {

        Sensor sensor = event.sensor;
        if (sensor.getType() == Sensor.TYPE_HEART_RATE) {
            float mHeartRateFloat = event.values[0];
            mHeartRate = Math.round(mHeartRateFloat);
            Heart.setText(Integer.toString(mHeartRate));
            heartValue = Integer.toString(mHeartRate);
        }
        if (mHeartRate != 0 ){
            start_record.setEnabled(true);
        }

    }
    public void checkCondition() {

        long[] pattern = {0, 1000, 1000};

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                HRold1 = mHeartRate;
                hrold1.setText(Integer.toString(HRold1));
            }
        }, 0, 10000);
        Timer timer2 = new Timer();
        timer2.schedule(new TimerTask() {

            @Override
            public void run() {
                HRold2 = mHeartRate;
                hrold2.setText(Integer.toString(HRold2));
            }
        }, 0, 5000);


        if (abs(mHeartRate-HRold1) >50 || abs(mHeartRate-HRold2) > 50 ) {
            Status.setText("Abnormal");
            vibrator.vibrate(pattern, 0);
            alarmOn();
        }
        else{
            if (mHeartRate >= 50 && mHeartRate <= 160) {
                Status.setText("Normal");
                }
            else {
                Status.setText("Abnormal");
                vibrator.vibrate(pattern, 0);
                alarmOn();
                }
            }
    }
//}
    public void alarmOn(){
        if (alarm == null){
            alarm = MediaPlayer.create(this, R.raw.alarm_tone);
        }
        audio.setStreamVolume(AudioManager.STREAM_MUSIC, seventyVolume, 0);
        alarm.start();
    }
    private void stopAlarm(){
        if (alarm!=null){
            alarm.release();
            alarm = null;
            Toast.makeText(this,"Alarm off", Toast.LENGTH_SHORT).show();
        }
    }
    private void startMeasure() {
        boolean sensorRegistered = mSensorManager.registerListener( this, heartRate, SensorManager.SENSOR_DELAY_FASTEST);
        Log.d("Sensor Status:", " Sensor registered: " + (sensorRegistered ? "yes" : "no"));

    }

    private void stopMeasure() {
        mSensorManager.unregisterListener( this,heartRate);
    }
    protected void onResume() {
        super.onResume();
//        getSensorManager().registerListener( this, getSensorManager().getDefaultSensor(Sensor.TYPE_HEART_RATE), SensorManager.SENSOR_DELAY_FASTEST);

    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    private SensorManager getSensorManager() {
        return (SensorManager) getSystemService(SENSOR_SERVICE);
    }
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }

}
