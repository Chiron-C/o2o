package com.domain;

import java.util.Date;

//店铺下的商品类别
public class ProductCategory {
	private Long productCategoruId;
	private Long shopId;
	private String productCategoryName;
	private Integer priority;
	private Date createTime;
	private Date lastEditTime;
	public Long getProductCategoruId() {
		return productCategoruId;
	}
	public void setProductCategoruId(Long productCategoruId) {
		this.productCategoruId = productCategoruId;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public String getProductCategoryName() {
		return productCategoryName;
	}
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	
}
