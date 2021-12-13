package com.hfad.workout;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class WorkOutDetailFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // onCreateView is called when android needs to create a new fragment layout
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_work_out_detail, container, false);
    }


}