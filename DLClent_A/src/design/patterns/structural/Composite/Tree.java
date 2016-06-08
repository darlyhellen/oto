/**上午11:39:02
 * @author zhangyh2
 * Tree.java
 * TODO
 */
package design.patterns.structural.Composite;

/** @author zhangyh2
 * Tree
 * 上午11:39:02
 * TODO
 */
public class Tree {

	
	public TreeNode root = null;

	public Tree(String name) {
		super();
		root = new TreeNode(name);
	}
}
