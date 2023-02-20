package org.example;

public class Main {
	public static void main(String[] args) {
		Account account = new Account(40.0f, 500.0f, 0.0f);
		Person person1 = new Person("Person 1", account);
		Person person2 = new Person("Person 2", account);
		Person person3 = new Person("Person 3", account);

		new Thread(person1).start();
		new Thread(person2).start();
		new Thread(person3).start();
	}
}