package edu.umb.cs.cs680.hw04;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class CheckingAccountTest {
	
	private CheckingAccount checkingAccount;
	private SavingsAccount savingsAccount;

	@Before
	public void setInitialAccountBalance() {
		savingsAccount = new SavingsAccount(10);
		checkingAccount = new CheckingAccount(100);
		checkingAccount.setSavingsAccount(savingsAccount);
	}
	
	@After
	public void resetAccountBalance() {
		checkingAccount = new CheckingAccount(0);
		savingsAccount = new SavingsAccount(0);
	}
	
	@Test
	public void testConstructor() {
		float checkingAccountBalance = 37;
		CheckingAccount checkingAccountOne = new CheckingAccount(checkingAccountBalance);
		float expected = checkingAccountOne.getBalance();
		assertThat(checkingAccountBalance, is(expected));
		
	}
	
	@Test
	public void testAddingSavingsAccountBalance() {
		float savingsAccountBalance = 78;
		CheckingAccount checkingAccountOne = new CheckingAccount(87);
		SavingsAccount savingsAccountOne = new SavingsAccount(savingsAccountBalance);
		checkingAccountOne.setSavingsAccount(savingsAccountOne);
		float expectedSavingsBalance = checkingAccountOne.getSavingsAccount().getBalance();
		assertThat(expectedSavingsBalance, is(expectedSavingsBalance));
	}
	
	@Test
	public void checkTotalSavingsAndCheckingBalance() {
		float expected = 110;
		float actual = savingsAccount.getBalance() + checkingAccount.getBalance();
		assertThat(actual, is(expected));
	}
	
	@Test
	public void withdrawalWhenBalanceIsGreater() throws InsufficientFundsException {
		float expected = 50;
		float actual = checkingAccount.withdraw(50);
		assertThat(actual, is(expected));
	}
	
	@Test
	public void withdrawalWhenCheckingsAndSavingsBalanceIsEqual() throws InsufficientFundsException {
		float expected = -50;
		float actual = checkingAccount.withdraw(110);
		assertThat(actual, is(expected));
	}
	
	@Test
	public void withdrawalWhenCheckingsAndSavingsBalanceIsGreater() throws InsufficientFundsException {
		float expected = -50;
		float actual = checkingAccount.withdraw(107);
		assertThat(actual, is(expected));
	}
	
	@Test(expected = InsufficientFundsException.class)
	public void whentotalSavingsAndCheckingBalanceIsLessThanW() throws InsufficientFundsException {
		checkingAccount.withdraw(120);
	}
	
    @Test(expected = InsufficientFundsException.class)
	public void whenCheckingAccountDosentHaveSavingsAccountAndNoBalance() throws InsufficientFundsException {
		checkingAccount.setSavingsAccount(null);
		checkingAccount.withdraw(130);
	}
	
	@Test(expected = InsufficientFundsException.class)
	public void whenCheckingsAccountHasNegativeBalance() throws InsufficientFundsException {
		checkingAccount.withdraw(110);
		checkingAccount.withdraw(50);
	}
	
}
