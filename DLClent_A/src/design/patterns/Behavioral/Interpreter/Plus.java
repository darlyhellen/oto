/**下午3:30:48
 * @author zhangyh2
 * Plus.java
 * TODO
 */
package design.patterns.Behavioral.Interpreter;

/**
 * @author zhangyh2 Plus 下午3:30:48 TODO
 */
public class Plus implements ExpressionListener {

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
		return context.getNum1()+context.getNum2();
	}

}
