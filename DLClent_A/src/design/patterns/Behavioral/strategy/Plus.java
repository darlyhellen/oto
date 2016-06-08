/**上午10:25:27
 * @author zhangyh2
 * Plus.java
 * TODO
 */
package design.patterns.Behavioral.strategy;

/**
 * @author zhangyh2 Plus 上午10:25:27 TODO
 */
public class Plus extends AbstractCalculator implements ICalculator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * design.patterns.Behavioral.strategy.ICalculator#calculator(java.lang.
	 * String)
	 */
	@Override
	public double calculator(String exp) {
		// TODO Auto-generated method stub
		double arrayInt[] = split(exp, "\\+");
		return arrayInt[0] + arrayInt[1];
	}

}
