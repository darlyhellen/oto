/**上午11:41:10
 * @author zhangyh2
 * CompositeTest.java
 * TODO
 */
package design.patterns.structural.Composite;

import org.junit.Test;

/**
 * @author zhangyh2 CompositeTest 上午11:41:10 TODO
 */
public class CompositeTest {

	@Test
	public void composite() {
		// 使用场景：将多个对象组合在一起进行操作，常用于表示树形结构中，例如二叉树，数等。
		Tree tree = new Tree("A");
		TreeNode b = new TreeNode("TreeNodeB");
		TreeNode c = new TreeNode("TreeNodeC");
		b.add(c);
		c.setParent(b);
		tree.root.add(b);
		b.setParent(tree.root);
		System.out.println("build the tree finished!");
	}
}
