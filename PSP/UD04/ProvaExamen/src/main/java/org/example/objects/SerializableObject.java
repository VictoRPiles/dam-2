package org.example.objects;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author victor
 */
@SuppressWarnings("unused")
public class SerializableObject implements Serializable {
	@Serial
	private static final long serialVersionUID = 1000L;

	private String name;
	private Integer number;

	public SerializableObject(String name, Integer number) {
		this.name = name;
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "SerializableObject{" +
				"name='" + name + '\'' +
				", number=" + number +
				'}';
	}
}
