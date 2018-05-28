package edu.umb.cs.cs680.hw05;

import java.awt.Point;
import java.util.List;

public class RectangleAreaCalc implements AreaCalculator{

	@Override
	public float getArea(List<Point> points) {
		
		float rectangleArea;
		double length, width, x1, x2, x3, x4, y1, y2, y3, y4;
		Point p1, p2, p3, p4;

		p1 = points.get(0);
		x1 = p1.getX();
		y1 = p1.getY();
		
		p2 = points.get(1);
		x2 = p2.getX();
		y2 = p2.getY();
		
		p3 = points.get(2);
		x3 = p3.getX();
		y3 = p3.getY();
		
		p4 = points.get(3);
		x4 = p4.getX();
		y4 = p4.getY();
		
		length = Math.hypot(x2-x1, y2-y1);
		width = Math.hypot(x4-x3, y4-y3);
		rectangleArea = (float) (length * width);
		return rectangleArea;
	}

	
	
}
