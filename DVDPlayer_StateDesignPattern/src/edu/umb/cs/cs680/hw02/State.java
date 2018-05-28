package edu.umb.cs.cs680.hw02;

public interface State {
	
	public void openCloseButtonPushed(DVDPlayer dvdPlayer);
	
	public void playButtonPushed(DVDPlayer dvdPlayer);
	
	public void stopButtonPushed(DVDPlayer dvdPlayer);
	
}
