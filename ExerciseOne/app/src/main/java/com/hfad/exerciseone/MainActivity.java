package com.hfad.exerciseone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // TODO (1) Declare a TextView variable called mToysListTextView
    TextView mToysListTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO (3) Use findViewById to get a reference to the TextView from the layout
        mToysListTextView = (TextView) findViewById(R.id.tv_toy_games);

        // TODO (4) Use the static ToyBox.getToyNames method and store the names in a String array
        String [] names = ToyBox.getToyNames();

        // TODO (5) Loop through each toy and append the name to the TextView (add \n for spacing)
        for( String toy:names){
            mToysListTextView.append(toy + "\n");
        };

    }
}