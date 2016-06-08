/**下午3:40:15
 * @author zhangyh2
 * BehavioralTest.java
 * TODO
 */
package design.patterns.Behavioral;

import java.util.Random;

import org.junit.Test;

import design.patterns.Behavioral.Command.CommandListener;
import design.patterns.Behavioral.Command.Ephor;
import design.patterns.Behavioral.Command.EphorCommand;
import design.patterns.Behavioral.Command.Invoker;
import design.patterns.Behavioral.Command.Receiver;
import design.patterns.Behavioral.Command.ReceiverCommand;
import design.patterns.Behavioral.Interpreter.Context;
import design.patterns.Behavioral.Interpreter.Minus;
import design.patterns.Behavioral.Interpreter.Plus;
import design.patterns.Behavioral.Iterator.Collection;
import design.patterns.Behavioral.Iterator.CollectionListener;
import design.patterns.Behavioral.Iterator.IteratorListener;
import design.patterns.Behavioral.Mediator.Mediator;
import design.patterns.Behavioral.Mediator.MediatorListener;
import design.patterns.Behavioral.Memento.Original;
import design.patterns.Behavioral.Memento.Storage;
import design.patterns.Behavioral.Observer.Subject;
import design.patterns.Behavioral.Observer.SubjectListener;
import design.patterns.Behavioral.Observer.UserOne;
import design.patterns.Behavioral.Observer.UserTwo;
import design.patterns.Behavioral.Responsibility.Response;
import design.patterns.Behavioral.State.State;
import design.patterns.Behavioral.TemplateMethod.AbstractCalculator;
import design.patterns.Behavioral.Visitor.Visitor;
import design.patterns.Behavioral.Visitor.VisitorListener;
import design.patterns.Behavioral.strategy.Division;
import design.patterns.Behavioral.strategy.ICalculator;
import design.patterns.Behavioral.strategy.Multiply;

/**
 * @author zhangyh2 BehavioralTest 下午3:40:15 TODO
 */
public class BehavioralTest {
	@Test
	public void command() {

		Receiver receiver = new Receiver();

		CommandListener commands = new ReceiverCommand(receiver);

		Ephor ephor = new Ephor(commands);

		CommandListener com = new EphorCommand(ephor);

		Invoker invoker = new Invoker(com);

		invoker.aciton();
	}

	@Test
	public void inerpreter() {
		// 计算9+2-8的值
		int res = new Minus().interpret(new Context(new Plus()
				.interpret(new Context(9, 2)), 8));
		System.out.println(res);
	}

	@Test
	public void iterator() {
		CollectionListener collect = new Collection();
		IteratorListener iter = collect.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}

	@Test
	public void mediator() {
		MediatorListener mediator = new Mediator();
		mediator.createMediator();
		mediator.workAll();
	}

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

	@Test
	public void observer() {
		// 这些东西，其实不难，只是有些抽象，不太容易整体理解，建议读者：根据关系图，新建项目，自己写代码（或者参考我的代码）,按照总体思路走一遍，这样才能体会它的思想，理解起来容易！
		SubjectListener sub = new Subject();
		sub.addObserver(new UserOne());
		sub.addObserver(new UserTwo());
		sub.operation();
	}

	@Test
	public void responsibility() {
		// 此处强调一点就是，链接上的请求可以是一条链，可以是一个树，还可以是一个环，模式本身不约束这个，需要我们自己去实现，同时，在一个时刻，命令只允许由一个对象传给另一个对象，而不允许传给多个对象。
		Response r1 = new Response("R1");
		Response r2 = new Response("R2");
		Response r3 = new Response("R3");

		r1.setHand(r2);
		r2.setHand(r3);

		r1.operator();

	}

	@Test
	public void state() {
		// 根据这个特性，状态模式在日常开发中用的挺多的，尤其是做网站的时候，我们有时希望根据对象的某一属性，区别开他们的一些功能，比如说简单的权限控制等。
		State state = new State();
		design.patterns.Behavioral.State.Context context = new design.patterns.Behavioral.State.Context(
				state);
		state.setValue("doone");
		context.chooseMethod();
		state.setValue("dotwo");
		context.chooseMethod();
	}

	@Test
	public void strategy() {
		// 策略模式的决定权在用户，系统本身提供不同算法的实现，新增或者删除算法，对各种算法做封装。因此，策略模式多用在算法决策系统中，外部用户只需要决定用哪个算法即可。
		String[] ca = { "+", "-", "*", "/" };
		String temp = new Random().nextInt(9) + ca[new Random().nextInt(4)]
				+ new Random().nextInt(9);
		ICalculator calculator = null;
		if (temp != null && temp.length() > 0) {
			if (temp.contains("+")) {
				calculator = new design.patterns.Behavioral.strategy.Plus();
			} else if (temp.contains("-")) {
				calculator = new design.patterns.Behavioral.strategy.Minus();
			} else if (temp.contains("*")) {
				calculator = new Multiply();
			} else if (temp.contains("/")) {
				calculator = new Division();
			} else {
				System.out.println("not simple math");
				return;
			}
			double extp = calculator.calculator(temp);
			System.out.println(temp + "=" + extp);
		} else {
			System.out.println("not data");
		}

	}

	@Test
	public void templateMethod() {
		// 我跟踪下这个小程序的执行过程：首先将exp和"\\+"做参数，调用AbstractCalculator类里的calculate(String,String)方法，在calculate(String,String)里调用同类的split()，之后再调用calculate(int
		// ,int)方法，从这个方法进入到子类中，执行完return num1 +
		// num2后，将值返回到AbstractCalculator类，赋给result，打印出来。正好验证了我们开头的思路。
		String exp = "8+8";
		// 父类实现对子类的调用
		AbstractCalculator cal = new design.patterns.Behavioral.TemplateMethod.Plus();
		double ex = cal.calculate(exp, "\\+");
		System.out.println(exp + "=" + ex);
	}

	@Test
	public void visitor() {
		// 该模式适用场景：如果我们想为一个现有的类增加新功能，不得不考虑几个事情：1、新功能会不会与现有功能出现兼容性问题？2、以后会不会再需要添加？
		// 3、如果类不允许修改代码怎么办？面对这些问题，最好的解决方法就是使用访问者模式，访问者模式适用于数据结构相对稳定的系统，把数据结构和算法解耦，
		VisitorListener visit = new Visitor();
		design.patterns.Behavioral.Visitor.SubjectListener subject = new design.patterns.Behavioral.Visitor.Subject(
				"目标");
		subject.accept(visit);
	}
}
