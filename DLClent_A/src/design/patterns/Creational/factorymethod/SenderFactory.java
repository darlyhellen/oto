/**下午3:39:03
 * @author zhangyh2
 * SenderFactory.java
 * TODO
 */
package design.patterns.Creational.factorymethod;

/**
 * @author zhangyh2 SenderFactory 下午3:39:03 TODO 工厂主体类
 */
public class SenderFactory {

	/**
	 * 下午4:09:21
	 * 
	 * @author zhangyh2 TODO 普通工厂模式
	 */
	public Sender getSend(String type) {
		if ("mail".equalsIgnoreCase(type)) {
			return new MailSender();
		} else if ("sms".equalsIgnoreCase(type)) {
			return new SMSSender();
		} else {
			System.out.println("wrong type");
			return null;
		}
	}

	/**
	 * 下午4:09:31
	 * 
	 * @author zhangyh2 TODO多个工厂方法模式
	 */
	public Sender produceMail() {
		return new MailSender();
	}

	/**
	 * 下午4:09:33
	 * 
	 * @author zhangyh2 TODO多个工厂方法模式
	 */
	public Sender produceSMS() {
		return new SMSSender();
	}

	/**
	 * 下午4:09:37
	 * 
	 * @author zhangyh2 TODO静态工厂方法模式
	 */
	public static Sender produceMailStatic() {
		return new MailSender();
	}

	/**
	 * 下午4:09:39
	 * 
	 * @author zhangyh2 TODO静态工厂方法模式
	 */
	public static Sender produceSMSStatic() {
		return new SMSSender();
	}

}
