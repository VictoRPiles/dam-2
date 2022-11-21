package com.example.seekbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	private TextView progressRed, progressGreen, progressBlue;
	private Button colorBtn;
	private SeekBar barRed, barGreen, barBlue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		progressRed = findViewById(R.id.progressRed);
		progressGreen = findViewById(R.id.progressGreen);
		progressBlue = findViewById(R.id.progressBlue);
		colorBtn = findViewById(R.id.colorBtn);

		barRed = findViewById(R.id.seekBarRed);
		barGreen = findViewById(R.id.seekBarGreen);
		barBlue = findViewById(R.id.seekBarBlue);

		barRed.setOnSeekBarChangeListener(getBarChangeListener());
		barGreen.setOnSeekBarChangeListener(getBarChangeListener());
		barBlue.setOnSeekBarChangeListener(getBarChangeListener());
	}

	@NonNull
	private SeekBar.OnSeekBarChangeListener getBarChangeListener() {
		return new SeekBar.OnSeekBarChangeListener() {
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
				colorBtn.setBackgroundColor(rgbColor);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}
		};
	}
}