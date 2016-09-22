/**下午2:57:36
 * @author zhangyh2
 * ResourceHandler.java
 * TODO
 */
package cn.com.sax;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.xml.sax.Attributes;

/**
 * @author zhangyh2 ResourceHandler 下午2:57:36 TODO 对XML的处理过程主要在ResourceHandler类中
 */
public class ResourceHandler extends BaseSourceHandler<List<Group>> {

	public final static String ROOT = "root";
	public final static String GROUPS = "groups";
	public final static String GROUP = "group";
	public final static String MACHINE = "machine";
	public final static String IP = "ip";
	public final static String HOSTNAME = "hostname";
	public final static String NAME = "name";
	public final static String TYPE = "type";
	public final static String TYPE_CONCRETE = "concrete";
	public final static String TYPE_VIRTUAL = "virtual";

	/**
	 * <group>节点开始时压栈，结束时出栈
	 */
	Stack<Group> groupStack = new Stack<Group>();

	/**
	 * <machine>节点开始时压栈，结束时出栈
	 */
	Stack<Machine> machineStack = new Stack<Machine>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.sax.BaseSourceHandler#start(java.lang.String,
	 * java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void start(String uri, String localName, String qName,
			Attributes attributes) {
		// TODO Auto-generated method stub
		if (GROUPS.equals(qName)) {
			result = new ArrayList<Group>();
		} else if (GROUP.equals(qName)) {
			String groupName = attributes.getValue(NAME);
			Group group = new Group(groupName);
			groupStack.push(group);
		} else if (MACHINE.equals(qName)) {
			String machineName = attributes.getValue(NAME);
			String machineType = attributes.getValue(TYPE);

			Machine machine = new Machine(machineName);
			machine.setType(machineType);
			machineStack.push(machine);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.sax.BaseSourceHandler#end(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void end(String uri, String localName, String qName) {
		// TODO Auto-generated method stub
		if (GROUP.equals(qName)) {
			result.add(groupStack.pop());
		} else if (MACHINE.equals(qName)) {
			groupStack.peek().addMachine(machineStack.pop());
		} else if (IP.equals(qName)) {
			// <ip>节点结束时“currentNodeText”的值即为IP
			machineStack.peek().setIp(currentNodeText);
			currentNodeText = null;
		} else if (HOSTNAME.equals(qName)) {
			// <hostname>节点结束时“currentNodeText”的值即为HOSTNAME
			machineStack.peek().setHostname(currentNodeText);
			currentNodeText = null;
		}
	}
}
