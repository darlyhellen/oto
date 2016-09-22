/**下午3:09:21
 * @author zhangyh2
 * Machine.java
 * TODO
 */
package cn.com.sax;

/**
 * @author zhangyh2 Machine 下午3:09:21 TODO
 */
public class Machine {
	private String name;
	private String type;
	private String ip;
	private String hostname;

	public Machine(String _name) {
		this.name = _name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(name).append("[type=" + type).append(",IP=" + ip)
				.append(",hostname=" + hostname).append("]");
		return sb.toString();
	}
}
