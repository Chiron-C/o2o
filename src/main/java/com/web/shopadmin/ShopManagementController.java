package com.web.shopadmin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.domain.Area;
import com.domain.PersonInfo;
import com.domain.Shop;
import com.domain.ShopCategory;
import com.dto.ShopExecution;
import com.enums.ShopStateEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.AreaService;
import com.service.ShopCategoryService;
import com.service.ShopService;
import com.util.HttpServletRequestUtil;
@Controller
@RequestMapping("shopadmin")
public class ShopManagementController {
	@Autowired
	private ShopService shopService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private AreaService areaService;
	@RequestMapping(value="/getshopinitinfo",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getShopInitInfo(){
		Map<String,Object> modelMap=new HashMap<String,Object>();
		List<ShopCategory> shopCategoryList=new ArrayList<ShopCategory>();
		List<Area> areaList=new ArrayList<Area>(); 
		try {
			shopCategoryList=shopCategoryService.getShopCategoryList(new ShopCategory());
			areaList=areaService.getAreaList();
			modelMap.put("success",true);
			modelMap.put("shopCategoryList",shopCategoryList);
			modelMap.put("areaList", areaList);
		}catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	
	@RequestMapping(value="/registershop",method=RequestMethod.POST)
	private Map<String,Object> registerShop(HttpServletRequest request){
		Map<String,Object> modelMap=new HashMap<String,Object>();
		/*
		 * 接受并转化相应的参数，包括店铺信息和图片信息
		 */
		String shopStr=HttpServletRequestUtil.gerString(request, "shopStr");
		ObjectMapper mapper=new ObjectMapper();
		Shop shop=null;
		try {
			shop=mapper.readValue(shopStr,Shop.class);//从json变成对象
		}catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("msg", e.getMessage());
			return modelMap;
		}
		//解析tupian
		CommonsMultipartFile shopImg=null;
		CommonsMultipartResolver shopResolver=new CommonsMultipartResolver(request.getSession().getServletContext());//文件解析器,从上下文获取本次上传文件的内容
		if(shopResolver.isMultipart(request)) {//判断是否有上传的文件流
			MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest)request;
			shopImg=(CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "上传文件不能为空");
			return modelMap;
		}
		
		/*
		 * 注册店铺
		 */
		if(shop!=null&&shopImg!=null) {
			PersonInfo owner=new PersonInfo();
			//session to do
			owner.setUserId(1L);
			shop.setOwner(owner);
			ShopExecution se=shopService.addShop(shop, shopImg);
			if(se.getState()==ShopStateEnum.CHECK.getState()) {
				modelMap.put("success", true);
			}else {
				modelMap.put("success", false);
				modelMap.put("errMsg", se.getStateInfo());
			}
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺信息");
		}
		return modelMap;
		/*
		 * 返回结果
		 */
	}
	private static void inputStreamToFile(InputStream ins,File file) {
		FileOutputStream os=null;
		try {
			os=new FileOutputStream(file);
			int byteRead=0;
			byte[] buffer=new byte[1024];
			while((byteRead=ins.read(buffer))!=-1) {
				os.write(buffer, 0, byteRead);
			}
		}catch(Exception e){
			throw new RuntimeException("调用inputStream产生异常"+e.getMessage());
		}finally {
			try {
				if(ins!=null) {
					ins.close();
				}
				if(os!=null) {
					os.close();
				}
			}catch(IOException e) {
				throw new RuntimeException("关闭输入输出流的时候出现异常"+e.getMessage());
			}
		}
	}
}
