package com.bw.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        text = findViewById(R.id.text);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Log.i(TAG, "Main2Activity:onCreate: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "Main2Activity:onRestart: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "Main2Activity:onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Main2Activity:onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "Main2Activity:onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "Main2Activity:onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Main2Activity:onDestroy: ");
    }

}
