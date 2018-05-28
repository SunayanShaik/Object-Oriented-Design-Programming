package edu.umb.cs.cs680.hw05;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PolygonTest {
	
	ArrayList<Point> al1, al2;
	ArrayList<Polygon> polygon;
	Polygon p1, p2;
	
	@Before
	public void setUp() {
		al1 = new ArrayList<Point>();
		al2 = new ArrayList<Point>();
		
		al1.add(new Point(0, 0));
		al1.add(new Point(0, 10));
		al1.add(new Point(10, 0));
		polygon = new ArrayList<Polygon>();
		
		al2.add(new Point(0, 0));
		al2.add(new Point(0, 9));
		al2.add(new Point(9, 0));	
		polygon.add(p1);
		polygon.add(p2);
	}
	
	@Test
	public void testTriangle1Constructor() {
		List<Point> expectedTriangle1List = Arrays.asList(new Point(0,0), new Point(0,10), new Point(10,0));
		Triangle triangle = new Triangle(al1, new TriangleAreaCalc());
		List<Point> actualtriangle1List = triangle.getPoints(al1);
		assertEquals(expectedTriangle1List, actualtriangle1List);
	}
	
	@Test
	public void testTriangle2Constructor() {
		List<Point> expectedTriangle2List = Arrays.asList(new Point(0,0), new Point(0,9), new Point(9,0));
		Triangle triangle = new Triangle(al2, new TriangleAreaCalc());
		List<Point> actualtriangle2List = triangle.getPoints(al2);
		assertEquals(expectedTriangle2List, actualtriangle2List);
	}
	
	@Test
	public void testRectangle1Constructor() {
		List<Point> expectedRectangle1List = Arrays.asList(new Point(0,0), new Point(0,10), new Point(10,0), new Point(10,10));
		al1.add(new Point(10,10));
		Rectangle rectangle = new Rectangle(al1, new RectangleAreaCalc());
		List<Point> actualRectangle1List = rectangle.getPoints(al1);
		assertEquals(expectedRectangle1List, actualRectangle1List);
	}
	
	@Test
	public void testRectangle2Constructor() {
		List<Point> expectedRectangle2List = Arrays.asList(new Point(0,0), new Point(0,9), new Point(9,0), new Point(9,9));
		al2.add(new Point(9,9));
		Rectangle rectangle = new Rectangle(al2, new RectangleAreaCalc());
		List<Point> actualRectangle2List = rectangle.getPoints(al2);
		assertEquals(expectedRectangle2List, actualRectangle2List);
	}
	
	@Test
	public void triangle1AreaCalcTest() {
		float expected = 50;
		p1 = new Polygon(al1, new TriangleAreaCalc());
		p1.getArea(al1);
		assertThat(p1.getArea(al1), is(expected));
	}
	
	@Test
	public void triangle2AreaCalcTest() {
		float expected = 40.5f;
		p2 = new Polygon(al2, new TriangleAreaCalc());
		p2.getArea(al2);
		assertThat(p2.getArea(al2), is(expected));
	}
	
	@Test
	public void rectangle1AreaCalcTest() {
		float expected = 100;
		p1 = new Polygon(al1, new RectangleAreaCalc());
		p1.addPoint(new Point(10, 10));
		p1.setAreaCalc(new RectangleAreaCalc());
		assertThat(p1.getArea(al1), is(expected));
	}
	
	@Test
	public void rectangle2AreaCalcTest() {
		float expected = 81;
		p2 = new Polygon(al2, new RectangleAreaCalc());
		p2.addPoint(new Point(9, 9));
		p2.setAreaCalc(new RectangleAreaCalc());
		assertThat(p2.getArea(al2), is(expected));
	}
	
	@Test
	public void testPolygonUsingPolymorphism() {
		p1 = new Polygon(al1, new TriangleAreaCalc());
		float triangle_area = p1.getArea(al1);
		System.out.println("The points of a triangle are: "); 
		for(Point point: al1) {
			System.out.println("("+point.x + "," + point.y + ")");
		}
		System.out.println("The triangle's area is : " + triangle_area + "\n");
		
		al1.add(new Point(10,10));
		p1.setAreaCalc(new RectangleAreaCalc());
		float rectangle_area = p1.getArea(al1);
		System.out.println("The points of a rectangle are: "); 
		for(Point point: al1) {
			System.out.println("("+point.x + "," + point.y + ")");
		}
		System.out.println("The rectangle's area is : " +rectangle_area);
	}

}
