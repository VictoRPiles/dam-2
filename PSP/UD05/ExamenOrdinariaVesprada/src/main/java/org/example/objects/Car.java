package org.example.objects;

import java.io.Serial;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Car implements Serializable {
	@Serial
	private static final long serialVersionUID = 1000L;

	private final String modelo;
	private String color;

	public Car(String modelo, String color) {
		this.modelo = modelo;
		this.color = color;
	}

	public String getModelo() {
		return modelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Car car = (Car) o;

		byte[] thisCarBytes = this.toString().getBytes();
		byte[] carBytes = car.toString().getBytes();

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}

		byte[] thisCarHash = md.digest(thisCarBytes);
		byte[] carHash = md.digest(carBytes);

		return Arrays.equals(thisCarHash, carHash);
	}

	@Override
	public String toString() {
		return "Car{" +
				"modelo='" + modelo + '\'' +
				", color='" + color + '\'' +
				'}';
	}
}
