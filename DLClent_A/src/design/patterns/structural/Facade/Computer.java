/**上午11:10:11
 * @author zhangyh2
 * Computer.java
 * TODO
 */
package design.patterns.structural.Facade;

/**
 * @author zhangyh2 Computer 上午11:10:11 TODO
 */
public class Computer {

	private Cpu cpu;
	private Memory memory;
	private Disk disk;

	public Computer() {
		super();
		this.cpu = new Cpu();
		this.memory = new Memory();
		this.disk = new Disk();
	}

	/**
	 * 上午11:08:14
	 * 
	 * @author zhangyh2 TODO
	 */
	public void startup() {
		// TODO Auto-generated method stub
		System.out.println("Computer startup");
		cpu.startup();
		memory.startup();
		disk.startup();
		System.out.println("Computer startup finished");
	}

	/**
	 * 上午11:08:19
	 * 
	 * @author zhangyh2 TODO
	 */
	public void shutdown() {
		// TODO Auto-generated method stub
		System.out.println("Computer shutdown");
		cpu.shutdown();
		memory.shutdown();
		disk.shutdown();
		System.out.println("Computer shutdown finished");
	}
}
