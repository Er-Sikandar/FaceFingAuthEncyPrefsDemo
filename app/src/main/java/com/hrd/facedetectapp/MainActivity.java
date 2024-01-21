package com.hrd.facedetectapp;

import androidx.annotation.NonNull;
import androidx.annotation.OptIn;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import com.hrd.facedetectapp.databinding.ActivityMainBinding;
import com.hrd.facedetectapp.utils.EncyPrefs;

import java.util.List;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private String TAG="MainActivity";
    private ActivityMainBinding binding;
    private EncyPrefs encyPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        encyPrefs=EncyPrefs.getInstance(this);
        encyPrefs.setPrefsString("Name","Sikandar Vishwakarma");
        Log.e(TAG, "onCreate: "+encyPrefs.getPrefsString("Name"));
        startActivity(new Intent(this, CallRecActivity.class));
        finish();


    }

}