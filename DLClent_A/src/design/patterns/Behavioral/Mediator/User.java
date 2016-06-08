/**下午3:16:51
 * @author zhangyh2
 * UserListener.java
 * TODO
 */
package design.patterns.Behavioral.Mediator;

/**
 * @author zhangyh2 UserListener 下午3:16:51 TODO
 */
public abstract class User {

	private MediatorListener mediator;

	public User(MediatorListener mediator) {
		super();
		this.mediator = mediator;
	}

	public MediatorListener getMediator() {
		return mediator;
	}

	public void setMediator(MediatorListener mediator) {
		this.mediator = mediator;
	}

	public abstract void work();
}
