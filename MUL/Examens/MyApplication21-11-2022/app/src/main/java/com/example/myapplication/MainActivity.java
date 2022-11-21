package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	private TextView progressRed, progressGreen, progressBlue, miColor;
	private Button miBtn;
	private SeekBar barRed, barGreen, barBlue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		progressRed = findViewById(R.id.progressRed);
		progressGreen = findViewById(R.id.progressGreen);
		progressBlue = findViewById(R.id.progressBlue);
		miColor = findViewById(R.id.miColor);
		miBtn = findViewById(R.id.miBoton);

		/* Stocky Font */
		Typeface stocky = ResourcesCompat.getFont(this, R.font.stocky);
		miColor.setTypeface(stocky);

		barRed = findViewById(R.id.sbRed);
		barGreen = findViewById(R.id.sbGreen);
		barBlue = findViewById(R.id.sbBlue);

		barRed.setOnSeekBarChangeListener(getBarChangeListener());
		barGreen.setOnSeekBarChangeListener(getBarChangeListener());
		barBlue.setOnSeekBarChangeListener(getBarChangeListener());

		miBtn.setOnClickListener(miBtnListener);
	}

	/* ========== LISTENERS ========== */

	@NonNull
	private SeekBar.OnSeekBarChangeListener getBarChangeListener() {
		return new SeekBar.OnSeekBarChangeListener() {
			@SuppressLint("DefaultLocale")
			@Override
			public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
				/* Red */
				if (barRed.equals(seekBar)) {
					progressRed.setText(String.valueOf(i));
				}
				/* Green */
				else if (barGreen.equals(seekBar)) {
					progressGreen.setText(String.valueOf(i));
				}
				/* Blue */
				else if (barBlue.equals(seekBar)) {
					progressBlue.setText(String.valueOf(i));
				}
				int rgbColor = Color.rgb(barRed.getProgress(), barGreen.getProgress(), barBlue.getProgress());
				miColor.setText(String.format("Azul %d, Verde %d, Rojo %d", barBlue.getProgress(), barGreen.getProgress(), barRed.getProgress()));
				miColor.setTextColor(rgbColor);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}
		};
	}

	private final View.OnClickListener miBtnListener = new View.OnClickListener() {
		@Override
		public void onClick(@NonNull View view) {
			Intent intent = new Intent(MainActivity.this, Botones.class);
			MainActivity.this.startActivity(intent);
		}
	};
}