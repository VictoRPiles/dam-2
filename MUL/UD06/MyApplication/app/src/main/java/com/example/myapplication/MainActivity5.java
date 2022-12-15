package com.example.myapplication;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity5 extends AppCompatActivity {

	private ListView itemList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main5);

		itemList = findViewById(R.id.itemList);
	}
}