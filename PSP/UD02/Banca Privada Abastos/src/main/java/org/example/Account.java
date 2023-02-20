package org.example;

import org.example.util.Color;

public class Account {
	private Float balance;
	private final Float maxBalance;
	private final Float minBalance;

	public Account(Float balance, Float maxBalance, Float minBalance) {
		this.balance = balance;
		this.maxBalance = maxBalance;
		this.minBalance = minBalance;
	}

	public synchronized void incrementBalance(Float increment) {
		Color.printGreenMessage("Current balance: " + balance + "€");
		Color.printGreenMessage("Incrementing balance by: " + increment + "€...");
		float newBalance = balance + increment;
		if (newBalance > maxBalance) {
			Color.printRedMessage("Reached maximum balance!!!");
			try {
				wait();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		this.balance += increment;
		Color.printGreenMessage("New balance: " + balance + "€");
	}

	public synchronized void decrementBalance(Float decrement) {
		Color.printGreenMessage("Current balance: " + balance + "€");
		Color.printGreenMessage("Decrementing balance by: " + decrement + "€...");
		float newBalance = balance - decrement;
		if (newBalance < minBalance) {
			Color.printRedMessage("Reached minimum balance!!!");
			try {
				wait();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		this.balance -= decrement;
		Color.printGreenMessage("New balance: " + balance + "€");
	}

	public Float getBalance() {
		return balance;
	}
}
