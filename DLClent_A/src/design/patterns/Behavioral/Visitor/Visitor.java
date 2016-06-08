/**下午2:58:42
 * @author zhangyh2
 * Visitor.java
 * TODO
 */
package design.patterns.Behavioral.Visitor;

/** @author zhangyh2
 * Visitor
 * 下午2:58:42
 * TODO
 */
public class Visitor implements VisitorListener {

	/* (non-Javadoc)
	 * @see design.patterns.Behavioral.Visitor.VisitorListener#visit(design.patterns.Behavioral.Visitor.SubjectListener)
	 */
	@Override
	public void visit(SubjectListener sub) {
		// TODO Auto-generated method stub
		System.out.println("visit the subject："+sub.getSubject());  
	}

}
