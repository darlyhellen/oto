/**下午3:43:51
 * @author zhangyh2
 * Test.java
 * TODO
 */
package design.patterns.Creational.factorymethod;

import org.junit.Test;

/**
 * @author zhangyh2 Test 下午3:43:51 TODO 工厂模式测试类
 * 
 *         总体来说，工厂模式适合：凡是出现了大量的产品需要创建，并且具有共同的接口时，可以通过工厂方法模式进行创建。在以上的三种模式中，
 *         第一种如果传入的字符串有误
 *         ，不能正确创建对象，第三种相对于第二种，不需要实例化工厂类，所以，大多数情况下，我们会选用第三种——静态工厂方法模式。
 */
public class Tests {

	@Test
	public void test() {
		// 普通工厂模式
		/* 就是建立一个工厂类，对实现了同一接口的一些类进行实例的创建。 */
		SenderFactory factory = new SenderFactory();
		Sender sender = factory.getSend("");
		if (sender != null) {
			sender.send();
			sender.shape();
			sender.smaile();
		}
	}

	@Test
	public void testMores() {
		// 多个工厂方法模式
		/*
		 * 是对普通工厂方法模式的改进，在普通工厂方法模式中，如果传递的字符串出错，则不能正确创建对象，而多个工厂方法模式是提供多个工厂方法，分别创建对象
		 * 将上面的代码做下修改，改动下SendFactory类就行，
		 */
		SenderFactory factory = new SenderFactory();
		Sender sender = factory.produceSMS();
		sender.send();
		sender.shape();
		sender.smaile();
	}

	@Test
	public void testStatic() {
		// 静态工厂方法模式
		/* 将上面的多个工厂方法模式里的方法置为静态的，不需要创建实例，直接调用即可 */
		Sender sender = SenderFactory.produceMailStatic();
		sender.send();
		sender.shape();
		sender.smaile();
	}
}
