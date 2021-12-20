package com.hfad.sunshinelayouts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView showWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // TODO (2) Use findViewById to get a reference to the weather display TextView
        showWeather = (TextView) findViewById(R.id.tv_weather_data);

        // TODO (3) Create an array of Strings that contain fake weather data
        String [] feck_weather_data = FeckData.dataFeck();

        // TODO (4) Append each String from the fake weather data array to the TextView
        for(String data : feck_weather_data){
            showWeather.append(data +"\n\n");
        }

    }
}