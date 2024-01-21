package com.hrd.facedetectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.hrd.facedetectapp.databinding.ActivityCallRecBinding;
import com.hrd.facedetectapp.services.CallRecorderService;

public class CallRecActivity extends AppCompatActivity {
private String TAG="CallRecActivity";
private ActivityCallRecBinding binding;
    private Intent intentService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityCallRecBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        intentService = new Intent(getApplicationContext(), CallRecorderService.class);

        binding.btnCall.setOnClickListener(v -> {
            startService(intentService);
        });



        Log.e(TAG, "onCreate: ");

    }

    @Override
    protected void onDestroy() {
        stopService(intentService);

        super.onDestroy();
    }


}