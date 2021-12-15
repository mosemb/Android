package com.hfad.workout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements WorkOutListFragment.Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onShowDetails(View view){
        // Method starts when button is clicked.

        Intent intent = new Intent(this,DetailActivity.class);
        startActivity(intent);

    }

    @Override
    public void itemClicked(long id) {

        View fragmentcontainter = findViewById(R.id.fragment_container);
        if(fragmentcontainter!=null){
            WorkOutDetailFragment details = new WorkOutDetailFragment();

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            details.setWorkout_detail_id(id);
            ft.replace(R.id.fragment_container,details);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            ft.commit();

        }else{
            Intent intent = new Intent(this,DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_WORKOUT_ID, (int) id);
            startActivity(intent);

        }

    }
}