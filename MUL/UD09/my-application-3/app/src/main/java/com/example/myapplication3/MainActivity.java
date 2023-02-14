package com.example.myapplication3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.start);

        startButton.setOnClickListener(startServiceClickListener);
    }

    private final View.OnClickListener startServiceClickListener = view -> {
        Intent service = new Intent(getApplicationContext(), MyService.class);
        startService(service);
    };
}