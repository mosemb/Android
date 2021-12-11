package com.hfad.starbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DrinkActivity extends AppCompatActivity {

    public static final String EXTRA_DRINKID = "drinkId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        //Get the drink from the id
        int drinkId = (Integer)getIntent().getExtras().get(EXTRA_DRINKID);
        Drinks drinks = Drinks.drinks[drinkId];

        // Populate the drink name
        TextView name = (TextView) findViewById(R.id.name);
        name.setText(drinks.getName());

        // The drink description
        TextView description = (TextView) findViewById(R.id.description);
        description.setText(drinks.getDescription());

        //Get the Image ID
        ImageView photo = (ImageView) findViewById(R.id.photo);
        photo.setImageResource(drinks.getImageResourceId());
        photo.setContentDescription(drinks.getName());
    }
}