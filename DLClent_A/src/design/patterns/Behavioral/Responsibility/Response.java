/**下午1:51:57
 * @author zhangyh2
 * Response.java
 * TODO
 */
package design.patterns.Behavioral.Responsibility;

/**
 * @author zhangyh2 Response 下午1:51:57 TODO
 */
public class Response extends Abstracthandler implements HandlerListener {
	private String name;

	public Response(String name) {
		super();
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see design.patterns.Behavioral.Responsibility.HandlerListener#operator()
	 */
	@Override
	public void operator() {
		// TODO Auto-generated method stub
		System.out.println(name + "deal!");
		if (getHand() != null) {
			getHand().operator();
		}
	}
}
