/**下午2:16:31
 * @author zhangyh2
 * Ephor.java
 * TODO
 */
package design.patterns.Behavioral.Command;

/**
 * @author zhangyh2 Ephor 下午2:16:31 TODO ephor长官，中间成员
 */
public class Ephor {

	private CommandListener command;

	public Ephor(CommandListener command) {
		super();
		this.command = command;
	}

	public void res() {
		System.out.println("长官收到指令");
	}

	public void action() {
		System.out.println("长官开始传达指令");
		command.exe();
	}
}
