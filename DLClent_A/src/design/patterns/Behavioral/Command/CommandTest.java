/**下午2:12:04
 * @author zhangyh2
 * CommandTest.java
 * TODO
 */
package design.patterns.Behavioral.Command;

import org.junit.Test;

/**
 * @author zhangyh2 CommandTest 下午2:12:04 TODO
 */
public class CommandTest {

	@Test
	public void command() {

		Receiver receiver = new Receiver();

		CommandListener commands = new ReceiverCommand(receiver);

		Ephor ephor = new Ephor(commands);

		CommandListener com = new EphorCommand(ephor);

		Invoker invoker = new Invoker(com);

		invoker.aciton();
	}
}
