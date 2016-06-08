/**下午3:40:46
 * @author zhangyh2
 * StructuralTest.java
 * TODO
 */
package design.patterns.structural;

import org.junit.Test;

import design.patterns.structural.Adapter.adapter.Adapter;
import design.patterns.structural.Adapter.adapter.ObjectAdapter;
import design.patterns.structural.Adapter.adapter.Source;
import design.patterns.structural.Adapter.interfaceadapter.AdapterOne;
import design.patterns.structural.Adapter.interfaceadapter.AdapterTwo;
import design.patterns.structural.Adapter.interfaceadapter.Sourceable;
import design.patterns.structural.Bridge.Bridge;
import design.patterns.structural.Bridge.RSources;
import design.patterns.structural.Bridge.SSources;
import design.patterns.structural.Composite.Tree;
import design.patterns.structural.Composite.TreeNode;
import design.patterns.structural.Facade.Computer;

/**
 * @author zhangyh2 StructuralTest 下午3:40:46 TODO
 */
public class StructuralTest {

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
		// 接口的适配器模式
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

	@Test
	public void bridge() {
		// 就通过对Bridge类的调用，实现了对接口Sourceable的实现类SourceSub1和SourceSub2的调用。
		Bridge bridge = new Bridge();
		/* 调用第一个对象 */
		bridge.setSourceable(new RSources());
		bridge.brig();
		/* 调用第二个对象 */
		bridge.setSourceable(new SSources());
		bridge.brig();
	}

	@Test
	public void composite() {
		// 使用场景：将多个对象组合在一起进行操作，常用于表示树形结构中，例如二叉树，数等。
		Tree tree = new Tree("A");
		TreeNode b = new TreeNode("TreeNodeB");
		TreeNode c = new TreeNode("TreeNodeC");
		b.add(c);
		c.setParent(b);
		tree.root.add(b);
		b.setParent(tree.root);
		System.out.println("build the tree finished!");
	}

	@Test
	public void decorator() {
		/*
		 * 1、需要扩展一个类的功能。 2、动态的为一个对象增加功能，而且还能动态撤销。（继承不能做到这一点，继承的功能是静态的，不能动态增删。）
		 * 缺点：产生过多相似的对象，不易排错！
		 */
		design.patterns.structural.Decorator.Sourceable source = new design.patterns.structural.Decorator.Source();
		design.patterns.structural.Decorator.Sourceable dec = new design.patterns.structural.Decorator.DecoratorSource(
				source);
		dec.simplename();
	}

	@Test
	public void useCompulter() {
		// TODO Auto-generated method stub
		/*
		 * 如果我们没有Computer类，那么，CPU、Memory、Disk他们之间将会相互持有实例，产生关系，这样会造成严重的依赖，修改一个类，
		 * 可能会带来其他类的修改
		 * ，这不是我们想要看到的，有了Computer类，他们之间的关系被放在了Computer类里，这样就起到了解耦的作用，这，就是外观模式！
		 */
		Computer computer = new Computer();
		computer.startup();
		computer.shutdown();
	}

	@Test
	public void proxy() {
		/*
		 * 代理模式的应用场景： 如果已有的方法在使用的时候需要对原有的方法进行改进，此时有两种办法：
		 * 1、修改原有的方法来适应。这样违反了“对扩展开放，对修改关闭”的原则。
		 * 2、就是采用一个代理类调用原有的方法，且对产生的结果进行控制。这种方法就是代理模式。
		 * 使用代理模式，可以将功能划分的更加清晰，有助于后期维护！
		 */

		design.patterns.structural.Proxy.Sourceable pro = new design.patterns.structural.Proxy.Proxy();
		pro.aoth();
	}
}
