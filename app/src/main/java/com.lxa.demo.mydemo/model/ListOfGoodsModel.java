package com.lxa.demo.mydemo.model;

/**
 * 商品列表MOdel
 * 
 * @author 谢俊杰 2016年1月6日
 *
 */
public class ListOfGoodsModel {
	/**
	 * 标题
	 */
	private String biaoti;
	/**
	 * 现价
	 */
	private String jiage;
	/**
	 * 原价
	 */
	private String yuanjia;

	public String getBiaoti() {
		return biaoti;
	}

	public void setBiaoti(String biaoti) {
		this.biaoti = biaoti;
	}

	public String getJiage() {
		return jiage;
	}

	public void setJiage(String jiage) {
		this.jiage = jiage;
	}

	public String getYuanjia() {
		return yuanjia;
	}

	public void setYuanjia(String yuanjia) {
		this.yuanjia = yuanjia;
	}

}
