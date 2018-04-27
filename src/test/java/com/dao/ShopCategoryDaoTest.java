package com.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.domain.ShopCategory;

public class ShopCategoryDaoTest extends BaseTest {
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	@Test
	public void testShopCategoryList() {
		ShopCategory parent=new ShopCategory();
		parent.setShopCategoryId(1L);
		ShopCategory a=new ShopCategory();
		a.setParent(parent);
		System.out.println(shopCategoryDao.queryShopCategory(new ShopCategory()).get(0).getShopCategoryName());
	}
}
