/**上午10:46:56
 * @author zhangyh2
 * ActThemeModel.java
 * TODO
 */
package com.darly.dlclent.model;

/**
 * @author zhangyh2 ActThemeModel 上午10:46:56 TODO
 */
public class ActThemeModel {
	private String id;

	private String imagepath;

	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImagePath() {
		return imagepath;
	}

	public void setImagePath(String imagePath) {
		this.imagepath = imagePath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ActThemeModel(String id, String imagePath, String description) {
		this.id = id;
		this.imagepath = imagePath;
		this.description = description;
	}

}
