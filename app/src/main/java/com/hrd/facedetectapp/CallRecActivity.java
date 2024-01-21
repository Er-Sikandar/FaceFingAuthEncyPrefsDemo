package com.hrd.facedetectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.hrd.facedetectapp.databinding.ActivityCallRecBinding;

public class CallRecActivity extends AppCompatActivity {
private String TAG="CallRecActivity";
private ActivityCallRecBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityCallRecBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.e(TAG, "onCreate: ");

    }

    @Override
    protected void onDestroy() {


        super.onDestroy();
    }
}