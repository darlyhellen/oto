/**上午11:50:03
 * @author zhangyh2
 * IteratorTest.java
 * TODO
 */
package design.patterns.Behavioral.Iterator;

import org.junit.Test;

/**
 * @author zhangyh2 IteratorTest 上午11:50:03 TODO
 */
public class IteratorTest {

	@Test
	public void iterator() {
		CollectionListener collect = new Collection();
		IteratorListener iter = collect.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
}
