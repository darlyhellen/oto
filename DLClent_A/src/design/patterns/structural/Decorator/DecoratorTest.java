/**上午10:47:27
 * @author zhangyh2
 * DecoratorTest.java
 * TODO
 */
package design.patterns.structural.Decorator;

import org.junit.Test;

/**
 * @author zhangyh2 DecoratorTest 上午10:47:27 TODO
 */
public class DecoratorTest {

	/**
	 * 上午10:47:38
	 * 
	 * @author zhangyh2 TODO
	 */
	@Test
	public void decorator() {
		/*
		 * 1、需要扩展一个类的功能。 2、动态的为一个对象增加功能，而且还能动态撤销。（继承不能做到这一点，继承的功能是静态的，不能动态增删。）
		 * 缺点：产生过多相似的对象，不易排错！
		 */
		Sourceable source = new Source();
		Sourceable dec = new DecoratorSource(source);
		dec.simplename();
	}
}
