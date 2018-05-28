package edu.umb.cs.cs680.hw06;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

public class CarComparatorTest {

	List<Car> usedCars;
	
	@Before
	public void setUp() {
		usedCars = new ArrayList<Car>();
		
		usedCars.add(new Car(1000, 2009, 120.4f));
		usedCars.add(new Car(2000, 2001, 100.1f));
		usedCars.add(new Car(1500, 2005, 80.5f));
	}
	
	@After
	public void releaseSetUp() {
		usedCars = null;
		assertNull(usedCars);
	}
	
	@Test
	public void testCarConstructor() {
		int expectedPrice = 1200;
		int expectedYear = 2015;
		float expectedMileage = 140.8f;
		Car actualCarObject = new Car(1200, 2015, 140.8f);
		assertThat(actualCarObject.getPrice(), is(expectedPrice));
		assertThat(actualCarObject.getYear(), is(expectedYear));
		assertThat(actualCarObject.getMileage(), is(expectedMileage));
	}
	
	@Test
	public void testCollectionSortUsingPriceComparator() {
		List<Car> expectedListSortedByPrice = Arrays.asList(usedCars.get(1), usedCars.get(2), usedCars.get(0));	
		Collections.sort(usedCars, new PriceComparator());
		assertThat(usedCars, is(expectedListSortedByPrice));
	}
	
	@Test
	public void testCollectionSortUsingYearComparator() {
		List<Car> expectedListSortedByYear = Arrays.asList(usedCars.get(1), usedCars.get(2), usedCars.get(0));
		Collections.sort(usedCars, new YearComparator());
		assertThat(usedCars, is(expectedListSortedByYear));
	}
	
	@Test
	public void testCollectionSortUsingMileageComparator() {
		List<Car> expectedListSortedByMileage = Arrays.asList(usedCars.get(2), usedCars.get(1), usedCars.get(0));	
		Collections.sort(usedCars, new MileageComparator());
		assertThat(usedCars, is(expectedListSortedByMileage));
	}
	
	@Test
	public void testSortingByPriceDemo() {
		Collections.sort(usedCars, new PriceComparator());
		System.out.println("-------------------------------------------------------------------");
		System.out.println("Sorting the cars in the descending order by price :");
		System.out.println("-------------------------------------------------------------------");
		for(Car car: usedCars) {
			System.out.println("Price:" + car.getPrice() + ",\t"  + "Year :" + car.getYear() +",\t" + "Mileage :" + car.getMileage() + "\n");
		}
	}
	
	@Test
	public void testSortingByYearDemo() {
		Collections.sort(usedCars, new YearComparator());
		System.out.println("-------------------------------------------------------------------");
		System.out.println("Sorting the cars in the ascending order by year :");
		System.out.println("-------------------------------------------------------------------");
		for(Car car: usedCars) {
			System.out.println("Year :" + car.getYear() +",\t" + "Price:" + car.getPrice() + ",\t" + "Mileage :" + car.getMileage() + "\n");
		}
	}
	
	@Test
	public void testSortingByMileageDemo() {
		Collections.sort(usedCars, new MileageComparator());
		System.out.println("-------------------------------------------------------------------");
		System.out.println("Sorting the cars in the ascending order by Mileage :");
		System.out.println("-------------------------------------------------------------------");
		for(Car car: usedCars) {
			System.out.println("Mileage :" + car.getMileage() + ",\t " + "Year :" + car.getYear() +",\t" + "Price:" + car.getPrice() + "\n");
		}
	}
	
}
