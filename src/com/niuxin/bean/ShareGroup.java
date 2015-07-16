package com.niuxin.bean;


import java.util.Date;

public class ShareGroup {

	private Integer id;
	private Integer type;
	private Integer enterGrade;
	private String description;
	private Integer isfree;
	private Integer totalNumber;
	private Integer currentNumber;
	private Date createTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getEnterGrade() {
		return enterGrade;
	}
	public void setEnterGrade(Integer enterGrade) {
		this.enterGrade = enterGrade;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getIsfree() {
		return isfree;
	}
	public void setIsfree(Integer isfree) {
		this.isfree = isfree;
	}
	public Integer getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}
	public Integer getCurrentNumber() {
		return currentNumber;
	}
	public void setCurrentNumber(Integer currentNumber) {
		this.currentNumber = currentNumber;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
