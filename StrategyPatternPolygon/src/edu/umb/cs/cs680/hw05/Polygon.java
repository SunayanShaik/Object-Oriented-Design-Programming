package edu.umb.cs.cs680.hw05;

import java.awt.Point;
import java.util.ArrayList;

public class Polygon {

	protected ArrayList<Point> points;
	protected AreaCalculator areaCalc;

	public Polygon(ArrayList<Point> points, AreaCalculator areaCalc) {
		this.points = points;
		this.areaCalc = areaCalc;
	}
	
	public void setAreaCalc(AreaCalculator calc) {
		this.areaCalc = calc;
	}
	
	public void addPoint(Point point) {
		points.add(point);
	}
	
	public float getArea(ArrayList<Point> points) {
		return areaCalc.getArea(points);
	}
	
}
