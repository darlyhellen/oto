/**上午10:28:36
 * @author zhangyh2
 * Division.java
 * TODO
 */
package design.patterns.Behavioral.strategy;

/**
 * @author zhangyh2 Division 上午10:28:36 TODO
 */
public class Division extends AbstractCalculator implements ICalculator {

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
		double arrayInt[] = split(exp, "\\/");
		return arrayInt[0] / arrayInt[1];
	}

}
