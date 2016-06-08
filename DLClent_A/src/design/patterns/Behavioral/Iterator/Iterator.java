/**上午11:45:24
 * @author zhangyh2
 * Iterator.java
 * TODO
 */
package design.patterns.Behavioral.Iterator;


/**
 * @author zhangyh2 Iterator 上午11:45:24 TODO
 */
public class Iterator implements IteratorListener {

	private CollectionListener collection;

	private int pos = -1;

	public Iterator(CollectionListener collection) {
		super();
		this.collection = collection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see design.patterns.Behavioral.Iterator.IteratorListener#previous()
	 */
	@Override
	public Object previous() {
		// TODO Auto-generated method stub
		if (pos > 0) {
			pos--;
		}
		return collection.get(pos);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see design.patterns.Behavioral.Iterator.IteratorListener#next()
	 */
	@Override
	public Object next() {
		// TODO Auto-generated method stub
		if (pos < collection.size() - 1) {
			pos++;
		}
		return collection.get(pos);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see design.patterns.Behavioral.Iterator.IteratorListener#hasNext()
	 */
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub

		if (pos < collection.size() - 1) {
			return true;
		} else {
			return false;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see design.patterns.Behavioral.Iterator.IteratorListener#first()
	 */
	@Override
	public Object first() {
		// TODO Auto-generated method stub
		pos = 0;
		return collection.get(pos);
	}

}
