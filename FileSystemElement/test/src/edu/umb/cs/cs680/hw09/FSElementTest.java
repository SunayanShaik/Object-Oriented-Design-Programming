package edu.umb.cs.cs680.hw09;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FSElementTest {

	private Directory root, system, home, pictures;
	private File a, b, c, d, e, f;
	private Link x, y;
	private int size = 0;
	
	@Before
	public void setUp() {
		Date created = new Date(System.currentTimeMillis() - 3600 * 1000);
		Date modified = new java.util.Date();
		root = new Directory(null, "root", "sunayan", created, modified, 0, false);
		system = new Directory(root, "system", "sunayan", created, modified, 0, false);
		home = new Directory(root, "home", "sunayan", created, modified, 0, false);
		pictures = new Directory(home, "pictures", "sunayan", created, modified, 0, false);
		
		root.appendChild(system);
		root.appendChild(home);
		
		a = new File(system, "a", "sunayan", created, modified, 10, true);
		b = new File(system, "b", "sunayan", created, modified, 20, true);
		c = new File(system, "c", "sunayan", created, modified, 30, true);
		
		system.appendChild(a);
		system.appendChild(b);
		system.appendChild(c);
		
		d = new File(home, "d", "sunayan", created, modified, 40, true);
		x = new Link(home, system, "x", "sunayan", created, modified, 0, false);
		
		home.appendChild(d);
		home.appendChild(x);
		home.appendChild(pictures);
		
		e = new File(pictures, "e", "sunayan",created, modified, 50, true);
		f = new File(pictures, "f", "sunayan",created, modified, 60, true);
		y = new Link(pictures, e, "y", "sunayan",created, modified, 0, false);
		
		pictures.appendChild(e);
		pictures.appendChild(f);
		pictures.appendChild(y);
		
	}
	
	@After
	public void tearDown() {
		root = null;
	}
	
	@Test
	public void showElementsTest() {
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Tree Structure: ");
		System.out.println("----------------------------------------------------------------------");
		FileSystem fileSystem = FileSystem.getFileSystem(root);
		fileSystem.showAllElements();
	}
	
	@Test
	public void getSizeOfRootTest() {
		size = root.getSize();
		
		int expectedSize = 210;
		assertEquals(expectedSize, size);
		
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Size of root is: " +size);
		System.out.println("----------------------------------------------------------------------");
	}
	
	@Test
	public void getTargetSizeOnLinkXTest() {
		size = x.getTargetSize();
		
		int expectedSize = 60;
		assertEquals(expectedSize, size);
		
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Target Size of link x is: " +size);
		System.out.println("----------------------------------------------------------------------");
	}
	
	@Test
	public void getTargetSizeOnLinkYTest() {
		size = y.getTargetSize();
		
		int expectedSize = 50;
		assertEquals(expectedSize, size);
		
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Target Size of link y is: " +size);
		System.out.println("----------------------------------------------------------------------");
	}
	
	@Test
	public void getSizeOnLinkXTest() {
		size = x.getSize();
		
		int expectedSize = 60;
		assertEquals(expectedSize, size);
		
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Size of link x is: " +size);
		System.out.println("----------------------------------------------------------------------");
	}
	
	@Test
	public void getSizeOnLinkYTest() {
		size = y.getSize();
		
		int expectedSize = 50;
		assertEquals(expectedSize, size);
		
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Size of link y is: " +size);
		System.out.println("----------------------------------------------------------------------"); 
	}
	
	@Test
	public void getParentTest() {
		
		String expected1 = "root";
		String expected2 = "root";
		String expected3 = "home";
		
		assertEquals(expected1, system.getParent().getName());
		assertEquals(expected2, home.getParent().getName());
		assertEquals(expected3, pictures.getParent().getName());
		
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Parent of:");
		System.out.println("----------------------------------------------------------------------");
		System.out.println("system   : " +system.getParent().getName());
		System.out.println("home     : " +home.getParent().getName());
		System.out.println("pictures : " +pictures.getParent().getName());
	}
	
	@Test
	public void getTargetNameTest() {
		
		String expected1 = "system";
		String expected2 = "e";
		
		assertEquals(expected1, x.getTargetName());
		assertEquals(expected2, y.getTargetName());
		
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Target names : ");
		System.out.println("----------------------------------------------------------------------");
		System.out.println("For x : " +x.getTargetName());
		System.out.println("For y : " +y.getTargetName());
	}
	

}
