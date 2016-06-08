/**下午3:31:33
 * @author zhangyh2
 * Minus.java
 * TODO
 */
package design.patterns.Behavioral.Interpreter;

/**
 * @author zhangyh2 Minus 下午3:31:33 TODO
 */
public class Minus implements ExpressionListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * design.patterns.Behavioral.Interpreter.ExpressionListener#interpret(design
	 * .patterns.Behavioral.Interpreter.Context)
	 */
	@Override
	public int interpret(Context context) {
		// TODO Auto-generated method stub
		return context.getNum1() - context.getNum2();
	}

}
