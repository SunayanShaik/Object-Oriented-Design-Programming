package edu.umb.cs.cs680.hw02;

public class DrawerClosedNotPlaying implements State {

	DVDPlayer player;
	private static State drawerClosedNotPlaying;
	
	private DrawerClosedNotPlaying(DVDPlayer dvdPlayer) {
		player = dvdPlayer;
	}
	
	public static State getInstance(DVDPlayer player) {
		if(drawerClosedNotPlaying == null) {
			drawerClosedNotPlaying = new DrawerClosedNotPlaying(player);
		}
		return drawerClosedNotPlaying;
	}

	@Override
	public void openCloseButtonPushed(DVDPlayer dvdPlayer) {
		player.open();
		player.changeState(DrawerOpen.getInstance(player));
	}

	@Override
	public void playButtonPushed(DVDPlayer dvdPlayer) {
		if (player.checkDvdExists()) {
			player.play();
			player.changeState(DrawerClosedPlaying.getInstance(player));
			return;
		}
		System.out.println("[ERROR]: Please insert DVD before playing..!!");
	}

	@Override
	public void stopButtonPushed(DVDPlayer dvdPlayer) {
		player.stop();
	}

}
