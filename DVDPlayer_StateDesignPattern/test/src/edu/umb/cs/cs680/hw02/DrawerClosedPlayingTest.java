package edu.umb.cs.cs680.hw02;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class DrawerClosedPlayingTest {
	DVDPlayer player;
	State drawerClosedPlaying;

	@Before
	public void initalize() {
		player = DVDPlayer.getInstance();
		drawerClosedPlaying = DrawerClosedPlaying.getInstance(player);
		System.out.println("Testing functions for Drawer Closed Playing State");
	}
	
	@Test
	public void testOpenCloseButtonPushed() {
		System.out.println("Open Close Button Pushed");
		player.changeState(drawerClosedPlaying);
		drawerClosedPlaying.openCloseButtonPushed(player);
		assertEquals(player.getState().hashCode(),DrawerOpen.getInstance(player).hashCode());
	}
	
	@Test
	public void testPlayButtonPushed() {
		System.out.println("Play Button Pushed");
		player.changeState(drawerClosedPlaying);
		drawerClosedPlaying.playButtonPushed(player);
		assertEquals(player.getState().hashCode(), DrawerClosedPlaying.getInstance(player).hashCode());
	}
	
	@Test
	public void testStopButtonPushed() {
		System.out.println("Stop Button Pushed");
		player.changeState(drawerClosedPlaying);
		drawerClosedPlaying.stopButtonPushed(player);
		assertEquals(player.getState().hashCode(), DrawerClosedNotPlaying.getInstance(player).hashCode());
	}
	
}
