package edu.umb.cs.cs680.hw08;

public class BondEvent {

	String ticker;
	float bond;
	
	public BondEvent(String ticker, float bond) {
		this.ticker = ticker;
		this.bond = bond;
	}

	public String getTicker() {
		return ticker;
	}

	public float getBond() {
		return bond;
	}
	
}
