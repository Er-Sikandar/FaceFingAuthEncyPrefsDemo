package com.hrd.facedetectapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class EncyPrefs {
private static EncyPrefs encyPrefs;
    private static final String PREF_NAME = "secret_shared_prefs";
    private static SharedPreferences sharedPreference;
    private static SharedPreferences.Editor editor;

    public static EncyPrefs getInstance(Context context) {
        try {
            String masterKey = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
            sharedPreference= EncryptedSharedPreferences.create(PREF_NAME, masterKey,context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
            editor=sharedPreference.edit();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (encyPrefs == null) {
            encyPrefs = new EncyPrefs();
        }
        return encyPrefs;
    }

    public void setPrefsString(String key, String value) {
        editor.putString(key, value).apply();
    }
    public String getPrefsString(String key) {
        return sharedPreference.getString(key, "");
    }



}
