package edu.umb.cs.cs680.hw02;

public class DVDPlayer {

	private static DVDPlayer player;
	private static State state;
	private static State drawerOpen;
	private static State drawerClosedPlaying;
	private static State drawerClosedNotPlaying;
	private static boolean dvdExist;

	public DVDPlayer() {
		player = null;

		drawerOpen = DrawerOpen.getInstance(this);
		drawerClosedPlaying = DrawerClosedPlaying.getInstance(this);
		drawerClosedNotPlaying = DrawerClosedNotPlaying.getInstance(this);
		state = drawerClosedNotPlaying;
		dvdExist = true;
	}

	public static DVDPlayer getInstance() {
		if(player == null) {
			player = new DVDPlayer();
		}
		return player;
	}

	public boolean checkDvdExists() {
		return dvdExist;
	}
	
	public void setDvdExists(boolean dvdExist) {
		this.dvdExist = dvdExist;
	}
	
	public void changeState(State state) {
		DVDPlayer.state = state;
	}
	
	public State getState() {
		return this.state;
	}
	
	public void openCloseButtonPushed() {
		state.openCloseButtonPushed(this);
	}

	public void playButtonPushed() {
		state.playButtonPushed(this);
	}
	
	public void stopButtonPushed() {
		state.stopButtonPushed(this);
	}

	public void open() {
		System.out.println("The DVD Player is opened...");
	}

	public void close() {
		System.out.println("The DVD Player is closed...");
	}

	public void play() {
		System.out.println("The DVD Player is playing...");
				
	}

	public void stop() {
		System.out.println("The DVD Player is stopped...");
	}
}
