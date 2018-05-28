package edu.umb.cs.cs680.hw12;

public class Car {

	private int price;
	private int year;
	private float mileage;
	
	public Car(int price, int year, float mileage) {
		this.price = price;
		this.year = year;
		this.mileage = mileage;
	}
	
	public int getPrice() {
		return price;
	}
	
	public int getYear() {
		return year;
	}
	
	public float getMileage() {
		return mileage;
	}
	
}