package edu.umb.cs.cs680.hw02;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class DrawerClosedNotPlayingTest {
	DVDPlayer player;
	State drawerClosedNotPlaying;

	@Before
	public void initalize() {
		player = DVDPlayer.getInstance();
		drawerClosedNotPlaying = DrawerClosedNotPlaying.getInstance(player);
		System.out.println("Testing functions for Drawer Closed Not Playing State");
	}
	
	@Test
	public void testOpenCloseButtonPushed() {
		System.out.println("Open Close Button Pushed");
		player.changeState(drawerClosedNotPlaying);
		drawerClosedNotPlaying.openCloseButtonPushed(player);
		assertEquals(player.getState().hashCode(),DrawerOpen.getInstance(player).hashCode());
	}
	
	@Test
	public void testPlayButtonPushed() {
		System.out.println("Play Button Pushed");
		player.changeState(drawerClosedNotPlaying);
		drawerClosedNotPlaying.playButtonPushed(player);
		assertEquals(player.getState().hashCode(), DrawerClosedPlaying.getInstance(player).hashCode());
	}
	
	@Test
	public void testPlayButtonPushedNoDVD() {
		System.out.println("Play Button Pushed No DVD");
		player.changeState(drawerClosedNotPlaying);
		player.setDvdExists(false);
		drawerClosedNotPlaying.playButtonPushed(player);
		assertEquals(player.getState().hashCode(), DrawerClosedNotPlaying.getInstance(player).hashCode());
	}
	
	@Test
	public void testStopButtonPushed() {
		System.out.println("Stop Button Pushed");
		player.changeState(drawerClosedNotPlaying);
		drawerClosedNotPlaying.stopButtonPushed(player);
		assertEquals(player.getState().hashCode(), DrawerClosedNotPlaying.getInstance(player).hashCode());
	}
}
