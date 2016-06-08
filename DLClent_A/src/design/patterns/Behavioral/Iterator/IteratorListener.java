/**上午11:42:15
 * @author zhangyh2
 * IteratorListener.java
 * TODO
 */
package design.patterns.Behavioral.Iterator;

/**
 * @author zhangyh2 IteratorListener 上午11:42:15 TODO
 */
public interface IteratorListener {

	// 前移
	public Object previous();

	// 后移
	public Object next();

	public boolean hasNext();

	// 取得第一个元素
	public Object first();
}
