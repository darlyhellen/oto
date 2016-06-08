/**下午2:34:31
 * @author zhangyh2
 * Memento.java
 * TODO
 */
package design.patterns.Behavioral.Memento;

/**
 * @author zhangyh2 Memento 下午2:34:31 TODO
 */
public class Memento {

	private String value;

	public Memento(String value) {
		super();
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
