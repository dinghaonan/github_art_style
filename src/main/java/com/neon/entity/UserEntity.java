package com.neon.entity;

public class UserEntity {

	private int userId;
	private String userName;
	private String userCode;
	private String vld;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getVld() {
		return vld;
	}

	public void setVld(String vld) {
		this.vld = vld;
	}
}
