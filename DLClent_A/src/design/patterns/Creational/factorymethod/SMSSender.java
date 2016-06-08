/**下午3:41:23
 * @author zhangyh2
 * SMSSender.java
 * TODO
 */
package design.patterns.Creational.factorymethod;

/**
 * @author zhangyh2 SMSSender 下午3:41:23 TODO 工厂封装实现类
 */
public class SMSSender implements Sender {

	/*
	 * (non-Javadoc)
	 * 
	 * @see design.patterns.factorymethod.Sender#send()
	 */
	@Override
	public void send() {
		// TODO Auto-generated method stub
		System.out.println("SMSSender");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see design.patterns.factorymethod.Sender#shape()
	 */
	@Override
	public void shape() {
		// TODO Auto-generated method stub
		System.out.println("方形");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see design.patterns.factorymethod.Sender#smaile()
	 */
	@Override
	public void smaile() {
		// TODO Auto-generated method stub
		System.out.println("咸味");
	}

}
