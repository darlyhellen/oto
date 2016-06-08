/**上午11:02:09
 * @author zhangyh2
 * TemplateMethodTest.java
 * TODO
 */
package design.patterns.Behavioral.TemplateMethod;

import org.junit.Test;

/**
 * @author zhangyh2 TemplateMethodTest 上午11:02:09 TODO
 */
public class TemplateMethodTest {

	@Test
	public void templateMethod() {
		// 我跟踪下这个小程序的执行过程：首先将exp和"\\+"做参数，调用AbstractCalculator类里的calculate(String,String)方法，在calculate(String,String)里调用同类的split()，之后再调用calculate(int
		// ,int)方法，从这个方法进入到子类中，执行完return num1 +
		// num2后，将值返回到AbstractCalculator类，赋给result，打印出来。正好验证了我们开头的思路。
		String exp = "8+8";
		//父类实现对子类的调用
		AbstractCalculator cal = new Plus();
		double ex = cal.calculate(exp, "\\+");
		System.out.println(exp + "=" + ex);
	}
}
