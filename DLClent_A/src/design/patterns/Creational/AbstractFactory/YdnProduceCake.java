/**下午4:23:35
 * @author zhangyh2
 * WnsProduceCake.java
 * TODO
 */
package design.patterns.Creational.AbstractFactory;

/**
 * @author zhangyh2 WnsProduceCake 下午4:23:35 TODO
 */
public class YdnProduceCake implements ProduceCake {

	/*
	 * (non-Javadoc)
	 * 
	 * @see design.patterns.AbstractFactory.ProduceCake#produce()
	 */
	@Override
	public CakeMode produce() {
		// TODO Auto-generated method stub
		return new YdnCake();
	}

}
