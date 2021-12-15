package com.hfad.workout;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class WorkOutDetailFragment extends Fragment {
    private long workout_detail_id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // onCreateView is called when android needs to create a new fragment layout
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_work_out_detail, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState==null){
           // workout_detail_id = savedInstanceState.getLong("workout_detail_id");
            StopwatchFragment stopwatch = new StopwatchFragment();
            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
            ft.add(R.id.stopwatch_container, stopwatch);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }else{

            workout_detail_id = savedInstanceState.getLong("workout_detail_id");

        }
    }

    @Override
    public void onStart() {
        View view = getView();
        if(view!=null){
            TextView title = (TextView) view.findViewById(R.id.textTitle);
            Workout workout = Workout.workouts[(int) workout_detail_id];
            title.setText(workout.getName());
            TextView description = (TextView) view.findViewById(R.id.textDescription);
            description.setText(workout.getDescription());
        }

        super.onStart();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putLong("workout_detail_id",workout_detail_id);
    }


    public void setWorkout_detail_id(long workout_detail_id){
        this.workout_detail_id = workout_detail_id;

    }


}