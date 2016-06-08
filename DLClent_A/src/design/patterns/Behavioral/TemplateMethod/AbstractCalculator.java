/**上午10:54:47
 * @author zhangyh2
 * AbstractCalculator.java
 * TODO
 */
package design.patterns.Behavioral.TemplateMethod;

/**
 * @author zhangyh2 AbstractCalculator 上午10:54:47 TODO
 */
public abstract class AbstractCalculator {
	/* 主方法，实现对本类其它方法的调用 */
	public final double calculate(String exp, String opt) {
		double arr[] = split(exp, opt);
		return calculate(arr[0], arr[1]);
	};

	/**
	 * 上午10:59:12
	 * 
	 * @author zhangyh2 TODO
	 */
	public abstract double calculate(double d, double e);

	public double[] split(String exp, String opt) {
		String array[] = exp.split(opt);
		double arrayInt[] = new double[2];
		arrayInt[0] = Double.parseDouble(array[0]);
		arrayInt[1] = Double.parseDouble(array[1]);
		return arrayInt;
	}
}
