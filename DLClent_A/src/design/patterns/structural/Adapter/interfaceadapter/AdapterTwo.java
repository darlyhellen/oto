/**上午10:33:54
 * @author zhangyh2
 * AdapterOne.java
 * TODO
 */
package design.patterns.structural.Adapter.interfaceadapter;

/**
 * @author zhangyh2 AdapterOne 上午10:33:54 TODO
 */
public class AdapterTwo extends InterAdapter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * design.patterns.structural.Adapter.interfaceadapter.InterAdapter#types()
	 */
	@Override
	public void plays() {
		// TODO Auto-generated method stub
		super.types();
		System.out.println("AdapterTwo");
	}
}
