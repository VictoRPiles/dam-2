package com.example.myapplication01;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Crea una actividad que tenga en la pantalla principal dos botones: el superior, que muestra una interfaz de
 * preferencias, con las opciones que se muestran en la imagen; y el inferior, que recupere los valores introducidos y
 * los muestre mediante log en el entorno de desarrollo.
 */
public class MainActivity extends AppCompatActivity {
    private TextView textTitle;
    private Button buttonDefinePreferences, buttonRecoverPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textTitle = findViewById(R.id.textTitle);
        buttonDefinePreferences = findViewById(R.id.buttonDefine);
        buttonRecoverPreferences = findViewById(R.id.buttonRecover);


    }
}