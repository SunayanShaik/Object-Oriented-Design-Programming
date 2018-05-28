package edu.umb.cs.cs680.hw01;

public class Calculator {

	public int add(int x, int y) {

		return x + y;
	}

	public int subtract(int x, int y) {

		return x - y;
	}

	public float multiply(float x, float y) {
		return x * y;
	}

	public float divide(float x, float y) {
		if(y==0) {
			throw new IllegalArgumentException("division by zero");
		}
		return x / y;
	}

	public double powerOf(double x, double y) {
		return Math.pow(x, y);
	}

	public double squareRoot(double x) {
		return Math.sqrt(x);
	}

	public static void main(String[] args) {

		Calculator calculator = new Calculator();

		int sum = calculator.add(2, 3);
		System.out.println("The sum of (2 + 3) is: " + sum + "\n");

		int difference = calculator.subtract(5, 3);
		System.out.println("The difference of (5 - 3) is: " + difference + "\n");

		float product = calculator.multiply(3, 4);
		System.out.println("The product of (3 * 4) is: " + product + "\n");

		float quotient = calculator.divide(5, 2);
		System.out.println("The quotient of (5 / 2) is: " + quotient + "\n");

		double power = calculator.powerOf(2, 3);
		System.out.println("The value of (2^3) is: " + power + "\n");

		double square_root = calculator.squareRoot(4);
		System.out.println("The square root of 4 is: " + square_root);

	}

}
