package com.hellen.bean;

/**
 * @author zhangyh2 VersionUpdate 下午4:19:47 TODO
 */
public class VersionModel {

	private String source;
	private int version;
	private String url;
	private int downloadType;
	private String compareStr;
	private String description;
	private boolean forced;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getDownloadType() {
		return downloadType;
	}

	public void setDownloadType(int downloadType) {
		this.downloadType = downloadType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCompareStr() {
		return compareStr;
	}

	public void setCompareStr(String compareStr) {
		this.compareStr = compareStr;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isForced() {
		return forced;
	}

	public void setForced(boolean forced) {
		this.forced = forced;
	}

}
