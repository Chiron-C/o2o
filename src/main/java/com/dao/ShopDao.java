package com.dao;

import com.domain.Shop;

public interface ShopDao {
	/**
	 * 新增店铺
	 * @param shop
	 * @return
	 */
	int insertShop(Shop shop);
	/**
	 * 更新shop信息
	 * @param shop
	 * @return
	 */
	int updateShop(Shop shop);
}
