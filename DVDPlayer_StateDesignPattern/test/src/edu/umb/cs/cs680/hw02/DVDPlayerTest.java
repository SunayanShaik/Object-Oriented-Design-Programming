package edu.umb.cs.cs680.hw02;

import static org.junit.Assert.*;
import org.junit.Test;

public class DVDPlayerTest {

	@Test
	public void dvdPlayerInstances() {
		DVDPlayer playerInstance1 = DVDPlayer.getInstance();
		DVDPlayer playerInstance2 = DVDPlayer.getInstance();
		assertEquals(playerInstance1.hashCode(), playerInstance2.hashCode());
		
	}
}
