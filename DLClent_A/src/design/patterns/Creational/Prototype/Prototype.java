/**上午9:49:01
 * @author zhangyh2
 * Prototype.java
 * TODO
 */
package design.patterns.Creational.Prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;

/**
 * @author zhangyh2 Prototype 上午9:49:01
 *         TODO原型模式虽然是创建型的模式，但是与工程模式没有关系，从名字即可看出，该模式的思想就是将一个对象作为原型
 *         ，对其进行复制、克隆，产生一个和原对象类似的新对象
 *         。本小结会通过对象的复制，进行讲解。在Java中，复制对象是通过clone()实现的，先创建一个原型类：
 */
public class Prototype implements Cloneable, Serializable {
	/**
	 * 上午9:54:28 TODO
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private DeepOb obj;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone() 浅复制
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Prototype prototype = (Prototype) super.clone();
		return prototype;
	}

	/*
	 * 很简单，一个原型类，只需要实现Cloneable接口，覆写clone方法，此处clone方法可以改成任意的名称，因为Cloneable接口是个空接口
	 * ，你可以任意定义实现类的方法名，如cloneA或者cloneB，因为此处的重点是super.clone()这句话，super.clone()
	 * 调用的是Object的clone
	 * ()方法，而在Object类中，clone()是native的，具体怎么实现，我会在另一篇文章中，关于解读Java中本地方法的调用
	 * ，此处不再深究。在这儿，我将结合对象的浅复制和深复制来说一下，首先需要了解对象深、浅复制的概念：
	 * 浅复制：将一个对象复制后，基本数据类型的变量都会重新创建，而引用类型，指向的还是原对象所指向的。
	 * 深复制：将一个对象复制后，不论是基本数据类型还有引用类型，都是重新创建的。简单来说，就是深复制进行了完全彻底的复制，而浅复制不彻底。
	 */

	/**
	 * 上午9:56:19
	 * 
	 * @author zhangyh2 TODO 深复制
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws OptionalDataException
	 */
	protected Object deepClone() throws OptionalDataException,
			ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		// 要实现深复制，需要采用流的形式读入当前对象的二进制输入，再写出二进制数据对应的对象
		/* 写入当前对象的二进制流 */
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(this);

		/* 读出二进制流产生的新对象 */
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bis);
		return ois.readObject();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DeepOb getObj() {
		return obj;
	}

	public void setObj(DeepOb obj) {
		this.obj = obj;
	}

	class DeepOb implements Serializable {

		/**
		 * 上午10:00:06 TODO
		 */
		private static final long serialVersionUID = 1L;

	}
}
