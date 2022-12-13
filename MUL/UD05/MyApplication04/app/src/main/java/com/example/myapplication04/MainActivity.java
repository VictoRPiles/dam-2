package com.example.myapplication04;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	private TextView message;
	private Button button1, button2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		message = findViewById(R.id.message);
		button1 = findViewById(R.id.button1);
		button2 = findViewById(R.id.button2);

		button1.setOnClickListener(buttonClickListener);
		button2.setOnClickListener(buttonClickListener);
	}

	/**
	 * Changes the {@link #message message} when {@link #button1} or {@link #button2} are clicked.
	 *
	 * @see View.OnClickListener#onClick(View)
	 */
	private final View.OnClickListener buttonClickListener = new View.OnClickListener() {
		@SuppressLint("SetTextI18n")
		@Override
		public void onClick(@NonNull View view) {
			if (view.equals(button1)) {
				message.setText("Button 1");
				message.setTextColor(Color.rgb(0, 255, 0));
			}
			else if (view.equals(button2)) {
				message.setText("Button 2");
				message.setTextColor(Color.rgb(255, 0, 0));
			}
		}
	};
}