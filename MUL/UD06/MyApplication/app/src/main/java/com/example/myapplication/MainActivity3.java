package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.jetbrains.annotations.NotNull;

public class MainActivity3 extends AppCompatActivity {

	private TextView countryName;
	private GridView countryTable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main3);

		countryName = findViewById(R.id.countryName);
		countryTable = findViewById(R.id.countryTable);
		countryTable.setOnItemClickListener(onItemClickListener);
		addValuesToTable(countryTable, "Spain", "France", "Portugal", "Italy", "Germany", "UK", "Canada", "Argentina", "Brazil", "China");
	}

	private void addValuesToTable(@NotNull GridView table, String... values) {
		ArrayAdapter<String> adapter =
				new ArrayAdapter<>(this,
				                   android.R.layout.simple_list_item_1,
				                   values);
		table.setAdapter(adapter);
	}

	private final AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(@NotNull AdapterView<?> values, View view, int item, long id) {
			String valueAtPosition = values.getItemAtPosition(item).toString();
			Log.i("onItemClickListener", valueAtPosition);
			countryName.setText(valueAtPosition);
		}
	};
}