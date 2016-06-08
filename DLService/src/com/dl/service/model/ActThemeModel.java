/**上午10:46:56
 * @author zhangyh2
 * ActThemeModel.java
 * TODO
 */
package com.dl.service.model;

/**
 * @author zhangyh2 ActThemeModel 上午10:46:56 TODO
 */
public class ActThemeModel {
	private String id;

	private String imagePath;

	private String description;
	

	public ActThemeModel(String id, String imagePath, String description) {
		this.id = id;
		this.imagePath = imagePath;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
