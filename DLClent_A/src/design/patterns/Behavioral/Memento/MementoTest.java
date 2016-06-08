/**下午2:37:23
 * @author zhangyh2
 * MementoTest.java
 * TODO
 */
package design.patterns.Behavioral.Memento;

import org.junit.Test;

/**
 * @author zhangyh2 MementoTest 下午2:37:23 TODO
 */
public class MementoTest {

	@Test
	public void memento() {
		// 新建原始类时，value被初始化为egg，后经过修改，将value的值置为niu，最后倒数第二行进行恢复状态，结果成功恢复了。其实我觉得这个模式叫“备份-恢复”模式最形象。
		Original original = new Original("原始状态");
		Storage storage = new Storage(original.createMemento());
		// 修改原始类的状态
		System.out.println("初始化状态为：" + original.getValue());
		original.setValue("第二形态");
		System.out.println("修改后的状态为：" + original.getValue());
		// 回复原始类的状态
		original.restoreMemento(storage.getMemento());
		System.out.println("恢复后的状态为：" + original.getValue());
	}
}
