/**上午11:44:59
 * @author zhangyh2
 * Collection.java
 * TODO
 */
package design.patterns.Behavioral.Iterator;

/**
 * @author zhangyh2 Collection 上午11:44:59 TODO
 */
public class Collection implements CollectionListener {
	public String string[] = { "A", "B", "C", "D", "E" };

	/*
	 * (non-Javadoc)
	 * 
	 * @see design.patterns.Behavioral.Iterator.CollectionListener#iterator()
	 */
	@Override
	public IteratorListener iterator() {
		// TODO Auto-generated method stub
		return new Iterator(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see design.patterns.Behavioral.Iterator.CollectionListener#get(int)
	 */
	@Override
	public Object get(int i) {
		// TODO Auto-generated method stub
		return string[i];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see design.patterns.Behavioral.Iterator.CollectionListener#size()
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return string.length;
	}

}
