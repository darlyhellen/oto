package cn.com.bean;


public class Cart {
	private Integer userId;

	private String userName;

	/** 上午10:47:36
	 * @author zhangyh2
	 */
	public Cart() {
		// TODO Auto-generated constructor stub
	}

	public Cart(Integer userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

}