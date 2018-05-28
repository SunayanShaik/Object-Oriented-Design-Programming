package edu.umb.cs.cs680.hw04;

public class CheckingAccount extends Account{

SavingsAccount savingsAccount;

public CheckingAccount(float balance) {
		this.balance = balance;
	}
	
public void setSavingsAccount(SavingsAccount savingsAccount) {
		this.savingsAccount = savingsAccount;
	}

public SavingsAccount getSavingsAccount() {
	return this.savingsAccount;
}

public float withdraw(float w) throws InsufficientFundsException {
		if(this.getBalance() < 0) {
			throw new InsufficientFundsException("Insufficient Funds. Checking account balance is less than zero.");
		}
		
		if(this.getBalance() > w) {
			balance -= w;
			return this.getBalance();
		}
		
		if(savingsAccount == null) {
			throw new InsufficientFundsException("Insufficient Funds. Please check checking account balance. No savings account is associated with this checkings account.");
		}
		
		float totalSavingsAndCheckingBalance = savingsAccount.getBalance() + this.getBalance(); 
		
		if(totalSavingsAndCheckingBalance < w) {
			throw new InsufficientFundsException("Insufficient Funds. Please check savings account and checking account balance.");
		}else {
			float amountToWithdrawFromSavings = w - this.getBalance();
			savingsAccount.withdraw(amountToWithdrawFromSavings);
			balance = -50;
		}
		return this.getBalance();
	}
}
