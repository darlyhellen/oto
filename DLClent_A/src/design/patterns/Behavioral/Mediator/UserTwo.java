/**下午3:20:10
 * @author zhangyh2
 * UserOne.java
 * TODO
 */
package design.patterns.Behavioral.Mediator;

/** @author zhangyh2
 * UserOne
 * 下午3:20:10
 * TODO
 */
public class UserTwo extends User {

	/** 下午3:20:22
	 * @author zhangyh2
	 */
	public UserTwo(MediatorListener mediator) {
		super(mediator);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see design.patterns.Behavioral.Mediator.User#work()
	 */
	@Override
	public void work() {
		// TODO Auto-generated method stub
		System.out.println("UserTwo is work");
	}

}
