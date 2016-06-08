/**上午10:43:25
 * @author zhangyh2
 * Source.java
 * TODO
 */
package design.patterns.structural.Decorator;

/**
 * @author zhangyh2 Source 上午10:43:25 TODOSource类是被装饰类
 */
public class Source implements Sourceable {

	/*
	 * (non-Javadoc)
	 * 
	 * @see design.patterns.structural.Decorator.Sourceable#simplename()
	 */
	@Override
	public void simplename() {
		// TODO Auto-generated method stub
		System.out.println("Source类是被装饰类");
	}

}
