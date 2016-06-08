/**上午11:29:22
 * @author zhangyh2
 * ObserverTest.java
 * TODO
 */
package design.patterns.Behavioral.Observer;

import org.junit.Test;

/**
 * @author zhangyh2 ObserverTest 上午11:29:22 TODO
 */
public class ObserverTest {

	@Test
	public void observer() {
		// 这些东西，其实不难，只是有些抽象，不太容易整体理解，建议读者：根据关系图，新建项目，自己写代码（或者参考我的代码）,按照总体思路走一遍，这样才能体会它的思想，理解起来容易！
		SubjectListener sub = new Subject();
		sub.addObserver(new UserOne());
		sub.addObserver(new UserTwo());
		sub.operation();
	}
}
