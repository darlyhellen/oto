/**下午3:19:13
 * @author zhangyh2
 * Mediator.java
 * TODO
 */
package design.patterns.Behavioral.Mediator;

/**
 * @author zhangyh2 Mediator 下午3:19:13 TODO
 */
public class Mediator implements MediatorListener {

	private UserOne one;

	private UserTwo two;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * design.patterns.Behavioral.Mediator.MediatorListener#createMediator()
	 */
	@Override
	public void createMediator() {
		// TODO Auto-generated method stub
		one = new UserOne(this);
		two = new UserTwo(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see design.patterns.Behavioral.Mediator.MediatorListener#workAll()
	 */
	@Override
	public void workAll() {
		// TODO Auto-generated method stub
		one.work();
		two.work();
	}

}
