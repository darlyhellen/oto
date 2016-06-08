/**上午11:28:25
 * @author zhangyh2
 * Subject.java
 * TODO
 */
package design.patterns.Behavioral.Observer;

/**
 * @author zhangyh2 Subject 上午11:28:25 TODO
 */
public class Subject extends AbstractSubject {

	/*
	 * (non-Javadoc)
	 * 
	 * @see design.patterns.Behavioral.Observer.SubjectListener#operation()
	 */
	@Override
	public void operation() {
		// TODO Auto-generated method stub
		System.out.println("Subject operation");
		notifyObserver();
	}

}
