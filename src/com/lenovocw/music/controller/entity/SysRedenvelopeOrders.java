/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.lenovocw.music.controller.entity;

import java.util.Date;

import com.sun.istack.internal.NotNull;


/**
 * REDENVELOPEEntity
 * @author 汉维
 * @version 2015-10-24
 */
public class SysRedenvelopeOrders  {
	
	private static final long serialVersionUID = 1L;
	private String wechatcode;		// 微信号
	private String wechatname;		// 微信名称
	private String mobileno;		// 电话号码
	private Long amount;		// 金额
	private Long quantity;		// 购买数量
	private Long flowsize;		// 流量大小
	private Date createdate;		// createdate
	private Date updatedate;		// updatedate
	private Integer sharestatus;		// 分享状态0：未分享:、1：已分享
	private Integer receivestatus;		// 领取状态0：未领取:、1：已领取
	private Integer remainingnumber;		// remainingnumber
	
	public SysRedenvelopeOrders() {
		super();
	}


	public String getWechatcode() {
		return wechatcode;
	}

	public void setWechatcode(String wechatcode) {
		this.wechatcode = wechatcode;
	}
	
	public String getWechatname() {
		return wechatname;
	}

	public void setWechatname(String wechatname) {
		this.wechatname = wechatname;
	}
	
	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	
	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	public Long getFlowsize() {
		return flowsize;
	}

	public void setFlowsize(Long flowsize) {
		this.flowsize = flowsize;
	}
	
	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	
	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	
	public Integer getSharestatus() {
		return sharestatus;
	}

	public void setSharestatus(Integer sharestatus) {
		this.sharestatus = sharestatus;
	}
	
	public Integer getReceivestatus() {
		return receivestatus;
	}

	public void setReceivestatus(Integer receivestatus) {
		this.receivestatus = receivestatus;
	}
	
	public Integer getRemainingnumber() {
		return remainingnumber;
	}

	public void setRemainingnumber(Integer remainingnumber) {
		this.remainingnumber = remainingnumber;
	}
	
}