/**下午2:05:59
 * @author zhangyh2
 * Command.java
 * TODO
 */
package design.patterns.Behavioral.Command;

/**
 * @author zhangyh2 Command 下午2:05:59 TODO Command是命令，实现了Command接口，持有接收对象
 */
public class ReceiverCommand implements CommandListener {

	private Receiver receiver;

	public ReceiverCommand(Receiver receiver) {
		super();
		this.receiver = receiver;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see design.patterns.Behavioral.Command.CommandListener#exe()
	 */
	@Override
	public void exe() {
		// TODO Auto-generated method stub
		receiver.action();
	}

}
