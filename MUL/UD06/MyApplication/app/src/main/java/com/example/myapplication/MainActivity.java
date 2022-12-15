package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	private ListView listView;
	private ArrayList<String> names;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = findViewById(R.id.list);
		names = new ArrayList<>();
		names.add("Victor");
		names.add("Nando");
		names.add("Sergio");
		names.add("Carlos");

		ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
		listView.setAdapter(adapter);
	}
}