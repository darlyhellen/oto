/**上午10:12:56
 * @author zhangyh2
 * Source.java
 * TODO
 */
package design.patterns.structural.Adapter.adapter;

/**
 * @author zhangyh2 Source 上午10:12:56
 *         TODO核心思想就是：有一个Source类，拥有一个方法，待适配，目标接口时Targetable
 *         ，通过Adapter类，将Source的功能扩展到Targetable里
 */
public class Source {

	/**
	 * 上午10:13:12
	 * 
	 * @author zhangyh2 TODO
	 */
	public void showInfo() {
		// TODO Auto-generated method stub
		System.out.println("Source ");
	}
}
