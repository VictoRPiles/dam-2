package com.example.myapplication01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class TercerActivity extends AppCompatActivity {

	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tercer);

		textView = findViewById(R.id.tercerTexto);
		textView.append("\n" + getString(R.string.tercerTextoSegundaParte));
	}
}