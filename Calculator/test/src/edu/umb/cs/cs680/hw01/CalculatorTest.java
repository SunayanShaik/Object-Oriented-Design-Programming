package edu.umb.cs.cs680.hw01;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

import edu.umb.cs.cs680.hw01.Calculator;

public class CalculatorTest {

	@Test
	public void add2Plus3() {
		Calculator calc = new Calculator();
		float expected = 5;
		float actual = calc.add(2, 3);
		assertThat(actual, is(expected));
	}
	
	@Test
	public void subtract3From5() {
		Calculator calc = new Calculator();
		float expected = 2;
		float actual = calc.subtract(5, 3);
		assertThat(actual, is(expected));
	}
	
	@Test
	public void subtract6From2() {
		Calculator calc = new Calculator();
		float expected = -4;
		float actual = calc.subtract(2, 6);
		assertThat(actual, is(expected));
	}
	
	@Test
	public void multiply2By4() {
		Calculator calc = new Calculator();
		float expected = 8;
		float actual = calc.multiply(2, 4);
		assertThat(actual, is(expected));
	}
	
	@Test
	public void multiply2By0() {
		Calculator calc = new Calculator();
		float expected = 0;
		float actual = calc.multiply(2, 0);
		assertThat(actual, is(expected));
	}
	
	@Test
	public void divide4By5() {
		Calculator calc = new Calculator();
		float expected = 0.8f;
		float actual = calc.divide(4, 5);
		assertThat(actual, is(expected));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void divide2By0() {
		Calculator calc = new Calculator();
		calc.divide(2, 0);
	}
	
	@Test
	public void powerOf2ToZero() {
		Calculator calc = new Calculator();
		double expected = 1;
		double actual = calc.powerOf(2, 0);
		assertThat(actual, is(expected));
	}
	
	@Test
	public void powerOf2ToAnyNumber() {
		Calculator calc = new Calculator();
		double expected = 8;
		double actual = calc.powerOf(2, 3);
		assertThat(actual, is(expected));
	}
	
	@Test
	public void square_rootOf4() {
		Calculator calc = new Calculator();
		double expected = 2;
		double actual = calc.squareRoot(4);
		assertThat(actual, is(expected));
	}

}
