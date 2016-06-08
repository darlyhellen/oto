/**上午11:41:45
 * @author zhangyh2
 * Collection.java
 * TODO
 */
package design.patterns.Behavioral.Iterator;

/**
 * @author zhangyh2 Collection 上午11:41:45 TODO
 */
public interface CollectionListener {

	public IteratorListener iterator();

	/* 取得集合元素 */
	public Object get(int i);

	/* 取得集合大小 */
	public int size();

}
