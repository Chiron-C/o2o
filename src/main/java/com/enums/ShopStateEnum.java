package com.enums;

public enum ShopStateEnum {
	CHECK(0,"审核中"),OFFLINE(-1,"非法店铺"),SUCCESS(1,"操作成功"),NUMM_SHOP(-1002,"shopId为空"),NULL_SHOP(-1003,"shop为空");
	private int state; 
	private String stateInfo;
	private ShopStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	/*
	 * 根据相应的state值返回对应的enum值
	 */
	public static ShopStateEnum stateOf(int state) {
		for(ShopStateEnum stateEnum:values()) {//ordinal()会返回每个enum生命的时候的次序，values（）会严格按照定义的顺序来产生数组
			if(stateEnum.getState()==state) {
				System.out.println(stateEnum.name());//返回名字
				return stateEnum;
			}
			//stateEnum.valueOf("审核中");根据名字来返回实例
		}
		return null;
	}
	public int getState() {
		return state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	
}
