/**下午1:57:07
 * @author zhangyh2
 * a.java
 * TODO
 */
package junit.darly.show;

/**
 * @author zhangyh2 a 下午1:57:07 TODO
 */
public class Account {

	private int balance;

	public Account(int balance) {
		this.balance = balance;
	}

	public int getBalance() {
		return balance;
	}

	public void add(int num) {
		balance = balance + num;
	}

	public void withdraw(int num) {
		balance = balance - num;
	}

	static class AddThread implements Runnable {
		Account account;
		int amount;

		public AddThread(Account account, int amount) {
			this.account = account;
			this.amount = amount;
		}

		@Override
		public void run() {
			for (int i = 0; i < 200000; i++) {
				account.add(amount);
			}
		}
	}

	static class WithdrawThread implements Runnable {
		Account account;
		int amount;

		public WithdrawThread(Account account, int amount) {
			this.account = account;
			this.amount = amount;
		}

		@Override
		public void run() {
			for (int i = 0; i < 100000; i++) {
				account.withdraw(amount);
			}
		}
	}
}