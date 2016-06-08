/**下午3:40:31
 * @author zhangyh2
 * CreationalTest.java
 * TODO
 */
package design.patterns.Creational;

import org.junit.Test;

import design.patterns.Creational.AbstractFactory.CakeMode;
import design.patterns.Creational.AbstractFactory.WnsProduceCake;
import design.patterns.Creational.AbstractFactory.YdnProduceCake;
import design.patterns.Creational.Builder.Builder;
import design.patterns.Creational.Builder.Paper;
import design.patterns.Creational.factorymethod.Sender;
import design.patterns.Creational.factorymethod.SenderFactory;

/**
 * @author zhangyh2 CreationalTest 下午3:40:31 TODO
 */
public class CreationalTest {

	@Test
	public void abstractFactory() {
		/*
		 * 其实这个模式的好处就是，如果你现在想增加一个功能：发及时信息，则只需做一个实现类，实现CakeMode.java接口，同时做一个工厂类，
		 * 实现ProduceCake.java接口 ，就OK了，无需去改动现成的代码。这样做，拓展性较好！
		 */
		WnsProduceCake wns = new WnsProduceCake();
		CakeMode wnsC = wns.produce();
		wnsC.name();
		wnsC.shape();
		wnsC.material();
		YdnProduceCake ydn = new YdnProduceCake();
		CakeMode ydnC = ydn.produce();
		ydnC.name();
		ydnC.shape();
		ydnC.material();
	}
	
	@Test
	public void builder() {

		Builder builder = new Builder();
		builder.wns(10);
		builder.ydn(10);
		for (Paper paper : builder.getData()) {
			paper.name();
			paper.shape();
			paper.material();
		}
	}
	
	
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
