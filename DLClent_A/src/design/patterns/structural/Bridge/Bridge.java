/**上午11:21:15
 * @author zhangyh2
 * Bridge.java
 * TODO
 */
package design.patterns.structural.Bridge;

/**
 * @author zhangyh2 Bridge 上午11:21:15 TODO
 */
public class Bridge {

	private Sourceable sourceable;

	public void brig() {
		// TODO Auto-generated method stub
		sourceable.brig();
	}

	public Sourceable getSourceable() {
		return sourceable;
	}

	public void setSourceable(Sourceable sourceable) {
		this.sourceable = sourceable;
	}

}
