package com.bw.activity.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bw.activity.R;

/**
 * @name APP
 * @class name：com.bw.activity.fragment
 * @class describe
 * @anthor 24673
 * @time 2019/11/19 13:57
 * @change
 * @chang time
 * @class describe
 */
public class One_Fragment extends Fragment {
    private static final String TAG = "MainActivity";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.one_faragment, container, false);
        Log.i(TAG, "One_Fragment:onCreateView: ");
        return view;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i(TAG, "One_Fragment:onAttach: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "One_Fragment:onCreate: ");
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "One_Fragment:onActivityCreated: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "One_Fragment:onStart: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "One_Fragment:onDetach: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "One_Fragment:onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "One_Fragment:onPause: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "One_Fragment:onDestroyView: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "One_Fragment:onStop: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "One_Fragment:onDestroy: ");
    }
}
