package cn.com.sax;

/**
 * @author zhangyh2 VersionUpdate 下午4:19:47 TODO
 */
public class VersionUpdateModel {

	public static String LIST = "list";
	public static String LISTITEM = "listitem";
	public static String SOURCE = "source";
	public static String VERSION = "version";
	public static String VERSIONNAME = "versionName";
	public static String URL = "url";
	public static String DOWNLOADTYPE = "downloadType";
	public static String COMPARESTR = "compareStr";
	public static String DESCRIPTION = "description";
	public static String FORCED = "forced";
	public static String TITLES = "titles";
	public static String DESIGN = "design";

	private String source;
	private String version;
	private String versionName;
	private String url;
	private String downloadType;
	private String compareStr;
	private String description;
	private String forced;
	private String titles;
	private String design;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDownloadType() {
		return downloadType;
	}

	public void setDownloadType(String downloadType) {
		this.downloadType = downloadType;
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

	public String getForced() {
		return forced;
	}

	public void setForced(String forced) {
		this.forced = forced;
	}

	public String getTitles() {
		return titles;
	}

	public void setTitles(String titles) {
		this.titles = titles;
	}

	public String getDesign() {
		return design;
	}

	public void setDesign(String design) {
		this.design = design;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("[source=" + source).append(",version=" + version)
				.append(",url=" + url).append(",downloadType=" + downloadType)
				.append(",compareStr=" + compareStr)
				.append(",description=" + description)
				.append(",forced=" + forced).append(",titles=" + titles)
				.append(",design=" + design).append("]");
		return sb.toString();
	}
}
