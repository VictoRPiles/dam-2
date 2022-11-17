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
	private Button btnAnimationRotate, btnAnimationFade, btnAnimationTranslate;
	private Animation rotateAnimation, fadeAnimation, translateAnimation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/* Views */
		message = findViewById(R.id.tvMessage);
		btnAnimationRotate = findViewById(R.id.btnAnimationRotate);
		btnAnimationFade = findViewById(R.id.btnAnimationFade);
		btnAnimationTranslate = findViewById(R.id.btnAnimationTranslate);

		/* OnClick */
		btnAnimationRotate.setOnClickListener(btnAnimationRotateListener);
		btnAnimationFade.setOnClickListener(btnAnimationFadeListener);
		btnAnimationTranslate.setOnClickListener(btnAnimationTranslateListener);

		/* Animations */
		rotateAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
		fadeAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
		translateAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate);
	}

	/* ==================== OnClickListener ==================== */

	private final View.OnClickListener btnAnimationRotateListener = new View.OnClickListener() {
		@Override
		public void onClick(@NonNull View view) {
			message.startAnimation(rotateAnimation);
		}
	};

	private final View.OnClickListener btnAnimationFadeListener = new View.OnClickListener() {
		@Override
		public void onClick(@NonNull View view) {
			message.startAnimation(fadeAnimation);
		}
	};

	private final View.OnClickListener btnAnimationTranslateListener = new View.OnClickListener() {
		@Override
		public void onClick(@NonNull View view) {
			message.startAnimation(translateAnimation);
		}
	};
}