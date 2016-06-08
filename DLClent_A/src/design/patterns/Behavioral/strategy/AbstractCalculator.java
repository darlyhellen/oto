/**上午10:21:52
 * @author zhangyh2
 * AbstractCalculator.java
 * TODO
 */
package design.patterns.Behavioral.strategy;

/**
 * @author zhangyh2 AbstractCalculator 上午10:21:52 TODO
 *         AbstractCalculator是辅助类，提供辅助方法
 */
public abstract class AbstractCalculator {

	public double[] split(String exp, String opt) {
		String array[] = exp.split(opt);
		double arrayInt[] = new double[2];
		arrayInt[0] = Double.parseDouble(array[0]);
		arrayInt[1] = Double.parseDouble(array[1]);
		return arrayInt;
	}
}
