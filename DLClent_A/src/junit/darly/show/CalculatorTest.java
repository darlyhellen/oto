/**下午2:36:03
 * @author zhangyh2
 * CalculatorTest.java
 * TODO
 */
package junit.darly.show;

import static org.junit.Assert.assertEquals;
import junit.darly.show.Account.AddThread;
import junit.darly.show.Account.WithdrawThread;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
/* 我们在测试的时候使用的一系列assertEquals方法就来自这个包
 大家注意一下，这是一个静态包含（static），是JDK5中新增添的一个功能。也就是说，assertEquals是Assert类中的一系列的静态方法，一般的使用方式是Assert.
 assertEquals()，但是使用了静态包含后，前面的类名就可以省略了，使用起来更加的方便。
 */
import org.junit.Test;

/**
 * @author zhangyh2 CalculatorTest 下午2:36:03 TODO
 */
public class CalculatorTest {

	private static Calculator calculator = new Calculator();

	/**
	 * 下午2:58:48
	 * 
	 * @author zhangyh2 TODO在任何一个测试执行之前必须执行的代码
	 */
	@Before
	public void setUp() throws Exception {
		calculator.clear();
	}

	/**
	 * Test method for {@link junit.darly.show.Calculator#add(int)}.
	 */
	@Test
	public void testAdd() {
		calculator.add(2);
		calculator.add(3);
		assertEquals(5, calculator.getResult());
	}

	/**
	 * Test method for {@link junit.darly.show.Calculator#substract(int)}.
	 */
	@Test
	public void testSubstract() {
		calculator.add(10);
		calculator.substract(2);
		assertEquals(8, calculator.getResult());
	}

	/**
	 * 下午2:55:29
	 * 
	 * @author zhangyh2 TODO某些方法尚未完成，暂不参与此次测试
	 */
	@Ignore
	public void testMultiply() {
	}

	/**
	 * Test method for {@link junit.darly.show.Calculator#divide(int)}.
	 */
	@Test(timeout = 400, expected = RuntimeException.class)
	public void testDivide() {
		calculator.add(8);
		calculator.divide(2);
		calculator.squareRoot(200);
		assertEquals(4, calculator.getResult());
	}

	@Test(expected = ArithmeticException.class)
	public void divideByZero() {
		calculator.divide(0);
	}

	/**
	 * 下午2:59:45
	 * 
	 * @author zhangyh2 TODO
	 */
	@After
	public void setDown() {
		// TODO Auto-generated method stub
		calculator.clear();
	}

	@Test
	public void account() {
		try {
			Account account = new Account(1000);
			Thread a = new Thread(new AddThread(account, 20), "add");
			Thread b = new Thread(new WithdrawThread(account, 20), "withdraw");
			a.start();
			b.start();
			a.join();
			b.join();
			System.out.println(account.getBalance());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
