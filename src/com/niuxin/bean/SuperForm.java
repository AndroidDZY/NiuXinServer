package com.niuxin.bean;

import java.math.BigDecimal;
import java.util.Date;

public class SuperForm{

	private Integer id;
	private String contract;	
	private String operation;
	private BigDecimal price;
	private Integer handnum;
	private Double position;
	private Double minnum;
	private Double maxnum;
	private String remark;
	private String pictureurl;
	private String audiourl;
	private Integer sendto;
	private Date createtime;
	private Date updatetime;
	private Integer audioread;
	private Integer sendfrom;
	private Integer occupy;
	private String name;
	private String sendtoUser;
	private String sendtoGroup;
	
	public Integer getId() {
		return id;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getHandnum() {
		return handnum;
	}
	public void setHandnum(Integer handnum) {
		this.handnum = handnum;
	}
	public Double getPosition() {
		return position;
	}
	public void setPosition(Double position) {
		this.position = position;
	}
	public Double getMinnum() {
		return minnum;
	}
	public void setMinnum(Double minnum) {
		this.minnum = minnum;
	}
	public Double getMaxnum() {
		return maxnum;
	}
	public void setMaxnum(Double maxnum) {
		this.maxnum = maxnum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPictureurl() {
		return pictureurl;
	}
	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}
	public String getAudiourl() {
		return audiourl;
	}
	public void setAudiourl(String audiourl) {
		this.audiourl = audiourl;
	}
	public Integer getSendto() {
		return sendto;
	}
	public void setSendto(Integer sendto) {
		this.sendto = sendto;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public Integer getAudioread() {
		return audioread;
	}
	public void setAudioread(Integer audioread) {
		this.audioread = audioread;
	}
	public Integer getSendfrom() {
		return sendfrom;
	}
	public void setSendfrom(Integer sendfrom) {
		this.sendfrom = sendfrom;
	}
	public Integer getOccupy() {
		return occupy;
	}
	public void setOccupy(Integer occupy) {
		this.occupy = occupy;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSendtoUser() {
		return sendtoUser;
	}
	public void setSendtoUser(String sendtoUser) {
		this.sendtoUser = sendtoUser;
	}
	public String getSendtoGroup() {
		return sendtoGroup;
	}
	public void setSendtoGroup(String sendtoGroup) {
		this.sendtoGroup = sendtoGroup;
	}
	
	
}
