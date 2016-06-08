/**下午2:56:39
 * @author zhangyh2
 * VisitorListener.java
 * TODO
 */
package design.patterns.Behavioral.Visitor;

/**
 * @author zhangyh2 VisitorListener 下午2:56:39 TODO
 * 
 *         一个Visitor类，存放要访问的对象，
 */
public interface VisitorListener {

	void visit(SubjectListener sub);
	
}
