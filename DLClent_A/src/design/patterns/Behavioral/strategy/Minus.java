/**上午10:26:54
 * @author zhangyh2
 * Minus.java
 * TODO
 */
package design.patterns.Behavioral.strategy;

/** @author zhangyh2
 * Minus
 * 上午10:26:54
 * TODO
 */
public class Minus extends AbstractCalculator implements ICalculator{

	/* (non-Javadoc)
	 * @see design.patterns.Behavioral.strategy.ICalculator#calculator(java.lang.String)
	 */
	@Override
	public double calculator(String exp) {
		// TODO Auto-generated method stub
		double arrayInt[] = split(exp, "\\-");
		return arrayInt[0] - arrayInt[1];
	}

}
