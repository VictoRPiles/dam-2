package com.example.practica00;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView message;
    private EditText user;
    private Button next, exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* initializing layout elements */
        message = findViewById(R.id.textViewGreeting);
        user = findViewById(R.id.editTextUser);
        next = findViewById(R.id.buttonNext);
        exit = findViewById(R.id.buttonExit);
    }

    public void next(View button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String greeting = String.format("Bienvenido a nuestra App %s", user.getText());
                message.setText(greeting);
            }
        });
    }

    public void exit(View button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}