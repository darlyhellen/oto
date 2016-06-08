/**上午11:24:01
 * @author zhangyh2
 * AbstractSubject.java
 * TODO
 */
package design.patterns.Behavioral.Observer;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author zhangyh2 AbstractSubject 上午11:24:01 TODO
 */
public abstract class AbstractSubject implements SubjectListener {
	// 观察者的列表
	private Vector<ObserverListener> vector = new Vector<ObserverListener>();

	@Override
	public void addObserver(ObserverListener ob) {
		// TODO Auto-generated method stub
		vector.add(ob);
	}

	@Override
	public void delObserver(ObserverListener ob) {
		// TODO Auto-generated method stub
		vector.remove(ob);
	}

	@Override
	public void notifyObserver() {
		// TODO Auto-generated method stub
		Enumeration<ObserverListener> enumd = vector.elements();
		while (enumd.hasMoreElements()) {
			ObserverListener observerListener = enumd
					.nextElement();
			observerListener.observer();
			observerListener.obsupdata();
		}
	}

}
