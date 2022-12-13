package com.example.myapplication06;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	private RadioButton radioLunes, radioMartes, radioMiercoles, radioJueves, radioViernes, radioSabado, radioDomingo;
	private TextView message;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		message = findViewById(R.id.message);

		/* ========== Radio buttons ========== */
		radioLunes = findViewById(R.id.radioLunes);
		radioLunes.setOnClickListener(buttonClickListener);

		radioMartes = findViewById(R.id.radioMartes);
		radioMartes.setOnClickListener(buttonClickListener);

		radioMiercoles = findViewById(R.id.radioMiercoles);
		radioMiercoles.setOnClickListener(buttonClickListener);

		radioJueves = findViewById(R.id.radioJueves);
		radioJueves.setOnClickListener(buttonClickListener);

		radioViernes = findViewById(R.id.radioViernes);
		radioViernes.setOnClickListener(buttonClickListener);

		radioSabado = findViewById(R.id.radioSabado);
		radioSabado.setOnClickListener(buttonClickListener);

		radioDomingo = findViewById(R.id.radioDomingo);
		radioDomingo.setOnClickListener(buttonClickListener);
	}

	private final View.OnClickListener buttonClickListener = new View.OnClickListener() {
		@SuppressLint("SetTextI18n")
		@Override
		public void onClick(@NonNull View view) {
			// Desmarcamos todos
			radioLunes.setChecked(false);
			radioMartes.setChecked(false);
			radioMiercoles.setChecked(false);
			radioJueves.setChecked(false);
			radioViernes.setChecked(false);
			radioSabado.setChecked(false);
			radioDomingo.setChecked(false);

			// Marcamos el que se ha pulsado
			RadioButton thisRadioButton = (RadioButton) view;
			thisRadioButton.setChecked(true);

			// Cambiamos el mensaje
			message.setText("Pulsado: " + thisRadioButton.getText());
		}
	};
}