package org.example;

import org.example.util.Color;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Person implements Runnable {
	private final String name;
	private final Account account;

	public Person(String name, Account account) {
		this.name = name;
		this.account = account;
	}

	public static @NotNull Float getRandomMovement(float min, float max) {
		Random random = new Random();
		return (random.nextFloat() * max) + min;
	}

	@Override
	public void run() {
		int iterations = 2;
		float randomMovement;
		for (int i = 0; i < iterations; i++) {
			randomMovement = getRandomMovement(1, 500);
			Color.printYellowMessage(name + " increments the balance by: " + randomMovement + "€");
			account.incrementBalance(randomMovement);

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

			randomMovement = getRandomMovement(1, 500);
			Color.printYellowMessage(name + " decrements the balance by: " + randomMovement + "€");
			account.decrementBalance(randomMovement);

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
