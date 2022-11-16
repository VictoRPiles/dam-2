package com.example.myapplication01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SegundoActivity extends AppCompatActivity {

	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_segundo);

		textView = findViewById(R.id.segundoTextView);
		textView.setText(R.string.segundoTexto);
	}
}