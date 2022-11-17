package com.example.seekbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	private TextView progress;
	private SeekBar bar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		progress = findViewById(R.id.seekBarProgress);
		bar = findViewById(R.id.seekBar);

		bar.setOnSeekBarChangeListener(getBarChangeListener());
	}

	@NonNull
	private SeekBar.OnSeekBarChangeListener getBarChangeListener() {
		return new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
				progress.setText(String.valueOf(i));
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