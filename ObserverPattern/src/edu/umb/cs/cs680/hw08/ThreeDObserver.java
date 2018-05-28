package edu.umb.cs.cs680.hw08;

import java.util.HashMap;

public class ThreeDObserver implements Observer {
	
	private HashMap<String, Float> threeDStocks = new HashMap<String, Float>();
	private HashMap<String, Float> threeDBonds = new HashMap<String, Float>();

	public void updateBond(BondEvent bondEv) {
		threeDBonds.put(bondEv.getTicker(), bondEv.getBond());
	}

	public void updateStock(StockEvent stockEv) {
		threeDStocks.put(stockEv.getTicker(), stockEv.getQuote());
	}
	
	@Override
	public void update(Object args) {
		if(args instanceof StockEvent) {
			updateStock((StockEvent) args);
		}
		
		if(args instanceof BondEvent) {
			updateBond((BondEvent) args);
		}
	}

	public HashMap<String, Float> getThreeDStocks() {
		return threeDStocks;
	}

	public HashMap<String, Float> getThreeDBonds() {
		return threeDBonds;
	}
	
}
