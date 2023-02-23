package objects;

import util.Serializer;

import java.io.Serializable;
import java.util.Arrays;

public class Car implements Serializable {
	// @Serial
	private static final long serialVersionUID = 1000L;

	private final String modelo;
	private String color;

	public Car(String modelo, String color) {
		this.modelo = modelo;
		this.color = color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Car car = (Car) o;

		byte[] thisCarHash = Serializer.serialize(this, "MD5");
		byte[] otherCarHash = Serializer.serialize(car, "MD5");

		return Arrays.equals(thisCarHash, otherCarHash);
	}

	@Override
	public String toString() {
		return "Car{" +
				"modelo='" + modelo + '\'' +
				", color='" + color + '\'' +
				'}';
	}
}
