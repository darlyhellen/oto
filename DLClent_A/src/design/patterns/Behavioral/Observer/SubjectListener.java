/**上午11:21:33
 * @author zhangyh2
 * SubjectListener.java
 * TODO
 */
package design.patterns.Behavioral.Observer;

/**
 * @author zhangyh2 SubjectListener 上午11:21:33 TODO
 */
public interface SubjectListener {
	/* 增加观察者 */
	void addObserver(ObserverListener ob);

	/* 删除观察者 */
	void delObserver(ObserverListener ob);

	/* 通知所有的观察者 */
	void notifyObserver();

	/* 自身的操作 */
	void operation();
}
