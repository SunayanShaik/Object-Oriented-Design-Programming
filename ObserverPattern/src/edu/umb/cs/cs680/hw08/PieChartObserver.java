package edu.umb.cs.cs680.hw08;

import java.util.HashMap;

public class PieChartObserver implements  Observer {
	
	private HashMap<String, Float> pieChartForStocks = new HashMap<String, Float>();
	private HashMap<String, Float> pieChartForBonds = new HashMap<String, Float>();

	public void updateStock(StockEvent stockEv) {
		pieChartForStocks.put(stockEv.getTicker(), stockEv.getQuote());
	}

	public void updateBond(BondEvent bondEv) {
		pieChartForBonds.put(bondEv.getTicker(), bondEv.getBond());
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

	public HashMap<String, Float> getPieChartForStocks() {
		return pieChartForStocks;
	}

	public HashMap<String, Float> getPieChartForBonds() {
		return pieChartForBonds;
	}

}
