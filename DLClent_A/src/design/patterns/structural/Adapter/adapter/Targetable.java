/**上午10:14:12
 * @author zhangyh2
 * Targetable.java
 * TODO
 */
package design.patterns.structural.Adapter.adapter;

/**
 * @author zhangyh2 Targetable 上午10:14:12
 *         TODO核心思想就是：有一个Source类，拥有一个方法，待适配，目标接口时Targetable
 *         ，通过Adapter类，将Source的功能扩展到Targetable里
 */
public interface Targetable {

	/**
	 * 上午10:14:25
	 * 
	 * @author zhangyh2 TODO 与原类中的方法相同
	 */
	void showInfo();

	/**
	 * 上午10:15:04
	 * 
	 * @author zhangyh2 TODO 新类的方法
	 */
	void showTime();
}
