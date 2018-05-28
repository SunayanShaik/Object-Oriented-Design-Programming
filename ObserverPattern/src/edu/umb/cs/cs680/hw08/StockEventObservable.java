package edu.umb.cs.cs680.hw08;

import java.util.HashMap;

public class StockEventObservable extends Observable {

	private HashMap<String, Float> stockMap = new HashMap<String, Float>();
	
	public void changeQuote(String t, float q) {
		stockMap.put(t, q);
		setChanged();
		notifyObservers(new StockEvent(t, q));
		clearChanged();
	}
	
	public void notifyObservers(Object arg) {
		
		if(super.hasChanged() == true) {
			for(Observer ob : observers) {
				System.out.println("Notifying observers on changes in Stock values.");
				ob.update((StockEvent) arg);
			}
		}
	}
	
	public HashMap<String, Float> getStocks() {
		return stockMap;
	}

}
