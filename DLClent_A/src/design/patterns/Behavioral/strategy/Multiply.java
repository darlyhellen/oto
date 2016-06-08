/**上午10:27:40
 * @author zhangyh2
 * Multiply.java
 * TODO
 */
package design.patterns.Behavioral.strategy;

/** @author zhangyh2
 * Multiply
 * 上午10:27:40
 * TODO
 */
public class Multiply extends AbstractCalculator implements ICalculator{

	/* (non-Javadoc)
	 * @see design.patterns.Behavioral.strategy.ICalculator#calculator(java.lang.String)
	 */
	@Override
	public double calculator(String exp) {
		// TODO Auto-generated method stub
		double arrayInt[] = split(exp, "\\*");
		return arrayInt[0] * arrayInt[1];
	}

}
