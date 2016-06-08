/**下午4:19:46
 * @author zhangyh2
 * WnsCake.java
 * TODO
 */
package design.patterns.Creational.Builder;

/**
 * @author zhangyh2 WnsCake 下午4:19:46 TODO
 */
public class YdnPaper implements Paper {

	/*
	 * (non-Javadoc)
	 * 
	 * @see design.patterns.AbstractFactory.CakeMode#name()
	 */
	@Override
	public void name() {
		// TODO Auto-generated method stub
		System.out.println("YdnCake");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see design.patterns.AbstractFactory.CakeMode#shape()
	 */
	@Override
	public void shape() {
		// TODO Auto-generated method stub
		System.out.println("女人");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see design.patterns.AbstractFactory.CakeMode#material()
	 */
	@Override
	public void material() {
		// TODO Auto-generated method stub
		System.out.println("肉松");
	}

}
