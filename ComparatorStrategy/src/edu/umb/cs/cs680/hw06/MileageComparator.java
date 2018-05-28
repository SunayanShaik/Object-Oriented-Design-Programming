package edu.umb.cs.cs680.hw06;

import java.util.Comparator;

public class MileageComparator implements Comparator<Car> {

	@Override
	public int compare(Car car1, Car car2) {
		return (int) (car1.getMileage() - car2.getMileage());
	}
	
}
