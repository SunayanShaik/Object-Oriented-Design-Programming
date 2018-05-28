package edu.umb.cs.cs680.hw08;

public interface Observer {
	
	public void update(Object args);

	public void updateStock(StockEvent stockEv);
	
	public void updateBond(BondEvent bondEv);
	
}
