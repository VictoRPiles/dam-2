package com.example.averias.damage;

/**
 * @author Victor Piles
 */
public class Damage {

	private String title;
	private String carModel;
	private String photoUrl;
	private int budgetNumber;

	public Damage(String title, String carModel, String photoUrl, int budgetNumber) {
		this.title = title;
		this.carModel = carModel;
		this.photoUrl = photoUrl;
		this.budgetNumber = budgetNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public int getBudgetNumber() {
		return budgetNumber;
	}

	public void setBudgetNumber(int budgetNumber) {
		this.budgetNumber = budgetNumber;
	}
}