/**下午2:35:49
 * @author zhangyh2
 * Storage.java
 * TODO
 */
package design.patterns.Behavioral.Memento;

/**
 * @author zhangyh2 Storage 下午2:35:49 TODO
 */
public class Storage {

	private Memento memento;

	public Storage(Memento memento) {
		super();
		this.memento = memento;
	}

	public Memento getMemento() {
		return memento;
	}

	public void setMemento(Memento memento) {
		this.memento = memento;
	}

}
