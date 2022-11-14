package com.example.contador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class Contador extends AppCompatActivity
{
	private static String CUENTA;

	private TextView textContador;

	private Button btnMas, btnMenos, btnReset;
	private CheckBox checkMaximo, checkMinimo;

	private int cuenta = 0;
	private int valorMaximo = Integer.MAX_VALUE,
			valorMinimo = Integer.MIN_VALUE;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contador);

		/* Components */
		textContador = findViewById(R.id.contador);

		btnMas = findViewById(R.id.btnMas);
		btnMas.setOnClickListener(btnMasListener);

		btnMenos = findViewById(R.id.btnMenos);
		btnMenos.setOnClickListener(btnMenosListener);

		btnReset = findViewById(R.id.btnReset);
		btnReset.setOnClickListener(btnResetListener);

		checkMaximo = findViewById(R.id.checkMaximo);
		checkMaximo.setOnClickListener(checkMaximoListener);

		checkMinimo = findViewById(R.id.checkMinimo);
		checkMinimo.setOnClickListener(checkMinimoListener);
	}

	@Override
	protected void onSaveInstanceState(@NonNull Bundle outState)
	{
		super.onSaveInstanceState(outState);

		outState.putInt(CUENTA, cuenta);
	}

	@Override
	protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState)
	{
		super.onRestoreInstanceState(savedInstanceState);

		cuenta = savedInstanceState.getInt(CUENTA, 0);
		textContador.setText(String.valueOf(cuenta));
	}

	/* Listeners */
	/* https://developer.android.com/guide/topics/ui/ui-events?hl=es-419#java */

	/**
	 * Aumenta el valor de {@link #cuenta}, si no excede {@link #valorMaximo}.
	 */
	private final View.OnClickListener btnMasListener = new View.OnClickListener()
	{
		@Override
		public void onClick(@NonNull View view)
		{
			if (cuenta < valorMaximo) {
				textContador.setText(String.valueOf(++cuenta));
			}
		}
	};
	/**
	 * Decrementa el valor de {@link #cuenta}, si no excede {@link #valorMinimo}.
	 */
	private final View.OnClickListener btnMenosListener = new View.OnClickListener()
	{
		@Override
		public void onClick(@NonNull View view)
		{
			if (cuenta > valorMinimo) {
				textContador.setText(String.valueOf(--cuenta));
			}
		}
	};
	/**
	 * Establece el valor de {@link #cuenta} a 0.
	 */
	private final View.OnClickListener btnResetListener = new View.OnClickListener()
	{
		@Override
		public void onClick(@NonNull View view)
		{
			cuenta = 0;
			textContador.setText(String.valueOf(cuenta));
		}
	};
	/**
	 * Establece el valor de {@link #valorMaximo} a 50.
	 * <p>
	 * Establece el valor de la cuenta a 50 si excede el {@link #valorMaximo}.
	 */
	private final View.OnClickListener checkMaximoListener = new View.OnClickListener()
	{
		@Override
		public void onClick(@NonNull View view)
		{
			if (((CheckBox) view).isChecked()) {
				valorMaximo = 50;
				/* Reset de la cuenta a 0 si excede el máximo */
				if (cuenta > valorMaximo) {
					cuenta = 50;
					textContador.setText(String.valueOf(cuenta));
				}
			}
			else {
				valorMaximo = Integer.MAX_VALUE;
			}
		}
	};

	/**
	 * Establece el valor de {@link #valorMinimo} a 0.
	 * <p>
	 * Establece el valor de la cuenta a 0 si excede el {@link #valorMinimo}.
	 */
	private final View.OnClickListener checkMinimoListener = new View.OnClickListener()
	{
		@Override
		public void onClick(@NonNull View view)
		{
			if (((CheckBox) view).isChecked()) {
				valorMinimo = 0;
				/* Reset de la cuenta a 0 si excede el mínimo */
				if (cuenta < valorMinimo) {
					cuenta = 0;
					textContador.setText(String.valueOf(cuenta));
				}
			}
			else {
				valorMinimo = Integer.MIN_VALUE;
			}
		}
	};
}