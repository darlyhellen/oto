/**上午11:01:09
 * @author zhangyh2
 * Plus.java
 * TODO
 */
package design.patterns.Behavioral.TemplateMethod;

/**
 * @author zhangyh2 Plus 上午11:01:09 TODO
 */
public class Plus extends AbstractCalculator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * design.patterns.Behavioral.TemplateMethod.AbstractCalculator#calculate
	 * (double, double)
	 */
	@Override
	public double calculate(double d, double e) {
		// TODO Auto-generated method stub
		return d + e;
	}

}
