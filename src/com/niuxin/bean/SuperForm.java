package com.niuxin.bean;

import java.math.BigDecimal;
import java.util.Date;

public class SuperForm{

	private Integer id; //表单的编号
	private String contract;	//合约类型
	private String operation;//操作类型
	private BigDecimal price;//价格
	private Integer handnum;//手数
	private Double position;//仓位
	private Double minnum;//止损范围最小值
	private Double maxnum;//止损范围最大值
	private String remark;//备注
	private String pictureurl;//上传的图片的url
	private String audiourl;//上传的语音的url
	private Integer collection;//是否收藏 0代表没有 1代表收藏
	private Date createtime;//报单创建时间
	private Date updatetime;//更新时间
	private Integer audioread;//是否听过语音 0 代表没有 1代表听过
	private Integer sendfrom;//是谁发送的
	private Integer occupy;//暂时保存的一个字段
	private String name;//表单名称
	private String sendtoUser;//发送给哪些用户  逗号分隔
	private String sendtoGroup;//发送给哪些群组  逗号分隔
	
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
	public Integer getCollection() {
		return collection;
	}
	public void setCollection(Integer collection) {
		this.collection = collection;
	}
	
	
}
