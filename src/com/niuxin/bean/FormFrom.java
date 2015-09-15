package com.niuxin.bean;

import java.util.Date;

public class FormFrom {

	private Integer id;
	private Integer userId;
	private Integer recriveUserid;
	private Integer recviveGroupid;
	private Date createTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getRecriveUserid() {
		return recriveUserid;
	}
	public void setRecriveUserid(Integer recriveUserid) {
		this.recriveUserid = recriveUserid;
	}
	public Integer getRecviveGroupid() {
		return recviveGroupid;
	}
	public void setRecviveGroupid(Integer recviveGroupid) {
		this.recviveGroupid = recviveGroupid;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
