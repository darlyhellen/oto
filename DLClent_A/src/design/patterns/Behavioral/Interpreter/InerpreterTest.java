/**下午3:32:27
 * @author zhangyh2
 * InerpreterTest.java
 * TODO
 */
package design.patterns.Behavioral.Interpreter;

import org.junit.Test;

/**
 * @author zhangyh2 InerpreterTest 下午3:32:27 TODO 一般主要应用在OOP开发中的编译器的开发中，所以适用面比较窄。
 */
public class InerpreterTest {

	@Test
	public void inerpreter() {
		// 计算9+2-8的值
		int res = new Minus().interpret(new Context(new Plus()
				.interpret(new Context(9, 2)), 8));
		System.out.println(res);
	}
}
