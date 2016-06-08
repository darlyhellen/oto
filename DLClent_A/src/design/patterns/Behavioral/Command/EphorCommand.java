/**下午2:19:02
 * @author zhangyh2
 * EphorCommand.java
 * TODO
 */
package design.patterns.Behavioral.Command;


/**
 * @author zhangyh2 EphorCommand 下午2:19:02 TODO
 */
public class EphorCommand implements CommandListener {

	private Ephor ephor;

	public EphorCommand(Ephor ephor) {
		super();
		this.ephor = ephor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see design.patterns.Behavioral.Command.CommandListener#exe()
	 */
	@Override
	public void exe() {
		// TODO Auto-generated method stub
		ephor.res();
		ephor.action();
	}

}
