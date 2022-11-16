package com.example.myapplication02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	private TextView message;
	private Button btnAnimation;
	private Animation animation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/* Layout */
		message = findViewById(R.id.tvMessage);
		btnAnimation = findViewById(R.id.btnAnimation);

		btnAnimation.setOnClickListener(btnAnimationListener);

		animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
	}

	private final View.OnClickListener btnAnimationListener = new View.OnClickListener() {
		@Override
		public void onClick(@NonNull View view) {
			message.startAnimation(animation);
		}
	};
}