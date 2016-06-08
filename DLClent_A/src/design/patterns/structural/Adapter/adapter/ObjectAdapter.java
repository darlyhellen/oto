/**上午10:24:42
 * @author zhangyh2
 * ObjectAdapter.java
 * TODO
 */
package design.patterns.structural.Adapter.adapter;

/** @author zhangyh2
 * ObjectAdapter
 * 上午10:24:42
 * TODO
 * Adapter不进行继承Source，而是直接进行初始化一个Source对象,如:ObjectAdapter
 */
public class ObjectAdapter implements Targetable {
	private Source source;
	
	public ObjectAdapter(Source source) {
		super();
		this.source = source;
	}

	/* (non-Javadoc)
	 * @see design.patterns.structural.Adapter.Targetable#showInfo()
	 */
	@Override
	public void showInfo() {
		// TODO Auto-generated method stub
		source.showInfo();
		
	}

	/* (non-Javadoc)
	 * @see design.patterns.structural.Adapter.Targetable#showTime()
	 */
	@Override
	public void showTime() {
		// TODO Auto-generated method stub
		System.out.println("ObjectAdapter");
	}

}
