package edu.umb.cs.cs680.hw08;

import java.util.HashMap;

public class BondEventObservable extends Observable {

	private HashMap<String, Float> bondMap = new HashMap<String, Float>();

	public void changeQuote(String t, float q) {
		bondMap.put(t, q);
		setChanged();
		notifyObservers(new BondEvent(t, q));
		clearChanged();
	}

	public void notifyObservers(Object arg) {

		if (super.hasChanged() == true) {
			for (Observer ob : observers) {
				ob.update((BondEvent) arg);
				System.out.println("Notifying observers on changes in Bond values.");
			}
		}
	}

	public HashMap<String, Float> getBondMap() {
		return bondMap;
	}

}
