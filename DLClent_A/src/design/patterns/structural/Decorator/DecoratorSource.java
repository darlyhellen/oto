/**上午10:44:01
 * @author zhangyh2
 * DecoratorSource.java
 * TODO
 */
package design.patterns.structural.Decorator;

/**
 * @author zhangyh2 DecoratorSource 上午10:44:01 TODO Decorator类是一个装饰类
 */
public class DecoratorSource implements Sourceable {

	private Sourceable source;

	public DecoratorSource(Sourceable source) {
		super();
		this.source = source;
	}

	/*
	 * 
	 * (non-Javadoc)
	 * 
	 * @see design.patterns.structural.Decorator.Sourceable#simplename()
	 */
	@Override
	public void simplename() {
		// TODO Auto-generated method stub
		System.out.println("before decorator!");
		source.simplename();
		System.out.println("after decorator!");
	}

}
