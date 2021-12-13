package com.hfad.workout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        WorkOutDetailFragment fragment = (WorkOutDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_frag);
        fragment.setWorkout_detail_id(0);
    }


}
