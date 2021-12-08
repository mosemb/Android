package com.example.beeradviserapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickFindBeer(View view){

        TextView brands = (TextView) findViewById(R.id.brands);  // Get the id of the Textview
        Spinner color = (Spinner) findViewById(R.id.color); // Get the id of the spinner
        // Then do somethings with the TextView and Spinner
        String beertype = String.valueOf(color.getSelectedItem());
        //brands.setText(beertype);

        BeerExpert beerExpert = new BeerExpert();

        List<String> brandsList = beerExpert.getBrands(beertype);
        StringBuilder brandForm = new StringBuilder();
        for(String brand:brandsList){
            brandForm.append(brand).append("\n");
        }

        brands.setText(brandForm);

    }
}