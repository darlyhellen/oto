/**下午1:54:32
 * @author zhangyh2
 * ResponsibilityTest.java
 * TODO
 */
package design.patterns.Behavioral.Responsibility;

import org.junit.Test;

/**
 * @author zhangyh2 ResponsibilityTest 下午1:54:32 TODO
 */
public class ResponsibilityTest {

	@Test
	public void responsibility() {
		// 此处强调一点就是，链接上的请求可以是一条链，可以是一个树，还可以是一个环，模式本身不约束这个，需要我们自己去实现，同时，在一个时刻，命令只允许由一个对象传给另一个对象，而不允许传给多个对象。
		Response r1 = new Response("R1");
		Response r2 = new Response("R2");
		Response r3 = new Response("R3");

		r1.setHand(r2);
		r2.setHand(r3);

		r1.operator();

	}
}
