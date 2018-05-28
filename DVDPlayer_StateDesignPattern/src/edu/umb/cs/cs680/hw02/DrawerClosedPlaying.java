package edu.umb.cs.cs680.hw02;

public class DrawerClosedPlaying implements State{
	
	DVDPlayer player;
	private static State drawerClosedPlaying;
	
	private DrawerClosedPlaying(DVDPlayer dvdPlayer) {
		player = dvdPlayer;
	}
	
	public static State getInstance(DVDPlayer player) {
		if(drawerClosedPlaying == null) {
			drawerClosedPlaying = new DrawerClosedPlaying(player);
		}
		return drawerClosedPlaying;
	}

	@Override
	public void openCloseButtonPushed(DVDPlayer dvdPlayer) {
		player.stop();
		player.open();
		player.changeState(DrawerOpen.getInstance(player));
	}

	@Override
	public void playButtonPushed(DVDPlayer dvdPlayer) {
		player.play();
	}

	@Override
	public void stopButtonPushed(DVDPlayer dvdPlayer) {
		player.stop();
		player.changeState(DrawerClosedNotPlaying.getInstance(player));
	}

}
