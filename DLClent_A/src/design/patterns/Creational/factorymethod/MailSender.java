/**下午3:40:43
 * @author zhangyh2
 * MailSender.java
 * TODO
 */
package design.patterns.Creational.factorymethod;

/**
 * @author zhangyh2 MailSender 下午3:40:43 TODO 工厂封装实现类
 */
public class MailSender implements Sender {

	/*
	 * (non-Javadoc)
	 * 
	 * @see design.patterns.factorymethod.Sender#send()
	 */
	@Override
	public void send() {
		// TODO Auto-generated method stub
		System.out.println("MailSender");
	}

	/* (non-Javadoc)
	 * @see design.patterns.factorymethod.Sender#shape()
	 */
	@Override
	public void shape() {
		// TODO Auto-generated method stub
		System.out.println("圆形");
	}

	/* (non-Javadoc)
	 * @see design.patterns.factorymethod.Sender#smaile()
	 */
	@Override
	public void smaile() {
		// TODO Auto-generated method stub
		System.out.println("甜味");
	}

}
