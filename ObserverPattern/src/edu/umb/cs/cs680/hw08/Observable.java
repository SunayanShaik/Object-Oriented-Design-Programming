package edu.umb.cs.cs680.hw08;

import java.util.LinkedHashSet;

public class Observable {

	protected LinkedHashSet<Observer> observers = new LinkedHashSet<Observer>();
	
	private boolean haschanged = false;
	
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	public void setChanged() {
		haschanged = true;
	}

	public boolean hasChanged() {
		return haschanged;
	}

	public void clearChanged() {
		haschanged = false;
	}

	public void notifyObservers() {
		notifyObservers(null);
	}

	public void notifyObservers(Object arg) {
		
	}

}
