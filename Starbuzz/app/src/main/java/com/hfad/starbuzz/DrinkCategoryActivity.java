package com.hfad.starbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DrinkCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_category);

        // The adapter shows the Drinks from the Drinks.java file, another way to bring in the data.
        ArrayAdapter<Drinks> listAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                Drinks.drinks
        );

        ListView listDrinks = (ListView) findViewById(R.id.list_drinks);
        listDrinks.setAdapter(listAdapter);

        //Onclick show the each drink detail, create a click listener
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Call in onother Activity
                Intent intent = new Intent(DrinkCategoryActivity.this,DrinkActivity.class);
                intent.putExtra(DrinkActivity.EXTRA_DRINKID,(int) id);
                startActivity(intent);
            }
        };

        listDrinks.setOnItemClickListener(itemClickListener);

    }
}