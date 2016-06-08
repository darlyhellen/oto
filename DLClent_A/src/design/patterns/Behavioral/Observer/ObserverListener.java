/**上午11:17:37
 * @author zhangyh2
 * Observer.java
 * TODO 我解释下这些类的作用：MySubject类就是我们的主对象，Observer1和Observer2是依赖于MySubject的对象，当MySubject变化时，Observer1和Observer2必然变化。AbstractSubject类中定义着需要监控的对象列表，可以对其进行修改：增加或删除被监控对象，且当MySubject变化时，负责通知在列表内存在的对象
 */
package design.patterns.Behavioral.Observer;

/**
 * @author zhangyh2 Observer 上午11:17:37 TODO
 */
public interface ObserverListener {

	void observer();

	void obsupdata();

}
