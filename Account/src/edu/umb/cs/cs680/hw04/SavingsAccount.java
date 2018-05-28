package edu.umb.cs.cs680.hw04;

public class SavingsAccount extends Account{
	
	public SavingsAccount(float balance) {
		this.balance = balance;
	}
	
	public float withdraw(float w) throws InsufficientFundsException {
		if(this.getBalance() < 0 || this.getBalance() < w) {
			throw new InsufficientFundsException("Insufficient Funds.");
		}
		
		balance -= w;
		return balance;
	}
}
