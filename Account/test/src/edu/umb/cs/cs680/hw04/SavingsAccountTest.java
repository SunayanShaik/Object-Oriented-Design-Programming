package edu.umb.cs.cs680.hw04;

import static org.junit.Assert.*;

import org.junit.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

public class SavingsAccountTest {

	private SavingsAccount savingsAccount;
	
	@Before
	public void setInitialAccountBalance() {
		savingsAccount = new SavingsAccount(100);
		
	}
	
	@After
	public void resetAccountBalance() {
		savingsAccount = new SavingsAccount(0);
	}
	
	@Test
	public void testConstructor() {
		float savingsAccountBalance = 67;
		SavingsAccount savingsAccountOne = new SavingsAccount(savingsAccountBalance);
		float expected = savingsAccountOne.getBalance();
		assertThat(savingsAccountBalance, is(expected));
	}

	@Test
	public void withdrawalWhenBalanceIsGreater() throws InsufficientFundsException {
		float expected = 80;
		float actual = savingsAccount.withdraw(20);
		assertThat(actual, is(expected));
	}
	
	@Test(expected=InsufficientFundsException.class)
	public void withdrawalWhenBalanceIsLow() throws InsufficientFundsException {
		savingsAccount.withdraw(105);
	}
	
	@Test(expected=InsufficientFundsException.class)
	public void withdrawalWhenBalanceIsLessThanZero() throws InsufficientFundsException {
		savingsAccount = new SavingsAccount(-5);
		savingsAccount.withdraw(120);
	}
	
	@Test
	public void testToDepositBalance() {
		float expected = 110;
		float actual = savingsAccount.deposit(10);
		assertThat(actual, is(expected));
	}
	
}
