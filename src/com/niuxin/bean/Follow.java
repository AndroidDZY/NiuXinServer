package com.niuxin.bean;

public class Follow {

	private int id;//
	private int userId;//自己的id
	private int followUserid;//关注的用户id
	private int followGroupid;//关注的群组id
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
	public int getFollowUserid() {
		return followUserid;
	}
	public void setFollowUserid(int followUserid) {
		this.followUserid = followUserid;
	}
	public int getFollowGroupid() {
		return followGroupid;
	}
	public void setFollowGroupid(int followGroupid) {
		this.followGroupid = followGroupid;
	}
	
}
