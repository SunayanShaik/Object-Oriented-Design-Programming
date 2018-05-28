package edu.umb.cs.cs680.hw02;

public class DVDPlayerDemo {

	public static void main(String[] args) {
		DVDPlayer player = new DVDPlayer();
		
		System.out.println("********************DrawerClosedNotPlaying********************");
		System.out.println("\nDVDPlayer DrawerClosedNotPlaying and playButtonPushed: ");
		player.playButtonPushed();	
		
		System.out.println("\nDVDPlayer DrawerClosedNotPlaying and openCloseButtonPushed: ");
		player.openCloseButtonPushed();
		
		System.out.println("\nDVDPlayer DrawerClosedNotPlaying and stopButtonPushed: ");
		player.stopButtonPushed();
		
		System.out.println("********************DrawerOpen********************");
		System.out.println("\nDVDPlayer DrawerOpen and openCloseButtonPushed: ");
		player.changeState(DrawerOpen.getInstance(player));
		player.openCloseButtonPushed();
		
		System.out.println("\nDVDPlayer DrawerOpen and playButtonPushed: ");
		player.changeState(DrawerOpen.getInstance(player));
		player.playButtonPushed();
		
		System.out.println("\nDVDPlayer DrawerOpen and stopButtonPushed: ");
		player.changeState(DrawerOpen.getInstance(player));
		player.stopButtonPushed();
		
		System.out.println("********************DrawerClosedPlaying********************");
		System.out.println("\nDVDPlayer DrawerClosedPlaying and stopButtonPushed");
		player.changeState(DrawerClosedPlaying.getInstance(player));
		player.stopButtonPushed();
		
		System.out.println("\nDVDPlayer DrawerClosedPlaying and openCloseButtonPushed");
		player.changeState(DrawerClosedPlaying.getInstance(player));
		player.openCloseButtonPushed();
		
		System.out.println("\nDVDPlayer DrawerClosedPlaying and playButtonPushed");
		player.changeState(DrawerClosedPlaying.getInstance(player));
		player.playButtonPushed();
		
	}
	
}
