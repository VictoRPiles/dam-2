package com.example.myapplication01;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * @author victor
 */
@SuppressWarnings("deprecation")
public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}