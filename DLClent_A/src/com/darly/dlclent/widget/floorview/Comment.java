/**
 * 
 */
package com.darly.dlclent.widget.floorview;


/**
 * @author zhangyh2 Comment 下午2:42:38 TODO 评论信息实例类
 */
public class Comment {

	/**
	 * 下午2:43:20 TODO 独立评论，没有楼层
	 */
	public static final long NULL_PARENT = -1;

	/**
	 * 下午2:40:22 TODO 上层楼层的ID号
	 */
	private long parentId;
	/**
	 * 下午2:40:57 TODO 用户的ID
	 */
	private long userId;
	/**
	 * 下午2:41:36 TODO 是否楼层开始 1为不开始，2为开始
	 */
	private long id;
	/**
	 * 下午2:41:51 TODO 内容
	 */
	private String content;
	/**
	 * 下午2:42:00 TODO 用户昵称
	 */
	private String userName;

	/**
	 * 下午2:45:10 TODO 用户图像信息
	 */
	private String icon;
	/**
	 * 下午2:42:09 TODO 发布时间
	 */
	private String date;
	/**
	 * 下午2:42:17 TODO 楼层
	 */
	private int floorNum;

	/**
	 * 下午4:49:10
	 * 
	 * @author zhangyh2
	 */
	public Comment() {
		// TODO Auto-generated constructor stub
	}

	public Comment(long userId, long id, String content, String userName,
			String icon, String date) {
		this.userId = userId;
		this.id = id;
		this.content = content;
		this.userName = userName;
		this.icon = icon;
		this.date = date;
		this.parentId = NULL_PARENT;
		this.floorNum = 1;
	}

	public Comment(long parent_id, long user_id, long ID, String content,
			String userName, String icon, String date, int floorNum) {
		this.parentId = parent_id;
		this.userId = user_id;
		this.id = ID;
		this.content = content;
		this.userName = userName;
		this.icon = icon;
		this.date = date;
		this.floorNum = floorNum;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getFloorNum() {
		return floorNum;
	}

	public void setFloorNum(int floorNum) {
		this.floorNum = floorNum;
	}

	public static long getNullParent() {
		return NULL_PARENT;
	}

}
