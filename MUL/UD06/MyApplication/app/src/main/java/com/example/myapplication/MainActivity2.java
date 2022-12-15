package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.jetbrains.annotations.NotNull;

public class MainActivity2 extends AppCompatActivity {

	private TextView countryName;
	private ListView countryList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);

		countryName = findViewById(R.id.countryName);
		countryList = findViewById(R.id.countryList);
		countryList.setOnItemClickListener(onItemClickListener);
		addValuesToList(countryList, "Spain", "France", "Portugal", "Italy", "Germany", "UK", "Canada", "Argentina", "Brazil", "China");
	}

	private void addValuesToList(@NotNull ListView list, String... values) {
		ArrayAdapter<String> adapter =
				new ArrayAdapter<>(this,
				                   android.R.layout.simple_list_item_1,
				                   values);
		list.setAdapter(adapter);
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