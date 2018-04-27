package com.Service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.BaseTest;
import com.domain.Area;
import com.domain.PersonInfo;
import com.domain.Shop;
import com.domain.ShopCategory;
import com.dto.ShopExecution;
import com.enums.ShopStateEnum;
import com.service.ShopService;

public class ShopServiceTest extends BaseTest {
	@Autowired
	private ShopService shopService;
	@Test
	public void testAddShop() {
		Shop shop=new Shop();
		PersonInfo owner=new PersonInfo();
		Area area=new Area();
		ShopCategory shopCategory=new ShopCategory();
		owner.setUserId(1L);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(1L);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("测试的店铺1");
		shop.setShopDesc("test1");
		shop.setShopAddr("test1");
		shop.setPhone("120");
		shop.setShopImg("test");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		File oldFile=new File("D:/Eclipse_Work/o2o/src/main/resources/xiaohuangren.jpg");
		MockMultipartFile mockFile;
		try {
			mockFile = new MockMultipartFile("xiaohuangren.jpg",new FileInputStream(oldFile));
			ShopExecution se=shopService.addShop(shop,(MultipartFile)mockFile);
			assertEquals(ShopStateEnum.CHECK.getState(),se.getState());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
