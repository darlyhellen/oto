/**上午10:29:37
 * @author zhangyh2
 * StrategyTest.java
 * TODO
 */
package design.patterns.Behavioral.strategy;

import java.util.Random;

import org.junit.Test;

/**
 * @author zhangyh2 StrategyTest 上午10:29:37 TODO
 */
public class StrategyTest {
	@Test
	public void strategy() {
		// 策略模式的决定权在用户，系统本身提供不同算法的实现，新增或者删除算法，对各种算法做封装。因此，策略模式多用在算法决策系统中，外部用户只需要决定用哪个算法即可。
		String[] ca = { "+", "-", "*", "/" };
		String temp = new Random().nextInt(9) + ca[new Random().nextInt(4)]
				+ new Random().nextInt(9);
		ICalculator calculator = null;
		if (temp != null && temp.length() > 0) {
			if (temp.contains("+")) {
				calculator = new Plus();
			} else if (temp.contains("-")) {
				calculator = new Minus();
			} else if (temp.contains("*")) {
				calculator = new Multiply();
			} else if (temp.contains("/")) {
				calculator = new Division();
			} else {
				System.out.println("not simple math");
				return;
			}
			double extp = calculator.calculator(temp);
			System.out.println(temp + "=" + extp);
		} else {
			System.out.println("not data");
		}

	}
}
