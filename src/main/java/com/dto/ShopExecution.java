package com.dto;

import java.util.List;

import com.domain.Shop;
import com.enums.ShopStateEnum;
/*
 * 店铺操作的时候错误处理类
 */
//增强店铺类，插入失败或者成功的时候返回什么
public class ShopExecution {
	/**
	 * 结果标识
	 */
	private int state;
	/**
	 * 状态标识
	 */
	private String stateInfo;
	/**
	 * 返回店铺数量
	 */
	private int count;
	/*
	 * 操作的shop（增删改店铺的时候用到）
	 */
	private Shop shop;
	//shop列表（查询店铺列表的时候使用）
	private List<Shop> shopList;
	public ShopExecution() {
		super();
		// TODO Auto-generated constructor stub
	}
	//店铺操作失败的时候使用的构造器
	public ShopExecution(ShopStateEnum stateEnum) {
		this.state=stateEnum.getState();
		this.stateInfo=stateEnum.getStateInfo();
	}
	//店铺操作成的时候使用的构造器
	public ShopExecution(ShopStateEnum stateEnum,Shop shop) {
		this.state=stateEnum.getState();
		this.stateInfo=stateEnum.getStateInfo();
		this.shop=shop;
	}
	//成功的构造器
	public ShopExecution(ShopStateEnum stateEnum,List<Shop> shopList) {
		this.state=stateEnum.getState();
		this.stateInfo=stateEnum.getStateInfo();
		this.shopList=shopList;
		this.count=shopList.size();
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public List<Shop> getShopList() {
		return shopList;
	}
	public void setShopList(List<Shop> shopList) {
		this.shopList = shopList;
	}
	
}
