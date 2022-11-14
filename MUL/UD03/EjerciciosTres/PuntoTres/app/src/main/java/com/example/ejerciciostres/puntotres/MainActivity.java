package com.example.ejerciciostres.puntotres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent example = new Intent(Intent.ACTION_VIEW);
        example.setData(Uri.parse("http://www.google.es"));
        startActivity(example);
    }
}