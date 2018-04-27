package com.service;

import org.springframework.web.multipart.MultipartFile;

import com.domain.Shop;
import com.dto.ShopExecution;

public interface ShopService {
	ShopExecution addShop(Shop shop,MultipartFile shopImg);
}
