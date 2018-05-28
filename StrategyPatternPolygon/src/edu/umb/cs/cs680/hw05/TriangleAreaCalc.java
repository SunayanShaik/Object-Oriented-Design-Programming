package edu.umb.cs.cs680.hw05;

import java.awt.Point;
import java.util.List;

public class TriangleAreaCalc implements AreaCalculator {

	@Override
	public float getArea(List<Point> points) {

		float triangleArea;
		double a, b, c, s, perimeter;
		double x1, x2, x3, y1, y2, y3;
		Point p1, p2, p3;

		p1 = points.get(0);
		x1 = p1.getX();
		y1 = p1.getY();
		
		p2 = points.get(1);
		x2 = p2.getX();
		y2 = p2.getY();
		
		p3 = points.get(2);
		x3 = p3.getX();
		y3 = p3.getY();

		a = Math.hypot(x2 - x1, y2 - y1);
		b = Math.hypot(x3 - x2, y3 - y2);
		c = Math.hypot(x3 - x1, y3 - y1);

		perimeter = a + b + c;
		s = perimeter / 2;
		triangleArea = (float) Math.sqrt(s * (s - a) * (s - b) * (s - c));

		return triangleArea;
	}

}
