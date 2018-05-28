package edu.umb.cs.cs680.hw08;

import java.util.HashMap;

public class TableObserver implements Observer {
	
	private HashMap<String, Float> tableStocks = new HashMap<String, Float>();
	private HashMap<String, Float> tableBonds = new HashMap<String, Float>();

	public void updateBond(BondEvent bondEv) {
		tableBonds.put(bondEv.getTicker(), bondEv.getBond());
	}

	public void updateStock(StockEvent stockEv) {
		tableStocks.put(stockEv.getTicker(), stockEv.getQuote());
	}
	
	public void update(Object args) {
		if(args instanceof StockEvent) {
			updateStock((StockEvent) args);
		}
		
		if(args instanceof BondEvent) {
			updateBond((BondEvent) args);
		}
	}

	public HashMap<String, Float> getTableStocks() {
		return tableStocks;
	}

	public HashMap<String, Float> getTableBonds() {
		return tableBonds;
	}

}
