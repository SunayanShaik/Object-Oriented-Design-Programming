package edu.umb.cs.cs60.hw12;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.umb.cs.cs680.hw12.Car;

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
	public void testCollectionSortUsingPriceComparatorAscendingOrder() {
		List<Car> expectedListSortedByPrice = Arrays.asList(usedCars.get(0), usedCars.get(2), usedCars.get(1));	
		Collections.sort(usedCars, (Car car1, Car car2)->{return car1.getPrice() - car2.getPrice();});
		assertThat(usedCars, is(expectedListSortedByPrice));
	}
	
	@Test
	public void testCollectionSortUsingYearComparatorAscendingOrder() {
		List<Car> expectedListSortedByYear = Arrays.asList(usedCars.get(1), usedCars.get(2), usedCars.get(0));
		Collections.sort(usedCars, (Car car1, Car car2)->{return car1.getYear() - car2.getYear();});
		assertThat(usedCars, is(expectedListSortedByYear));
	}
	
	@Test
	public void testCollectionSortUsingMileageComparatorAscendingOrder() {
		List<Car> expectedListSortedByMileage = Arrays.asList(usedCars.get(2), usedCars.get(1), usedCars.get(0));	
		Collections.sort(usedCars, (Car car1, Car car2)->{return (int) (car1.getMileage() - car2.getMileage());});
		assertThat(usedCars, is(expectedListSortedByMileage));
	}
	
	@Test
	public void testCollectionSortUsingPriceComparatorDescendingOrder() {
		List<Car> expectedListSortedByPrice = Arrays.asList(usedCars.get(1), usedCars.get(2), usedCars.get(0));	
		Collections.sort(usedCars, Comparator.comparing(Car::getPrice, Comparator.reverseOrder()));
		assertThat(usedCars, is(expectedListSortedByPrice));
	}
	
	@Test
	public void testCollectionSortUsingYearComparatorDescendingOrder() {
		List<Car> expectedListSortedByYear = Arrays.asList(usedCars.get(0), usedCars.get(2), usedCars.get(1));
		Collections.sort(usedCars, Comparator.comparing(Car::getYear, Comparator.reverseOrder()));
		assertThat(usedCars, is(expectedListSortedByYear));
	}
	
	@Test
	public void testCollectionSortUsingMileageComparatorDescendingOrder() {
		List<Car> expectedListSortedByMileage = Arrays.asList(usedCars.get(0), usedCars.get(1), usedCars.get(2));	
		Collections.sort(usedCars, Comparator.comparing(Car::getMileage, Comparator.reverseOrder()));
		assertThat(usedCars, is(expectedListSortedByMileage));
	}
	
	@Test
	public void testSortingByPriceDemoAscOrder() {
		Collections.sort(usedCars, (Car car1, Car car2)->{return car1.getPrice() - car2.getPrice();});
		System.out.println("-------------------------------------------------------------------");
		System.out.println("Sorting the cars in the ascending order by price :");
		System.out.println("-------------------------------------------------------------------");
		for(Car car: usedCars) {
			System.out.println("Price:" + car.getPrice() + ",\t"  + "Year :" + car.getYear() +",\t" + "Mileage :" + car.getMileage() + "\n");
		}
	}
	
	@Test
	public void testSortingByPriceDemoDescOrder() {
		Collections.sort(usedCars, Comparator.comparing(Car::getPrice, Comparator.reverseOrder()));
		System.out.println("-------------------------------------------------------------------");
		System.out.println("Sorting the cars in the descending order by price :");
		System.out.println("-------------------------------------------------------------------");
		for(Car car: usedCars) {
			System.out.println("Price:" + car.getPrice() + ",\t"  + "Year :" + car.getYear() +",\t" + "Mileage :" + car.getMileage() + "\n");
		}
	}
	
	@Test
	public void testSortingByYearDemoAscOrder() {
		Collections.sort(usedCars, (Car car1, Car car2)->{return car2.getPrice() - car1.getPrice();});
		System.out.println("-------------------------------------------------------------------");
		System.out.println("Sorting the cars in the ascending order by year :");
		System.out.println("-------------------------------------------------------------------");
		for(Car car: usedCars) {
			System.out.println("Year :" + car.getYear() +",\t" + "Price:" + car.getPrice() + ",\t" + "Mileage :" + car.getMileage() + "\n");
		}
	}
	
	@Test
	public void testSortingByYearDemoDescOrder() {
		Collections.sort(usedCars, Comparator.comparing(Car::getYear, Comparator.reverseOrder()));
		System.out.println("-------------------------------------------------------------------");
		System.out.println("Sorting the cars in the descending order by year :");
		System.out.println("-------------------------------------------------------------------");
		for(Car car: usedCars) {
			System.out.println("Year :" + car.getYear() +",\t" + "Price:" + car.getPrice() + ",\t" + "Mileage :" + car.getMileage() + "\n");
		}
	}
	
	
	@Test
	public void testSortingByMileageDemoAscOrder() {
		Collections.sort(usedCars, (Car car1, Car car2)->{return (int) (car1.getMileage() - car2.getMileage());});
		System.out.println("-------------------------------------------------------------------");
		System.out.println("Sorting the cars in the ascending order by Mileage :");
		System.out.println("-------------------------------------------------------------------");
		for(Car car: usedCars) {
			System.out.println("Mileage :" + car.getMileage() + ",\t " + "Year :" + car.getYear() +",\t" + "Price:" + car.getPrice() + "\n");
		}
	}
	
	@Test
	public void testSortingByMileageDemoDescOrder() {
		Collections.sort(usedCars, Comparator.comparing(Car::getMileage, Comparator.reverseOrder()));
		System.out.println("-------------------------------------------------------------------");
		System.out.println("Sorting the cars in the descending order by Mileage :");
		System.out.println("-------------------------------------------------------------------");
		for(Car car: usedCars) {
			System.out.println("Mileage :" + car.getMileage() + ",\t " + "Year :" + car.getYear() +",\t" + "Price:" + car.getPrice() + "\n");
		}
	}
	
}
