/**上午11:19:16
 * @author zhangyh2
 * UserOne.java
 * TODO
 */
package design.patterns.Behavioral.Observer;

/**
 * @author zhangyh2 UserOne 上午11:19:16 TODO
 */
public class UserTwo implements ObserverListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see design.patterns.Behavioral.Observer.ObserverListener#observer()
	 */
	@Override
	public void observer() {
		// TODO Auto-generated method stub
		System.out.println("UserTwo_observer");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see design.patterns.Behavioral.Observer.ObserverListener#obsupdata()
	 */
	@Override
	public void obsupdata() {
		// TODO Auto-generated method stub
		System.out.println("UserTwo_obsupdata has received!");
	}

}
