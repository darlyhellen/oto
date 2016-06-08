/**下午2:58:18
 * @author zhangyh2
 * Subject.java
 * TODO
 */
package design.patterns.Behavioral.Visitor;

/**
 * @author zhangyh2 Subject 下午2:58:18 TODO
 */
public class Subject implements SubjectListener {

	private String name;

	public Subject(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * design.patterns.Behavioral.Visitor.SubjectListener#accept(design.patterns
	 * .Behavioral.Visitor.VisitorListener)
	 */
	@Override
	public void accept(VisitorListener visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see design.patterns.Behavioral.Visitor.SubjectListener#getSubject()
	 */
	@Override
	public String getSubject() {
		// TODO Auto-generated method stub
		return name;
	}

}
