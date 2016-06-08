/**上午10:15:38
 * @author zhangyh2
 * Adapter.java
 * TODO
 */
package design.patterns.structural.Adapter.adapter;

/**
 * @author zhangyh2 Adapter 上午10:15:38
 *         TODO核心思想就是：有一个Source类，拥有一个方法，待适配，目标接口时Targetable
 *         ，通过Adapter类，将Source的功能扩展到Targetable里 Adapter类继承Source类，实现Targetable接口
 */
public class Adapter extends Source implements Targetable {

	/*
	 * (non-Javadoc)
	 * 
	 * @see design.patterns.structural.Adapter.Targetable#showTime()
	 */
	@Override
	public void showTime() {
		// TODO Auto-generated method stub
		System.out.println("Adapter");
	}
	
	
	//---------------------上面是普通适配器模式-------------------------
	//---------------------下面是对象适配器模式-------------------------
	//Adapter不进行继承Source，而是直接进行初始化一个Source对象
	

}
