/**
 * 下午4:26:44
 * @author zhangyh2
 * $
 * CheckUpdata.java
 * TODO
 */
package com.darly.dlclent.model;

/**
 * @author zhangyh2 CheckUpdata $ 下午4:26:44 TODO
 */
public class CheckUpdata {

	public String LastestUrl;

	public int version;

	public String description;

	public boolean Force;

	public CheckUpdata(String lastestUrl, int version, String description,
			boolean force) {
		LastestUrl = lastestUrl;
		this.version = version;
		this.description = description;
		Force = force;
	}

	public String getLastestUrl() {
		return LastestUrl;
	}

	public void setLastestUrl(String lastestUrl) {
		LastestUrl = lastestUrl;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getForce() {
		return Force;
	}

	public void setForce(boolean force) {
		Force = force;
	}

}
