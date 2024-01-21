package com.hrd.facedetectapp.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import androidx.annotation.Nullable;

import java.io.File;


public class CallRecorderService extends Service {
    private MediaRecorder mRecorder;
    private boolean isRecording = false;
    private PhoneStateListener phoneStateListener = new PhoneStateListener() {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    stopRecording();
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    startRecording(incomingNumber);
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    private void startRecording(String number) {
        try {
            String savePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            savePath += "/SikRecorded";
            File file = new File(savePath);
            if (!file.exists()) {
                file.mkdir();
            }
            savePath += "/record_" + System.currentTimeMillis() + ".amr";
            mRecorder = new MediaRecorder();
           /* SharedPreferences sPrefs = getSharedPreferences(Constants.PREFERENCES_NAME, Context.MODE_PRIVATE);
            int inputSource = sPrefs.getInt(Constants.SOURCE_INPUT, Constants.SOURCE_VOICE_CALL);
            int outputSource = sPrefs.getInt(Constants.SOURCE_OUTPUT, Constants.OUTPUT_MPEG4);
            switch (inputSource) {
                case Constants.SOURCE_MIC:
                    increaseSpeakerVolume();
                    break;
            }*/
           /* mRecorder.setAudioSource(inputSource);
            mRecorder.setOutputFormat(outputSource);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mRecorder.setOutputFile(savePath);
            mRecorder.prepare();
            mRecorder.start();
            isRecording = true;*/
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void stopRecording(){
        if (isRecording) {
            isRecording = false;
            mRecorder.stop();
            mRecorder.release();
        }
    }
    private void increaseSpeakerVolume(){
        AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audio.adjustStreamVolume(AudioManager.STREAM_VOICE_CALL,
                AudioManager.ADJUST_RAISE,
                AudioManager.FLAG_SHOW_UI);
    }

}
