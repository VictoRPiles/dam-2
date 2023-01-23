package com.example.myapplication02;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private final View.OnClickListener buttonDefinePreferencesClickListener = view -> {
        Log.d("DEBUG", "onClick: buttonDefinePreferences");
        definePreferences();
    };
    private final View.OnClickListener buttonRecoverPreferencesClickListener = view -> {
        Log.d("DEBUG", "onClick: buttonRecoverPreferences");
        recoverPreferences();
    };

    @SuppressWarnings("FieldCanBeLocal")
    private Button buttonDefinePreferences, buttonRecoverPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonDefinePreferences = findViewById(R.id.buttonDefine);
        buttonRecoverPreferences = findViewById(R.id.buttonRecover);

        buttonDefinePreferences.setOnClickListener(buttonDefinePreferencesClickListener);
        buttonRecoverPreferences.setOnClickListener(buttonRecoverPreferencesClickListener);
    }

    private void definePreferences() {
        Log.d("DEBUG", "definePreferences: Start settings activity");
        /* Start settings activity */
        Intent intent = new Intent(getBaseContext(), SettingsActivity.class);
        startActivity(intent);
    }

    @SuppressWarnings("deprecation")
    private void recoverPreferences() {
        Log.d("DEBUG", "recoverPreferences: Recover preferences");
        /* Get the preferences */
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        boolean singleOperatingSystemPreference = pref.getBoolean("singleOperatingSystem", false);
        String osPreference = pref.getString("os", "Linux");
        String versionPreference = pref.getString("version", "69");
        String msg = "\t- singleOperatingSystemPreference: " + singleOperatingSystemPreference + "\n" +
                "\t- osPreference: " + osPreference + "\n" +
                "\t- versionPreference: " + versionPreference;

        /* Recover the preferences via Toast */
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        /* Recover the preferences via Log */
        Log.d("DEBUG", "recoverPreferences() returned: " + "\n" + msg);
    }
}