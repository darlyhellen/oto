/**下午2:46:49
 * @author zhangyh2
 * Context.java
 * TODO
 */
package design.patterns.Behavioral.State;

/**
 * @author zhangyh2 Context 下午2:46:49 TODO Context类可以实现切换
 * 
 *         当对象的状态改变时，同时改变其行为，很好理解！就拿QQ来说，有几种状态，在线、隐身、忙碌等，每个状态对应不同的操作，
 *         而且你的好友也能看到你的状态，所以，状态模式就两点：1、可以通过改变状态来获得不同的行为。2、你的好友能同时看到你的变化。
 */
public class Context {

	private State state;

	public Context(State state) {
		super();
		this.state = state;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public void chooseMethod() {
		if (state.getValue().equals("doone")) {
			state.doone();
		} else if (state.getValue().equals("dotwo")) {
			state.dotwo();
		}
	}
}
