/**上午10:56:26
 * @author zhangyh2
 * Proxy.java
 * TODO
 */
package design.patterns.structural.Proxy;

/**
 * @author zhangyh2 Proxy 上午10:56:26 TODO
 */
public class Proxy implements Sourceable {

	private Source source;

	public Proxy() {
		super();
		this.source = new Source();
	}

	private void before() {
		System.out.println("before");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see design.patterns.structural.Proxy.Sourceable#aoth()
	 */
	@Override
	public void aoth() {
		// TODO Auto-generated method stub
		before();
		source.aoth();
		end();
	}

	private void end() {
		System.out.println("end");
	}

}
