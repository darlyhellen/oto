/**上午11:24:22
 * @author zhangyh2
 * BridgeTest.java
 * TODO
 */
package design.patterns.structural.Bridge;

import org.junit.Test;

/**
 * @author zhangyh2 BridgeTest 上午11:24:22 TODO
 */
public class BridgeTest {

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
}
