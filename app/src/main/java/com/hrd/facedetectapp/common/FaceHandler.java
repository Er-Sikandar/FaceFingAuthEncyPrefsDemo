package com.hrd.facedetectapp.common;

import static androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK;
import static androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL;
import static androidx.core.content.ContextCompat.getMainExecutor;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.fragment.app.FragmentActivity;

public class FaceHandler {
        private static String TAG="FaceDetectHandler";

        public static void onFaceClick(Context context) {
                        getFacePromptHandler(context).authenticate(getFaceBiometricPrompt());

        }

        private static BiometricPrompt getFacePromptHandler(Context context) {
                return new BiometricPrompt((FragmentActivity) context, getMainExecutor(context),new BiometricPrompt.AuthenticationCallback() {
                        @Override
                        public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                                if (errorCode == BiometricPrompt.ERROR_HW_NOT_PRESENT) {
                                        // Handle hardware not present error
                                } else if (errorCode == BiometricPrompt.ERROR_HW_UNAVAILABLE) {
                                        // Handle hardware unavailable error
                                } else if (errorCode == BiometricPrompt.ERROR_LOCKOUT) {

                                        // Handle lockout error
                                } else if (errorCode == BiometricPrompt.ERROR_NO_BIOMETRICS) {

                                } else if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {

                                } else if (errorCode == BiometricPrompt.ERROR_LOCKOUT_PERMANENT) {

                                } else {

                                }
                                Log.e(TAG, "ErrorCode: "+errorCode);

                                super.onAuthenticationError(errorCode, errString);
                        }

                        @Override
                        public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                                super.onAuthenticationSucceeded(result);
                                Log.e(TAG, "success: "+result.getAuthenticationType());

                        }

                        @Override
                        public void onAuthenticationFailed() {
                                super.onAuthenticationFailed();

                                Log.e(TAG, "Failed");
                        }
                });

        }

        private static BiometricPrompt.PromptInfo getFaceBiometricPrompt() {
                return new BiometricPrompt.PromptInfo.Builder()
                        .setTitle("Authentication required")
                        .setSubtitle("Verify Identity")
                        .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG | BIOMETRIC_WEAK | DEVICE_CREDENTIAL)
                       // .setNegativeButtonText("Cancel")
                        .setConfirmationRequired(false)
                        .build();

        }

        }
