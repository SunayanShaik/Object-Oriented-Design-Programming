package edu.umb.cs.cs680.hw02;

public class DrawerOpen implements State {

	DVDPlayer player;
	private static State drawerOpen;

	private DrawerOpen(DVDPlayer dvdPlayer) {
		player = dvdPlayer;
	}

	public static State getInstance(DVDPlayer player) {
		if (drawerOpen == null) {
			drawerOpen = new DrawerOpen(player);
		}
		return drawerOpen;
	}

	@Override
	public void openCloseButtonPushed(DVDPlayer dvdPlayer) {
		player.close();
		player.changeState(DrawerClosedNotPlaying.getInstance(player));
	}

	@Override
	public void playButtonPushed(DVDPlayer dvdPlayer) {
		player.close();
		if (player.checkDvdExists()) {
			player.play();
			player.changeState(DrawerClosedPlaying.getInstance(player));
			return;
		}
		System.out.println("[ERROR]: Please insert DVD before playing..!!");
		player.changeState(DrawerClosedNotPlaying.getInstance(player));
	}

	@Override
	public void stopButtonPushed(DVDPlayer dvdPlayer) {
		player.stop();
	}
}
