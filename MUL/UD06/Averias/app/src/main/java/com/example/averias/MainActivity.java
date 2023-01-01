package com.example.averias;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.averias.damage.Damage;
import com.example.averias.damage.DamagesAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Victor Piles
 */
public class MainActivity extends AppCompatActivity {

	@SuppressWarnings("FieldCanBeLocal")
	private ListView damagesListView;
	@SuppressWarnings("FieldCanBeLocal")
	private List<Damage> damagesList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		damagesListView = findViewById(R.id.damagesList);
		List<Damage> damages = Arrays.asList(new Damage("Espejo roto", "Audi - A4", "", 2),
		                                     new Damage("Paragolpes delantero", "Citroen - C4", "", 0),
		                                     new Damage("Embrague", "Seat - Ibiza", "", 0),
		                                     new Damage("Cambio de aceite", "Seat - Toledo", "", 1)
		);
		damagesList = new ArrayList<>(damages);

		DamagesAdapter adapter = new DamagesAdapter(this, R.layout.damage_item, damagesList);
		damagesListView.setAdapter(adapter);
	}
}