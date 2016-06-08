/**上午10:16:58
 * @author zhangyh2
 * AdapterTest.java
 * TODO
 */
package design.patterns.structural.Adapter;

import org.junit.Test;

import design.patterns.structural.Adapter.adapter.Adapter;
import design.patterns.structural.Adapter.adapter.ObjectAdapter;
import design.patterns.structural.Adapter.adapter.Source;
import design.patterns.structural.Adapter.interfaceadapter.AdapterOne;
import design.patterns.structural.Adapter.interfaceadapter.AdapterTwo;
import design.patterns.structural.Adapter.interfaceadapter.Sourceable;

/**
 * @author zhangyh2 AdapterTest 上午10:16:58 TODO
 *         类的适配器模式：当希望将一个类转换成满足另一个新接口的类时，可以使用类的适配器模式，创建一个新类，继承原有的类，实现新的接口即可。
 *         对象的适配器模式
 *         ：当希望将一个对象转换成满足另一个新接口的对象时，可以创建一个Wrapper类，持有原类的一个实例，在Wrapper类的方法中
 *         ，调用实例的方法就行。
 *         接口的适配器模式：当不希望实现一个接口中所有的方法时，可以创建一个抽象类Wrapper，实现所有方法，我们写别的类的时候，继承抽象类即可。
 */
public class AdapterTest {
	@Test
	public void adapter() {
		// 这样Targetable接口的实现类就具有了Source类的功能
		Adapter targetable = new Adapter();
		targetable.showInfo();
		targetable.showTime();
	}

	@Test
	public void object() {
		// 输出与第一种一样，只是适配的方法不同而已
		ObjectAdapter adapter = new ObjectAdapter(new Source());
		adapter.showInfo();
		adapter.showTime();
	}

	@Test
	public void inter() {
		//接口的适配器模式
		Sourceable one = new AdapterOne();
		Sourceable two = new AdapterTwo();
		one.types();
		one.hides();
		one.hosts();
		one.plays();
		two.types();
		two.hides();
		two.hosts();
		two.plays();
	}

}
