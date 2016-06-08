/**上午11:19:16
 * @author zhangyh2
 * UserOne.java
 * TODO
 */
package design.patterns.Behavioral.Observer;

/** @author zhangyh2
 * UserOne
 * 上午11:19:16
 * TODO
 */
public class UserOne implements ObserverListener{

	/* (non-Javadoc)
	 * @see design.patterns.Behavioral.Observer.ObserverListener#observer()
	 */
	@Override
	public void observer() {
		// TODO Auto-generated method stub
		System.out.println("UserOne_observer");
	}

	/* (non-Javadoc)
	 * @see design.patterns.Behavioral.Observer.ObserverListener#obsupdata()
	 */
	@Override
	public void obsupdata() {
		// TODO Auto-generated method stub
		System.out.println("UserOne_obsupdata has received!");
	}

}
