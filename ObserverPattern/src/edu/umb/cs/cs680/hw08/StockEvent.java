package edu.umb.cs.cs680.hw08;

public class StockEvent {

	String ticker;
	float quote;
	
	public StockEvent(String ticker, float quote) {
		this.ticker = ticker;
		this.quote = quote;
	}

	public String getTicker() {
		return ticker;
	}

	public float getQuote() {
		return quote;
	}
	
}
