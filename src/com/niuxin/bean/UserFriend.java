package com.niuxin.bean;

public class UserFriend {

	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserSelfId() {
		return userSelfId;
	}
	public void setUserSelfId(Integer userSelfId) {
		this.userSelfId = userSelfId;
	}
	public Integer getUserFriendId() {
		return userFriendId;
	}
	public void setUserFriendId(Integer userFriendId) {
		this.userFriendId = userFriendId;
	}
	private Integer userSelfId;
	private Integer userFriendId;
	
}
