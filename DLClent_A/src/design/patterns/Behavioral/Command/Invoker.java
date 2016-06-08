/**下午2:09:36
 * @author zhangyh2
 * Invoker.java
 * TODO
 */
package design.patterns.Behavioral.Command;

/**
 * @author zhangyh2 Invoker 下午2:09:36 TODO Invoker是调用者（司令员）
 */

public class Invoker {

	private CommandListener command;

	public Invoker(CommandListener command) {
		super();
		this.command = command;
	}

	public void aciton() {
		System.out.println("司令员发出指令");
		command.exe();
	}

}
