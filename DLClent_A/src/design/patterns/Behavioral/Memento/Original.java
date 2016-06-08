/**下午2:32:36
 * @author zhangyh2
 * Original.java
 * TODO
 */
package design.patterns.Behavioral.Memento;

/**
 * @author zhangyh2 Original 下午2:32:36 TODO
 */
public class Original {

	private String value;

	public Original(String value) {
		super();
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 下午2:33:35
	 * 
	 * @author zhangyh2 TODO
	 */
	public Memento createMemento() {
		// TODO Auto-generated method stub
		return new Memento(value);
	}

	/**
	 * 下午2:33:51
	 * 
	 * @author zhangyh2 TODO
	 */
	public void restoreMemento(Memento memento) {
		// TODO Auto-generated method stub
		this.value = memento.getValue();
	}
}
