package com.niuxin.bean;


import java.util.Date;

public class ShareGroup {

	private Integer id;//
	private String type;
	private String enterGrade;
	private String description;
	private String isfree;
	private Integer totalNumber;
	private Integer currentNumber;
	private Date createTime;
	private String name;
	private String mark;
	private Integer createuserid;
	private String img;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEnterGrade() {
		return enterGrade;
	}
	public void setEnterGrade(String enterGrade) {
		this.enterGrade = enterGrade;
	}
	public String getIsfree() {
		return isfree;
	}
	public void setIsfree(String isfree) {
		this.isfree = isfree;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
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
	public Integer getCreateuserid() {
		return createuserid;
	}
	public void setCreateuserid(Integer createuserid) {
		this.createuserid = createuserid;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
}
