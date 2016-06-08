/**下午1:50:02
 * @author zhangyh2
 * Abstracthandler.java
 * TODO
 */
package design.patterns.Behavioral.Responsibility;

/**
 * @author zhangyh2 Abstracthandler 下午1:50:02 TODO
 */
public abstract class Abstracthandler {

	private HandlerListener hand;

	public HandlerListener getHand() {
		return hand;
	}

	public void setHand(HandlerListener hand) {
		this.hand = hand;
	}

}
