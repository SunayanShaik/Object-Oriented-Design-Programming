package edu.umb.cs.cs680.hw04;

public abstract class Account {

	protected float balance;

	public float getBalance() {
		return this.balance;
	}
	
	public float deposit(float d) {
		return this.balance += d;
	}

	abstract float withdraw(float w) throws InsufficientFundsException;

}
