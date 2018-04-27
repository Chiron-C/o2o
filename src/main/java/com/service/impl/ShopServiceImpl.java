package com.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dao.ShopDao;
import com.domain.Shop;
import com.dto.ShopExecution;
import com.enums.ShopStateEnum;
import com.exceptions.ShopOperationException;
import com.service.ShopService;
import com.util.ImageUtil;
import com.util.PathUtil;
@Service("ShopService")
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopDao shopDao;
	@Override
	@Transactional//开启事务的支持
	public ShopExecution addShop(Shop shop, MultipartFile shopImg) {
		// TODO Auto-generated method stub
		if(shop==null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		try {
			//给店铺信息附初始值(外面不能改变的值)
			shop.setEnableStatus(0);//0是审核中，-1是非法，1是操作成功，2是审核通过
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			int effectedNum=shopDao.insertShop(shop);
			
			if(effectedNum<=0) {
				throw new ShopOperationException("店铺创建失败");//只有ShopOperationException事务才能得到终止，并且回滚
			}else {
				if(shopImg!=null) {
					//存储图片
					try {
						addShopImg(shop,shopImg);
					}catch(Exception e) {
						throw new ShopOperationException("add Img error"+e.getMessage());
					}
					effectedNum=shopDao.updateShop(shop);
					if(effectedNum<=0) {
						throw new ShopOperationException("更新图片地址失败");
					}
					
				}
			}
		}catch(Exception e) {
			throw new ShopOperationException("addShop error"+e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK,shop);//返回execution的同时返回店铺的信息
	}
	private static void addShopImg(Shop shop,MultipartFile shopImg) {
		String dest=PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr=ImageUtil.generateThumbnail(shopImg, dest);//返回相对值路径
		shop.setShopImg(shopImgAddr);
	}

}
