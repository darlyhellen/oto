/**下午2:57:19
 * @author zhangyh2
 * SubjectListener.java
 * TODO
 */
package design.patterns.Behavioral.Visitor;

/**
 * @author zhangyh2 SubjectListener 下午2:57:19 TODO
 *         Subject类，accept方法，接受将要访问它的对象，getSubject()获取将要被访问的属性，
 */
public interface SubjectListener {

	void accept(VisitorListener visitor);

	String getSubject();
}
