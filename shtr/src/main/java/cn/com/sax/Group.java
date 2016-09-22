/**下午3:08:50
 * @author zhangyh2
 * Group.java
 * TODO
 */
package cn.com.sax;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyh2 Group 下午3:08:50 TODO
 */
public class Group {
	private String name;
	private List<Machine> machineList;

	public Group(String _name) {
		this.name = _name;
		machineList = new ArrayList<Machine>();
	}

	public void addMachine(Machine machine) {
		machineList.add(machine);
	}

	public void removeMachine(Machine machine) {
		machineList.remove(machine);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Machine> getMachineList() {
		return machineList;
	}

	public void setMachineList(List<Machine> machineList) {
		this.machineList = machineList;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(name).append("{");

		for (int i = 0; i < machineList.size(); i++) {
			sb.append(machineList.get(i));
			if (i != (machineList.size() - 1)) {
				sb.append(",");
			}
		}
		sb.append("}");
		return sb.toString();
	}
}
