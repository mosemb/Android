package com.hfad.workout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_WORKOUT_ID = "id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        WorkOutDetailFragment fragment = (WorkOutDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_frag);
       // fragment.setWorkout_detail_id(0);
        int workout_id = (int) getIntent().getExtras().get(EXTRA_WORKOUT_ID);
        fragment.setWorkout_detail_id(workout_id);

    }

}
