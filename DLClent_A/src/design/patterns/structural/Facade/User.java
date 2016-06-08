/**上午11:13:19
 * @author zhangyh2
 * User.java
 * TODO
 */
package design.patterns.structural.Facade;

import org.junit.Test;

/**
 * @author zhangyh2 User 上午11:13:19 TODO
 */
public class User {

	/**
	 * 上午11:13:30
	 * 
	 * @author zhangyh2 TODO
	 */
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
}
