package com.niuxin.bean;

public class Shield {

	private int id;//
	private int userId;//自己的id
	private int shieldUserid;//关注的用户id
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getShieldUserid() {
		return shieldUserid;
	}
	public void setShieldUserid(int shieldUserid) {
		this.shieldUserid = shieldUserid;
	}
	
}
