package com.example.ejerciciostres.puntouno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("ESTADO", "Estoy onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("ESTADO", "Estoy onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("ESTADO", "Estoy onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("ESTADO", "Estoy onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("ESTADO", "Estoy onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ESTADO", "Estoy onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("ESTADO", "Estoy onRestart");
    }
}