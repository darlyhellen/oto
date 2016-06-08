/**下午4:25:19
 * @author zhangyh2
 * FacTest.java
 * TODO
 */
package design.patterns.Creational.AbstractFactory;

import org.junit.Test;

/**
 * @author zhangyh2 FacTest 下午4:25:19 TODO
 */
public class FacTest {
	@Test
	public void show() {
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

}
