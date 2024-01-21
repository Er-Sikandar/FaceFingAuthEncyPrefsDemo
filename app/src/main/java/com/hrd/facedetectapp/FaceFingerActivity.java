package com.hrd.facedetectapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.hrd.facedetectapp.common.FaceHandler;
import com.hrd.facedetectapp.common.FingerHandler;
import com.hrd.facedetectapp.databinding.ActivityFaceFingerBinding;
import com.hrd.facedetectapp.databinding.SelectYesAndNoDialogBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class FaceFingerActivity extends AppCompatActivity {
    private String TAG="FaceFingerActivity";
    private ActivityFaceFingerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityFaceFingerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


      binding.btnFinger.setOnClickListener(v -> {
          BiometricManager biometricManager = BiometricManager.from(this);
          if (biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE) {
              Toast.makeText(this, "Hardware Not Available", Toast.LENGTH_SHORT).show();

          } else if (biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE) {
              Toast.makeText(this, "Hardware Error Available", Toast.LENGTH_SHORT).show();
          } else if (biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED) {
              privacyProtectionAlertDialog();
          } else if (biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS) {
              FingerHandler.onTouchIdClick(this);

          }
          Log.e(TAG, "Finger Values: "+biometricManager.canAuthenticate());
      });

      binding.btnFace.setOnClickListener(v -> {
          BiometricManager biometricManager = BiometricManager.from(this);
          if (biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE) {
              Toast.makeText(this, "Hardware Not Available", Toast.LENGTH_SHORT).show();

          } else if (biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE) {
              Toast.makeText(this, "Hardware Error Available", Toast.LENGTH_SHORT).show();

          } else if (biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED) {
              privacyProtectionAlertDialog();
          } else if (biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS) {
              FaceHandler.onFaceClick(this);

          }
          Log.e(TAG, "Finger Values: "+biometricManager.canAuthenticate());
      });


      binding.btnDep.setOnClickListener(v -> {



      });

    }


    private void privacyProtectionAlertDialog() {
        AlertDialog.Builder alertBuild = new AlertDialog.Builder(this);
        SelectYesAndNoDialogBinding dialogView = SelectYesAndNoDialogBinding.inflate(getLayoutInflater());
        alertBuild.setView(dialogView.getRoot());
        alertBuild.setCancelable(false);
        dialogView.tvYes.setText("Go TO SETTINGS");
        dialogView.tvNo.setText("NOT NOW");
        dialogView.tvMsg.setText(getResources().getString(R.string.lock_enable_msg));
        final AlertDialog dialog = alertBuild.create();
        dialogView.tvYes.setOnClickListener(v -> {
            startActivity(new Intent(Settings.ACTION_SETTINGS));
            dialog.dismiss();
        });
        dialogView.tvNo.setOnClickListener(v -> {

            dialog.dismiss();
        });
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}