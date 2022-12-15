package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.jetbrains.annotations.NotNull;

public class MainActivity4 extends AppCompatActivity {

	private TextView countryName;
	private Spinner countrySpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main4);

		countryName = findViewById(R.id.countryName);
		countrySpinner = findViewById(R.id.countrySpinner);
		countrySpinner.setOnItemSelectedListener(onItemSelectedListener);
		addValuesToSpinner(countrySpinner, "Spain", "France", "Portugal", "Italy", "Germany", "UK", "Canada", "Argentina", "Brazil", "China");
	}

	private void addValuesToSpinner(@NotNull Spinner spinner, String... values) {
		ArrayAdapter<String> adapter =
				new ArrayAdapter<>(this,
				                   android.R.layout.simple_spinner_dropdown_item,
				                   values);
		spinner.setAdapter(adapter);
	}

	private final AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
		@Override
		public void onItemSelected(@NotNull AdapterView<?> values, View view, int item, long id) {
			String valueAtPosition = values.getItemAtPosition(item).toString();
			Log.i("onItemSelected", valueAtPosition);
			countryName.setText(valueAtPosition);
		}

		@SuppressLint("SetTextI18n")
		@Override
		public void onNothingSelected(AdapterView<?> adapterView) {
			countryName.setText("Country");
		}
	};
}